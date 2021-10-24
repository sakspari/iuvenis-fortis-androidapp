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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.navigation.Navigation;

import com.example.hotel.R;
import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.model.BookDetail;
import com.example.hotel.model.RoomReview;

import java.util.Date;

public class ReviewDialog extends AppCompatDialogFragment {
    private int booking_id;

    public ReviewDialog(int booking_id) {
        this.booking_id = booking_id;
    }

    private EditText inputReview;
    private Button btnSubmit;
//    private ReviewDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.review_dialog, null);

        inputReview = view.findViewById(R.id.edit_text);
        btnSubmit = view.findViewById(R.id.btn_submit);

        //get Booking Detail
        BookDetail bookDetail = MyDatabaseClient.getInstance(getContext())
                .getDatabase()
                .bookingDao()
                .bookingById(booking_id);

        // set room review
        RoomReview roomReview = new RoomReview();
        roomReview.setFk_room_id(bookDetail.getFk_room_id());
        roomReview.setFk_username(bookDetail.getFk_username());
        roomReview.setReview_date(new Date());

        //btnSubmit Action
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reviewDesc = inputReview.getText().toString();
                roomReview.setReview_description(reviewDesc);
//                Navigation.findNavController(getActivity(),R.id.home_navigation).navigateUp();
            }
        });

        //dialog builder
        builder.setView(view)
                .setTitle("Review Room")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String reviewDesc = inputReview.getText().toString();
                        roomReview.setReview_description(reviewDesc);

                        RoomReview old = MyDatabaseClient.getInstance(getContext())
                                .getDatabase()
                                .reviewDao()
                                .getRoomReview(bookDetail.getFk_username(), bookDetail.getFk_room_id());

                        if (old == null) {
                            MyDatabaseClient.getInstance(getContext())
                                    .getDatabase()
                                    .reviewDao()
                                    .insertReview(roomReview);
                            Toast.makeText(getContext(), "Review berhasil!", Toast.LENGTH_SHORT).show();
                        }else{
                            roomReview.setReview_id(old.getReview_id());
                            MyDatabaseClient.getInstance(getContext())
                                    .getDatabase()
                                    .reviewDao()
                                    .updateReview(roomReview);
                            Toast.makeText(getContext(), "Review diupdate!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return builder.create();
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        try {
//            listener = (ReviewDialog.ReviewDialogListener) context;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public interface ReviewDialogListener{
//        void passReview(String review);
//    }
}
