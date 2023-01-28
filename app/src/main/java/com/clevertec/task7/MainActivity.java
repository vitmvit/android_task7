package com.clevertec.task7;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.clevertec.task7.adapter.RecyclerViewAdapter;
import com.clevertec.task7.api.impl.MetaInfoApiServiceImpl;
import com.clevertec.task7.model.dto.MetaDto;
import com.clevertec.task7.model.pojo.RecyclerItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private TextView textView;
    private ImageView imageView;
    private RecyclerViewAdapter adapter;
    private List<RecyclerItem> listItems;
    private TextView txtTitle;
    private TextView txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MetaInfoApiServiceImpl atmAnswer = new MetaInfoApiServiceImpl(this);
        atmAnswer.getInfo();
    }


    public void addInfo(MetaDto metaDto) {
        recyclerView = this.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerViewAdapter(metaDto.getFields(), this);
        recyclerView.setAdapter(adapter);

        textView = this.findViewById(R.id.forms_title);
        textView.setText(metaDto.getTitle());
        imageView = this.findViewById(R.id.forms_image);
        Glide
                .with(this)
                .load(metaDto.getImage())
                .into(imageView);

        System.out.printf(metaDto.toString());
    }
}