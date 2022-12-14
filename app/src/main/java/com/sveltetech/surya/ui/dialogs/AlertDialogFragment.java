package com.sveltetech.surya.ui.dialogs;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.sveltetech.surya.R;


public class AlertDialogFragment extends DialogFragment {

    private String message;

    public AlertDialogFragment(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        builder.setMessage(message)
                .setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss());
        return builder.create();
    }
}
