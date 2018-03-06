package com.my.test.domain.interactors;

import org.json.JSONObject;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;


public abstract class DefaultObserver<T> extends DisposableObserver<T> {
    private static final String ERROR_CODE = "400";

    @Override
    protected void onStart() {
        super.onStart();
        // Do override
    }

    @Override
    public void onNext(T t) {
        // Do override
    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) e).response().errorBody();
            if (getErrorMessage(responseBody).equals(ERROR_CODE)) {
                onHttpError(e);
            }
        } else
            onUnknownError(e);
    }

    @Override
    public void onComplete() {
        // Do override
    }


    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("cod");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    protected void onHttpError(Throwable throwable) {
        //do override
    }

    protected void onUnknownError(Throwable throwable) {
        //do override
    }
}
