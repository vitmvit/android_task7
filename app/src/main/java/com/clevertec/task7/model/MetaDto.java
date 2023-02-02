package com.clevertec.task7.model;

import com.clevertec.task7.model.parent.MetaTitleDto;

import java.util.List;

public class MetaDto extends MetaTitleDto {

    private String image;
    private List<MetaFieldDto> fields;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMacbook() {
        return 1;
    }

    public List<MetaFieldDto> getFields() {
        return fields;
    }

    public void setFields(List<MetaFieldDto> fields) {
        this.fields = fields;
    }
}
