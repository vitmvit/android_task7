package com.clevertec.task7.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.clevertec.task7.R;
import com.clevertec.task7.model.dto.MetaFieldDto;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.clevertec.task7.constant.Constants.NUMBER_FIELD;
import static com.clevertec.task7.constant.Constants.TEXT_FIELD;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<MetaFieldDto> listItems;
    private final Activity mContext;

    public RecyclerViewAdapter(List<MetaFieldDto> listItems, Activity mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if (listItems.get(position).getTitle().equals(TEXT_FIELD)) {
            return 0;
        } else if (listItems.get(position).getTitle().equals(NUMBER_FIELD)) {
            return 1;
        } else {
            return 2;
        }
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        if (viewType == 0) {
            return new ViewHolderText(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_text, parent, false));
        } else if (viewType == 1) {
            return new ViewHolderNumber(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_number, parent, false));
        } else {
            return new ViewHolderComboBox(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_combobox, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NotNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final MetaFieldDto metaFieldDto = listItems.get(position);

        if (this.getItemViewType(position) == 0) {
            ((ViewHolderText) holder).txtName.setText(metaFieldDto.getName());
        } else if (this.getItemViewType(position) == 1) {
            ((ViewHolderNumber) holder).txtName.setText(metaFieldDto.getName());
        } else {
            ((ViewHolderComboBox) holder).txtName.setText(metaFieldDto.getName());

            List<String> valueList = new ArrayList<>(metaFieldDto.getValues().values());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.mContext, android.R.layout.simple_spinner_item, valueList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ((ViewHolderComboBox) holder).spinner.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolderText extends ViewHolder {

        public TextView txtName;

        public ViewHolderText(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textViewTitle);

        }
    }

    public class ViewHolderNumber extends ViewHolder {

        public TextView txtName;

        public ViewHolderNumber(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textViewTitle);
        }
    }

    public class ViewHolderComboBox extends ViewHolder {

        public TextView txtName;
        public Spinner spinner;

        public ViewHolderComboBox(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textViewTitle);
            spinner = itemView.findViewById(R.id.combobox);
//            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(view.getContext(), spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> arg0) {
//                }
//            });
        }
    }
}
