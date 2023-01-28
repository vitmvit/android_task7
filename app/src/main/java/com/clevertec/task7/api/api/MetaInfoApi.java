package com.clevertec.task7.api.api;

import com.clevertec.task7.model.dto.FormRequestDto;
import com.clevertec.task7.model.dto.MetaDto;
import com.clevertec.task7.model.dto.OperationResultDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MetaInfoApi {

    @GET("meta")
    Call<MetaDto> getMetaInfo();

    @POST("data")
    Call<OperationResultDto> formRequest(@Body FormRequestDto formRequestDto);
}
