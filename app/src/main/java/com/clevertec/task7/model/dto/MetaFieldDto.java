package com.clevertec.task7.model.dto;

import com.clevertec.task7.model.dto.parent.MetaNameTypeDto;

import java.util.Map;

public class MetaFieldDto extends MetaNameTypeDto {

    private Map<String, String> values;

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }
}
