package com.clevertec.task7.api.api;

import com.clevertec.task7.model.FormRequestDto;
import com.clevertec.task7.model.MetaDto;
import com.clevertec.task7.model.ResultDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RemoteApi {

    @Headers("Content-Type: application/json")
    @GET("meta")
    Call<MetaDto> getMetaInfo();

    @Headers("Content-Type: application/json")
    @POST("data/")
    Call<ResultDto> formRequest(@Body FormRequestDto formRequestDto);
}
