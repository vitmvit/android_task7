package com.clevertec.task7.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OperationResultDto implements Serializable {

    @SerializedName("result")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
