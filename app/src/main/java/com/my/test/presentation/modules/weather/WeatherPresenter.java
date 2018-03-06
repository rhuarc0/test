package com.my.test.presentation.modules.weather;

import android.text.format.DateUtils;
import android.util.Log;
import android.util.Pair;

import com.annimon.stream.OptionalInt;
import com.annimon.stream.Stream;
import com.my.test.domain.entities.City;
import com.my.test.domain.entities.CurrentWeather;
import com.my.test.domain.entities.Forecast;
import com.my.test.domain.entities.ForecastUnit;
import com.my.test.domain.interactors.WeatherInteractor;
import com.my.test.presentation.modules.base.BasePresenter;
import com.my.test.presentation.utils.Utils;

import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class WeatherPresenter extends BasePresenter<WeatherContract.View> implements WeatherContract.Presenter {

    @Inject
    WeatherInteractor weatherInteractor;

    private List<City> cityList = new ArrayList<>();

    @Inject
    public WeatherPresenter() {}


    @Override
    public void onViewAttached() { }

    private DisposableObserver<Pair<CurrentWeather, Forecast>> createObserver() {
        return new DisposableObserver<Pair<CurrentWeather,Forecast>>() {
            @Override
            protected void onStart() {
                super.onStart();
                view.showEmptyState();
            }

            @Override
            public void onNext(Pair<CurrentWeather, Forecast> pair) {
                if (pair.first.getCity() != null &&
                        pair.first.getCity().getName().equals(""))
                    view.showEmptyState();
                else {

                    if (pair.first.getTemperature() == null)
                        view.setWeatherPending(true);
                    else {
                        view.setWeatherPending(false);
                        view.showCurrentWeather(pair.first);

                        Calendar calendar = Calendar.getInstance();
                        long diff3Hours = (3 * 60 * 60 * 1000);

                        boolean isObsolete = Utils.isDateDifferenceMoreThan(calendar.getTime(),
                                pair.first.getTimeOfDataCalculation(),
                                diff3Hours);
                        view.showIfWeatherIsObsolete(isObsolete);
                    }

                    if (pair.second.getForecastUnitList().isEmpty())
                        view.setForecastPending(true);
                    else {
                        view.setForecastPending(false);
                        view.showForecast(prepareForecast(pair.second));
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof UnknownHostException)
                    view.showMessage("Can't connect to the server");
                else
                    view.showMessage(e.getMessage());
            }

            @Override
            public void onComplete() {
                view.setForecastPending(false);
                view.setWeatherPending(false);
            }
        };
    }

    private static List<ForecastUnit> prepareForecast(Forecast forecast) {
        Calendar calendar = Calendar.getInstance();

        // Получим ближайший прогноз на этот день и 12-часовые на все последующие
        List<ForecastUnit> units = forecast.getForecastUnitList();
        List<ForecastUnit> result = new ArrayList<>(units.size());

        if (units.size() > 0)
            if (DateUtils.isToday(units.get(0).getForecastTime().getTime()))
                result.add(units.get(0));

        List<ForecastUnit> filteredList = Stream.of(forecast.getForecastUnitList())
                .filter(unit -> {
                    calendar.setTime(unit.getForecastTime());
                    if (DateUtils.isToday(unit.getForecastTime().getTime()))
                        return false;

                    // API возвращает разные часы
                    return Utils.between(calendar.get(Calendar.HOUR_OF_DAY) , 12, 14);
                })
                .toList();
        result.addAll(filteredList);

        // Рассчитаем мин/макс температуры на каждый день

        List<Map.Entry<Date, Pair<OptionalInt, OptionalInt>>> temperatureList = Stream
                .of(forecast.getForecastUnitList())
                .groupBy(unit -> getStartOfDay(unit.getForecastTime(), calendar))
                .map(entry -> {

                    OptionalInt min = Stream.of(entry.getValue())
                            .mapToInt(ForecastUnit::getTempMin)
                            .min();
                    OptionalInt max = Stream.of(entry.getValue())
                            .mapToInt(ForecastUnit::getTempMax)
                            .max();

                    Pair<OptionalInt, OptionalInt> pair = new Pair<>(min, max);
                    return (Map.Entry<Date, Pair<OptionalInt, OptionalInt>>) new AbstractMap.SimpleEntry<>(entry.getKey(), pair);
                }).toList();

        // Подменим в результатах температуры
        result = Stream.of(result).map(unit -> {
            Date date = getStartOfDay(unit.getForecastTime(), calendar);
            Pair<OptionalInt, OptionalInt> pair = Stream.of(temperatureList)
                    .filter(entry -> entry.getKey().getTime() == date.getTime())
                    .findFirst()
                    .get()
                    .getValue();

            unit.setTempMin(pair.first.getAsInt());
            unit.setTempMax(pair.second.getAsInt());

            return unit;
        }).toList();
        return result;
    }

    private static Date getStartOfDay(Date day, Calendar cal) {
        if (day == null)
            day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    @Override
    public void selectCity(int position) {
        weatherInteractor.setDefaultCityPosition(position);

        Observable<CurrentWeather> obs1 = weatherInteractor.fetchCurrentWeather(cityList.get(position));
        Observable<Forecast> obs2 = weatherInteractor.fetchForecast(cityList.get(position));

        Disposable disposable =  Observable.zip(obs1, obs2, Pair::new)
                .doOnNext(pair -> {
                    view.setWeatherPending(pair.first.getTemperature() == null);
                    view.setForecastPending(pair.second.getForecastUnitList().isEmpty());
                })
                .subscribeWith(createObserver());
        subscriptions.add(disposable);
    }

    @Override
    public void refresh() {
        Disposable disposable = weatherInteractor.fetchCities()
                // Подргрузив города, строим меню, подтягиваем текущий отображаемый индекс
                .doOnNext(cities -> {
                    cityList.clear();
                    cityList.addAll(cities);
                    view.showCityList(cities);

                    int currentPosition = weatherInteractor.getDefaultCityPosition();

                    if (cities.size() > 0) {
                        if (currentPosition == -1 || currentPosition >= cities.size()) {
                            currentPosition = 0;
                            weatherInteractor.setDefaultCityPosition(currentPosition);
                        }
                        view.setCurrentCityMenuPosition(currentPosition);
                    }
                })
                // Запрашиваем погоду и прогноз
                .flatMap(cities -> {
                    if (cities.size() > 0) {
                        int currentPosition = weatherInteractor.getDefaultCityPosition();

                        Observable<CurrentWeather> obs1 = weatherInteractor.fetchCurrentWeather(cities.get(currentPosition));
                        Observable<Forecast> obs2 = weatherInteractor.fetchForecast(cities.get(currentPosition));
                        return Observable.zip(obs1, obs2, Pair::new);
                    }
                    return Observable.empty(); // Если нет городов, вернём пустой Observable
                })
                // Если с кэша ничего не пришло (первый запрос), показываем прогресс
                .doOnNext(pair -> {
                    view.setWeatherPending(pair.first.getTemperature() == null);
                    view.setForecastPending(pair.second.getForecastUnitList().isEmpty());
                })
                // Если Observable пустой (нет городов), возвращаем пустышку д.т.ч. заполнить поля
                .defaultIfEmpty(new Pair<>(CurrentWeather.createEmptyObject(), Forecast.createEmptyObject()))
                .subscribeWith(createObserver());
        subscriptions.add(disposable);
    }
}
