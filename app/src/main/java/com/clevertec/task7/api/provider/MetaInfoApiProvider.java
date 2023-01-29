package com.clevertec.task7.api.provider;

import com.clevertec.task7.api.api.MetaInfoApi;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.clevertec.task7.constant.Constants.BASE_URL;

public class MetaInfoApiProvider {

    private final MetaInfoApi metaInfoApi;

    public MetaInfoApiProvider() {
        metaInfoApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(MetaInfoApi.class);
    }

    public MetaInfoApi getMetaInfoApi() {
        return metaInfoApi;
    }
}
