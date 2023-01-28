package com.clevertec.task7.api.impl;

import com.clevertec.task7.api.api.MetaInfoApi;
import com.clevertec.task7.api.api.MetaInfoApiProvider;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MetaInfoApiProviderImpl implements MetaInfoApiProvider {

    private final MetaInfoApi metaInfoApi;

    public MetaInfoApiProviderImpl() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://test.clevertec.ru/tt/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        metaInfoApi = retrofit.create(MetaInfoApi.class);
    }

    @Override
    public MetaInfoApi getMetaInfoApi() {
        return metaInfoApi;
    }
}
