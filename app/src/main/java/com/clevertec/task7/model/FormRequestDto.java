package com.clevertec.task7.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FormRequestDto implements Serializable {

    private Map<String, String> form = new HashMap<>();

    public Map<String, String> getForm() {
        return form;
    }

    public void setForm(Map<String, String> form) {
        this.form = form;
    }

    public void putKeyValue(String key, String value) {
        form.put(key, value);
    }

    public boolean isEmpty() {
        return form.size() == 0;
    }
}
