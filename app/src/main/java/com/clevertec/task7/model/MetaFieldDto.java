package com.clevertec.task7.model;

import com.clevertec.task7.model.parent.MetaNameTypeDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaFieldDto extends MetaNameTypeDto {

    private Map<String, String> values = new HashMap<>();

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }
}
