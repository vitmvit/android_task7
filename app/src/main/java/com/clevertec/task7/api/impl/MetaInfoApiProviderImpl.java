package com.clevertec.task7.api.impl;

import com.clevertec.task7.api.MetaInfoApi;
import com.clevertec.task7.api.MetaInfoApiProvider;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.clevertec.task7.constant.Constants.BASE_URL;

public class MetaInfoApiProviderImpl implements MetaInfoApiProvider {

    private final MetaInfoApi metaInfoApi;

    public MetaInfoApiProviderImpl() {
        metaInfoApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(MetaInfoApi.class);
    }

    @Override
    public MetaInfoApi getMetaInfoApi() {
        return metaInfoApi;
    }
}
