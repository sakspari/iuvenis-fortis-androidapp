package com.example.hotel.view.home.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.hotel.R;

public class ReviewDialog extends AppCompatDialogFragment {
    private EditText inputReview;
    private Button btnSubmit;
    private ReviewDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.review_dialog, null);
        
        inputReview = view.findViewById(R.id.edit_text);
        btnSubmit = view.findViewById(R.id.btn_submit);

        builder.setView(view)
                .setTitle("Review Room")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String review = inputReview.getText().toString();
                        listener.passReview(review);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ReviewDialog.ReviewDialogListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface ReviewDialogListener{
        void passReview(String review);
    }
}
