package com.clevertec.task7;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.clevertec.task7.adapter.RecyclerViewAdapter;
import com.clevertec.task7.api.impl.MetaInfoApiServiceImpl;
import com.clevertec.task7.fragment.ShowDialogFragment;
import com.clevertec.task7.model.dto.FormRequestDto;
import com.clevertec.task7.model.dto.MetaDto;

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

        MetaInfoApiServiceImpl atmAnswer = new MetaInfoApiServiceImpl(this);
        atmAnswer.getInfo();
    }

    public void loadInfo(MetaDto metaDto) {
        loadRecycler(metaDto);
        loadTitleForm(metaDto);
        loadImage(metaDto);
        progressBarInVisible();

        Button button_send = findViewById(R.id.button_send);
        ImageButton image_button = findViewById(R.id.image_button);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormRequestDto formRequestDto = adapter.getFormRequestDto();
                if (formRequestDto.isEmpty()) {
                    Toast.makeText(v.getContext(), R.string.EMPTY_FORM, Toast.LENGTH_SHORT).show();
                } else {
                    MetaInfoApiServiceImpl atmAnswer = new MetaInfoApiServiceImpl(new MainActivity());
                    atmAnswer.sendForm(formRequestDto);
                }
            }
        });

        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toShowContactsFragment();
            }
        });
    }

    public void toShowContactsFragment() {
        ShowDialogFragment dialog = new ShowDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
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
        editor.putString(String.valueOf(R.string.KEY_SP_NAME), String.valueOf(R.string.DEF_VALUE));
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