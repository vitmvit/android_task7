package com.clevertec.task7.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.clevertec.task7.R;
import com.clevertec.task7.model.FormRequestDto;
import com.clevertec.task7.model.MetaFieldDto;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.clevertec.task7.constant.Constants.NUMBER_FIELD;
import static com.clevertec.task7.constant.Constants.TEXT_FIELD;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<MetaFieldDto> listItems;
    private final Activity mContext;
    private final FormRequestDto formRequestDto = new FormRequestDto();

    public RecyclerViewAdapter(List<MetaFieldDto> listItems, Activity mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    public FormRequestDto getFormRequestDto() {
        return formRequestDto;
    }

    @Override
    public int getItemViewType(int position) {
        return listItems.get(position).getType().equals(TEXT_FIELD)
                ? 0
                : listItems.get(position).getType().equals(NUMBER_FIELD)
                ? 1
                : 2;
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
            ((ViewHolderText) holder).fieldTitle.setText(metaFieldDto.getTitle());
            ((ViewHolderText) holder).fieldName = (metaFieldDto.getName());
        } else if (this.getItemViewType(position) == 1) {
            ((ViewHolderNumber) holder).fieldTitle.setText(metaFieldDto.getTitle());
            ((ViewHolderNumber) holder).fieldName = (metaFieldDto.getName());
        } else {
            ((ViewHolderComboBox) holder).fieldTitle.setText(metaFieldDto.getTitle());
            ((ViewHolderComboBox) holder).fieldName = (metaFieldDto.getName());
            List<String> valueList = new ArrayList<>(metaFieldDto.getValues().values());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.mContext, android.R.layout.simple_spinner_item, valueList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ((ViewHolderComboBox) holder).spinner.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolderText extends ViewHolder {

        public TextView fieldTitle;
        public String fieldName;
        public EditText fieldText;

        public ViewHolderText(View itemView) {
            super(itemView);
            fieldTitle = itemView.findViewById(R.id.textViewTitle);
            fieldText = itemView.findViewById(R.id.edit_text);

            fieldText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    formRequestDto.putKeyValue(fieldName, String.valueOf(s));
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }

    public class ViewHolderNumber extends ViewHolder {

        public TextView fieldTitle;
        public String fieldName;
        public EditText fieldText;

        public ViewHolderNumber(View itemView) {
            super(itemView);
            fieldTitle = itemView.findViewById(R.id.textViewTitle);
            fieldText = itemView.findViewById(R.id.edit_number);
            fieldText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    formRequestDto.putKeyValue(fieldName, String.valueOf(s));
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }

    public class ViewHolderComboBox extends ViewHolder {

        public TextView fieldTitle;
        public String fieldName;
        public Spinner spinner;

        public ViewHolderComboBox(View itemView) {
            super(itemView);
            fieldTitle = itemView.findViewById(R.id.textViewTitle);
            spinner = itemView.findViewById(R.id.combobox);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    formRequestDto.putKeyValue(fieldName, spinner.getSelectedItem().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });
        }
    }
}
