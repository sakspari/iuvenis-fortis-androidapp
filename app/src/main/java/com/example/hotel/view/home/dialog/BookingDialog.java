package com.example.hotel.view.home.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;

import com.example.hotel.R;
import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.databinding.BookingDialogBinding;
import com.example.hotel.model.BookDetail;
import com.example.hotel.model.User;
import com.example.hotel.preferences.UserLoginPreferences;
import com.example.hotel.view.home.DetailBooking;
import com.example.hotel.view.home.DetailRoom;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookingDialog extends AppCompatDialogFragment {

    BookingDialogBinding binding;

    private TextInputLayout etDateFrom;
    private TextInputLayout etDateTo;
    private String room_id;
    private BookingDialogListener listener;

    public BookingDialog(String room_id) {
        this.room_id = room_id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        binding = DataBindingUtil.inflate(inflater,R.layout.booking_dialog, null,false);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yy", Locale.US);

        builder.setView(binding.getRoot())
                .setTitle("Pick Booking Time")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Booking", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BookDetail bookDetail = new BookDetail();
                        User user = new UserLoginPreferences(getContext()).getUserLogin();
                        bookDetail.setFk_room_id(room_id);
                        bookDetail.setFk_username(user.getUsername());
                        try {
                            bookDetail.setCheck_in_date(dateFormatter.parse(binding.tglMasuk.getText().toString()));
                            bookDetail.setCheck_out_date(dateFormatter.parse(binding.tglKeluar.getText().toString()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        insertBooking(bookDetail);

//                        MyDatabaseClient.getInstance(getContext())
//                                .getDatabase()
//                                .bookingDao()
//                                .insertBooking(bookDetail);

                        listener.passDate(binding.tglMasuk.getText().toString(), binding.tglKeluar.getText().toString());
                    }
                });
//        etDateFrom = view.findViewById(R.id.etDateFrom);
//        etDateTo = view.findViewById(R.id.etDateTo);

//        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yy", Locale.US);

        binding.tglMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(binding.getRoot().getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year,month,day);

                        binding.tglMasuk.setText(dateFormatter.format(newDate.getTime()));
                    }
                },newCalendar.get(Calendar.YEAR),newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        binding.tglKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(binding.getRoot().getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year,month,day);

                        binding.tglKeluar.setText(dateFormatter.format(newDate.getTime()));
                    }
                },newCalendar.get(Calendar.YEAR),newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (BookingDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implements interfaces");
        }
    }

    public void insertBooking(BookDetail bookDetail) {
        class InsertBooking extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                MyDatabaseClient.getInstance(getContext())
                        .getDatabase()
                        .bookingDao()
                        .insertBooking(bookDetail);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getContext(), "Bookings insert Succecssfully", Toast.LENGTH_SHORT).show();
            }
        }
        InsertBooking insertBooking = new InsertBooking();
        insertBooking.execute();
    }

    public interface BookingDialogListener{
        void passDate(String dateIn, String dateOut);
    }
}
