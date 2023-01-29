package com.clevertec.task7.api.api;

import com.clevertec.task7.model.FormRequestDto;
import com.clevertec.task7.model.MetaDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MetaInfoApi {

    @GET("meta")
    Call<MetaDto> getMetaInfo();

    @POST("data/")
    Call<String> formRequest(@Body FormRequestDto formRequestDto);
}
