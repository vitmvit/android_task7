package com.clevertec.task7.api.impl;

import android.widget.Toast;
import com.clevertec.task7.MainActivity;
import com.clevertec.task7.R;
import com.clevertec.task7.api.MetaInfoApiProvider;
import com.clevertec.task7.api.MetaInfoApiService;
import com.clevertec.task7.model.dto.FormRequestDto;
import com.clevertec.task7.model.dto.MetaDto;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MetaInfoApiServiceImpl implements MetaInfoApiService {
    private final MainActivity mainActivity;

    private final MetaInfoApiProvider metaInfoApiProvider;

    public MetaInfoApiServiceImpl(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        metaInfoApiProvider = new MetaInfoApiProviderImpl();
    }

    public void getInfo() {

        Call<MetaDto> call = metaInfoApiProvider.getMetaInfoApi().getMetaInfo();
        call.enqueue(new Callback<MetaDto>() {
            @Override
            public void onResponse(@NotNull Call<MetaDto> call, @NotNull Response<MetaDto> response) {
                assert response.body() != null;
                mainActivity.loadInfo(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<MetaDto> call, @NotNull Throwable t) {
                Toast.makeText(mainActivity, com.clevertec.task7.R.string.DATA_LOADING_ERROR, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void sendForm(FormRequestDto formRequestDto) {
        Call<String> call = metaInfoApiProvider.getMetaInfoApi().formRequest(formRequestDto);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                assert response.body() != null;
                String body = response.body();
                mainActivity.loadResults(body);
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                Toast.makeText(MainActivity.getAppContext(), R.string.DATA_LOADING_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
