package com.clevertec.task7.api;

import com.clevertec.task7.model.dto.FormRequestDto;

public interface MetaInfoApiService {

    void getInfo();

    void sendForm(FormRequestDto formRequestDto);
}
