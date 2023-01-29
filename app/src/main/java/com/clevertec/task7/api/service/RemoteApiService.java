package com.clevertec.task7.api.service;

import android.annotation.SuppressLint;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.clevertec.task7.MainActivity;
import com.clevertec.task7.R;
import com.clevertec.task7.api.provider.RemoteApiProvider;
import com.clevertec.task7.dagger.component.DaggerApiComponent;
import com.clevertec.task7.model.FormRequestDto;
import com.clevertec.task7.model.MetaDto;
import com.clevertec.task7.model.ResultDto;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteApiService extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    private final MainActivity mainActivity = new MainActivity();
    private final RemoteApiProvider remoteApiProvider;
    private MutableLiveData<MetaDto> mutableLiveDataGet;

    public RemoteApiService() {
        remoteApiProvider = new RemoteApiProvider();
        DaggerApiComponent.create().inject(remoteApiProvider);
    }

    public LiveData<MetaDto> getQuery() {
        if (mutableLiveDataGet == null) {
            mutableLiveDataGet = new MutableLiveData<MetaDto>();
            getInfo();
        }
        return mutableLiveDataGet;
    }

    public void getInfo() {
        Call<MetaDto> call = remoteApiProvider.getMetaInfoApi().getMetaInfo();
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
        Call<ResultDto> call = remoteApiProvider.getMetaInfoApi().formRequest(formRequestDto);
        call.enqueue(new Callback<ResultDto>() {
            @Override
            public void onResponse(@NotNull Call<ResultDto> call, @NotNull Response<ResultDto> response) {
                assert response.body() != null;
                mainActivity.loadResults(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ResultDto> call, @NotNull Throwable t) {
                Toast.makeText(MainActivity.getAppContext(), R.string.DATA_LOADING_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
