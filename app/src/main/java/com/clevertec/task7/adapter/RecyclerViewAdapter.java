package com.clevertec.task7.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.clevertec.task7.MainActivity;
import com.clevertec.task7.R;
import com.clevertec.task7.model.dto.MetaFieldDto;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.clevertec.task7.constant.Constants.NUMBER_FIELD;
import static com.clevertec.task7.constant.Constants.TEXT_FIELD;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<MetaFieldDto> listItems;
    private final MainActivity mContext;

    public RecyclerViewAdapter(List<MetaFieldDto> listItems, MainActivity mContext) {
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
    public void onBindViewHolder(@NotNull final ViewHolder holder, final int position) {

        final MetaFieldDto metaFieldDto = listItems.get(position);


        if (this.getItemViewType(position) == 0) {
            ((ViewHolderText) holder).txtTitle.setText(metaFieldDto.getTitle());
        } else if (this.getItemViewType(position) == 1) {
            ((ViewHolderNumber) holder).txtTitle.setText(metaFieldDto.getTitle());
        } else {
            ((ViewHolderComboBox) holder).txtTitle.setText(metaFieldDto.getTitle());

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

        public TextView txtTitle;

        public ViewHolderText(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }

    public class ViewHolderNumber extends ViewHolder {

        public TextView txtTitle;

        public ViewHolderNumber(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }

    public class ViewHolderComboBox extends ViewHolder {

        public TextView txtTitle;
        public Spinner spinner;

        public ViewHolderComboBox(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textViewTitle);
            spinner = itemView.findViewById(R.id.combobox);
        }
    }
}
