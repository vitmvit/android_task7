package com.clevertec.task7.model.parent;

public abstract class MetaNameTypeDto extends MetaTitleDto {

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String setType(String type) {
        this.type = type;
        return type;
    }
}
