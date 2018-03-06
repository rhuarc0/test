package com.my.test.presentation.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.my.test.R;
import com.my.test.data.rest.WebService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private final String ApiKeyQueryName = "APPID";

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor apiKeyInterceptor) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebService.URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    WebService provideWebService(Retrofit retrofit) {
        return retrofit.create(WebService.class);
    }

    @Provides
    @Singleton
    Interceptor provideApiKeyInterceptor(@Named(ApiKeyQueryName) String apiKey) {
        return chain -> {
            HttpUrl url = chain.request().url().newBuilder()
                .addQueryParameter(ApiKeyQueryName, apiKey)
                .build();
            Request request = chain.request().newBuilder().url(url).build();
            return chain.proceed(request);
        };
    }

    @Provides
    @Named(ApiKeyQueryName)
    @Singleton
    String provideApiKey(Context context) {
        return context.getString(R.string.api_key);
    }
}
