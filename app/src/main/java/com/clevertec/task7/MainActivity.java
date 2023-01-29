package com.clevertec.task7;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.clevertec.task7.adapter.RecyclerViewAdapter;
import com.clevertec.task7.api.service.MetaInfoApiService;
import com.clevertec.task7.fragment.ShowDialogFragment;
import com.clevertec.task7.model.FormRequestDto;
import com.clevertec.task7.model.MetaDto;

import static com.clevertec.task7.constant.Constants.DEF_VALUE;

public class MainActivity extends AppCompatActivity {

    private static Context context;
    SharedPreferences sharedPreferences;
    private RecyclerViewAdapter adapter;

    public static Context getAppContext() {
        return MainActivity.context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarVisible();
        MainActivity.context = getApplicationContext();

        sharedPreferencesDefault();

        MetaInfoApiService metaInfoApiService = new ViewModelProvider(this).get(MetaInfoApiService.class);
        LiveData<MetaDto> metaDtoLiveData = metaInfoApiService.getQuery();
        metaDtoLiveData.observe(this, new Observer<MetaDto>() {
            @Override
            public void onChanged(MetaDto metaDto) {
                loadInfo(metaDto);
            }
        });
    }

    public void loadInfo(MetaDto metaDto) {
        loadRecycler(metaDto);
        loadTitleForm(metaDto);
        loadImage(metaDto);
        progressBarInVisible();

        Button buttonSend = findViewById(R.id.button_send);
        ImageButton imageButton = findViewById(R.id.image_button);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarVisible();

                FormRequestDto formRequestDto = adapter.getFormRequestDto();
                if (formRequestDto.isEmpty()) {
                    Toast.makeText(v.getContext(), R.string.EMPTY_FORM, Toast.LENGTH_SHORT).show();
                } else {
                    MetaInfoApiService answer = new MetaInfoApiService();
                    answer.sendForm(formRequestDto);
                }

                progressBarInVisible();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toShowContactsFragment();
            }
        });
    }

    public void toShowContactsFragment() {
        ShowDialogFragment dialog = new ShowDialogFragment();
        dialog.show(getSupportFragmentManager(), String.valueOf(R.string.tag));
    }

    public void loadResults(String operationResultDto) {
        sharedPreferences = getAppContext().getSharedPreferences(String.valueOf(R.string.NAME_SP), MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit().clear();
        editor.putString(String.valueOf(R.string.KEY_SP_NAME), operationResultDto);
        editor.apply();
    }

    private void sharedPreferencesDefault() {
        sharedPreferences = getAppContext().getSharedPreferences(String.valueOf(R.string.NAME_SP), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().clear();
        editor.putString(String.valueOf(R.string.KEY_SP_NAME), DEF_VALUE);
        editor.apply();
    }

    private void loadRecycler(MetaDto metaDto) {
        RecyclerView recyclerView = this.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerViewAdapter(metaDto.getFields(), this);
        recyclerView.setAdapter(adapter);
    }

    private void loadTitleForm(MetaDto metaDto) {
        TextView textView = this.findViewById(R.id.forms_title);
        textView.setText(metaDto.getTitle());
    }

    private void loadImage(MetaDto metaDto) {
        ImageView imageView = this.findViewById(R.id.forms_image);
        Glide
                .with(this)
                .load(metaDto.getImage())
                .into(imageView);
    }

    private void progressBarVisible() {
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void progressBarInVisible() {
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
    }
}