package com.clevertec.task7.api.service;

import android.annotation.SuppressLint;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.clevertec.task7.MainActivity;
import com.clevertec.task7.R;
import com.clevertec.task7.api.provider.MetaInfoApiProvider;
import com.clevertec.task7.dagger.component.DaggerApiComponent;
import com.clevertec.task7.model.FormRequestDto;
import com.clevertec.task7.model.MetaDto;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MetaInfoApiService extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    private final MainActivity mainActivity = new MainActivity();
    private final MetaInfoApiProvider metaInfoApiProvider;
    private MutableLiveData<MetaDto> mutableLiveDataGet;

    public MetaInfoApiService() {
        metaInfoApiProvider = new MetaInfoApiProvider();
        DaggerApiComponent.create().inject(metaInfoApiProvider);
    }

    public LiveData<MetaDto> getQuery() {
        if (mutableLiveDataGet == null) {
            mutableLiveDataGet = new MutableLiveData<MetaDto>();
            getInfo();
        }
        return mutableLiveDataGet;
    }

    public void getInfo() {
        Call<MetaDto> call = metaInfoApiProvider.getMetaInfoApi().getMetaInfo();
        call.enqueue(new Callback<MetaDto>() {
            @Override
            public void onResponse(@NotNull Call<MetaDto> call, @NotNull Response<MetaDto> response) {
                assert response.body() != null;
                mutableLiveDataGet.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<MetaDto> call, @NotNull Throwable t) {
                mutableLiveDataGet.postValue(null);
                Toast.makeText(MainActivity.getAppContext(), com.clevertec.task7.R.string.DATA_LOADING_ERROR, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sendForm(FormRequestDto formRequestDto) {
        Call<String> call = metaInfoApiProvider.getMetaInfoApi().formRequest(formRequestDto);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                assert response.body() != null;
                mainActivity.loadResults(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                Toast.makeText(MainActivity.getAppContext(), R.string.DATA_LOADING_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
