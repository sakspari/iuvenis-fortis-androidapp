package com.example.hotel.view.home.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.hotel.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class BookingDialog extends AppCompatDialogFragment {

    private TextInputLayout etDateFrom;
    private TextInputLayout etDateTo;
    private BookingDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.booking_dialog, null);

        builder.setView(view)
                .setTitle("Pick Booking Time")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Booking", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            Date dateIn = new SimpleDateFormat("dd/mm/yy")
                                    .parse(etDateFrom.getEditText().getText().toString());
                            Date dateOut = new SimpleDateFormat("dd/mm/yy")
                                    .parse(etDateTo.getEditText().getText().toString());
                            listener.passDate(dateIn,dateOut);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
        etDateFrom = view.findViewById(R.id.etDateFrom);
        etDateTo = view.findViewById(R.id.etDateTo);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (BookingDialogListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface BookingDialogListener{
        void passDate(Date dateIn, Date dateOut);
    }
}
