package com.clevertec.task7.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import com.clevertec.task7.MainActivity;
import com.clevertec.task7.R;
import org.jetbrains.annotations.NotNull;

import static android.content.Context.MODE_PRIVATE;
import static com.clevertec.task7.constant.Constants.BUTTON_TEXT;

public class ShowDialogFragment extends DialogFragment {

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return requireActivity().getLayoutInflater().inflate(R.layout.fragment_show_dialog, null);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        sharedPreferences = MainActivity.getAppContext().getSharedPreferences(String.valueOf(R.string.NAME_SP), MODE_PRIVATE);
        String result = sharedPreferences.getString(String.valueOf(R.string.KEY_SP_NAME), String.valueOf(R.string.DEF_VALUE));

        builder.setTitle(R.string.QUERY_RESULT)
                .setMessage(result)
                .setIcon(R.drawable.ic_android_black_36dp)
                .setPositiveButton(BUTTON_TEXT, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
