package com.example.hotel.view.home.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;

import com.example.hotel.R;
import com.example.hotel.adapter.ReviewListener;
import com.example.hotel.databinding.ReviewDialogBinding;
import com.example.hotel.model.BookDetail;

import java.util.Date;

public class ReviewDialog extends AppCompatDialogFragment {

    ReviewDialogBinding binding;
    ReviewListener listener;

    public ReviewDialog(ReviewListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        binding = DataBindingUtil.inflate(inflater, R.layout.review_dialog, null, false);

        builder.setView(binding.getRoot())
                .setTitle("Create new Review for This Room!");

        binding.btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onReviewSubmit(binding.getReviewDesc());
                dismiss();
            }
        });

        return builder.create();
    }
}
