package com.clevertec.task7.api.impl;

import android.widget.Toast;
import com.clevertec.task7.MainActivity;
import com.clevertec.task7.api.MetaInfoApiProvider;
import com.clevertec.task7.api.MetaInfoApiService;
import com.clevertec.task7.model.dto.MetaDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.clevertec.task7.constant.Constants.DATA_LOADING_ERROR;

public class MetaInfoApiServiceImpl implements MetaInfoApiService {
    private final MainActivity mainActivity;

    private final MetaInfoApiProvider metaInfoApiProvider;

    public MetaInfoApiServiceImpl(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        metaInfoApiProvider = new MetainfoApiProviderImpl();
    }

    public void getInfo() {

        Call<MetaDto> call = metaInfoApiProvider.getMetaInfoApi().getMetaInfo();
        call.enqueue(new Callback<MetaDto>() {
            @Override
            public void onResponse(Call<MetaDto> call, Response<MetaDto> response) {
                mainActivity.addInfo(response.body());
            }

            @Override
            public void onFailure(Call<MetaDto> call, Throwable t) {
                Toast.makeText(mainActivity, DATA_LOADING_ERROR, Toast.LENGTH_LONG).show();
            }
        });
    }
}
