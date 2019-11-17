package com.goland.gas_delivery.model;


import android.util.Base64;

import java.io.IOException;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.goland.gas_delivery.model.Constants.CONSUMER_KEY;
import static com.goland.gas_delivery.model.Constants.CONSUMER_SECRET;

public class NetworkUtil {
    private static final String TAG = "NetworkUtil";
     // Getting an access token

    public static MPESAInterface getRetrofit(){
        String credentials = CONSUMER_KEY + ":" + CONSUMER_SECRET;
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);


         //Using okhttp interceptor to add an authorization header

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder()
                        .addHeader("Authorization", basic)
                        .method(original.method(),original.body());
                return  chain.proceed(builder.build());
            }
        });

        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constants.MPESA_BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MPESAInterface.class);
    }

     //Sending the STK push request

    public static MPESAInterface getRetrofit(String accesstoken){
        final String credentials = "Bearer " + accesstoken;

         // Using okhttp interceptor to add an authorization header

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder()
                        .addHeader("Authorization", credentials)
                        .method(original.method(),original.body());
                return  chain.proceed(builder.build());
            }
        });

        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constants.MPESA_BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MPESAInterface.class);
    }
}