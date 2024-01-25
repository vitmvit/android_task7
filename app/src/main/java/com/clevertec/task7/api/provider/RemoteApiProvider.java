package com.clevertec.task7.api.provider;

import com.clevertec.task7.api.api.RemoteApi;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.clevertec.task7.constant.Constants.BASE_URL;

public class RemoteApiProvider {

    private final RemoteApi remoteApi;

    public RemoteApiProvider() {
        remoteApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(RemoteApi.class);
    }

    public RemoteApi getMetaInfoApi() {
        return remoteApi;
    }
}
