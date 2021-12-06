package com.example.hotel.adapter;

import static com.android.volley.Request.Method.GET;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotel.R;
import com.example.hotel.api.RoomApi;
import com.example.hotel.databinding.RvBookDetailBinding;
import com.example.hotel.model.BookDetail;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.HotelRoomResponse;
import com.example.hotel.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RVBookingAdapter extends RecyclerView.Adapter<RVBookingAdapter.viewHolder> {

    private List<BookDetail> bookDetailList;
    private OnBookingClickListener listener;
    RvBookDetailBinding binding;
    private User user;
    private HotelRoom hotelRoom;
    private RequestQueue queue;


    public RVBookingAdapter(List<BookDetail> bookDetailList, OnBookingClickListener listener) {
        this.bookDetailList = bookDetailList;
        this.listener = listener;
    }

    public List<BookDetail> getBookDetailList() {
        return bookDetailList;
    }

    public void setBookDetailList(List<BookDetail> bookDetailList) {
        this.bookDetailList = bookDetailList;
    }

    public OnBookingClickListener getListener() {
        return listener;
    }

    public void setListener(OnBookingClickListener listener) {
        this.listener = listener;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setHotelRoom(HotelRoom hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    @NonNull
    @Override
    public RVBookingAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_book_detail, parent, false);

        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RVBookingAdapter.viewHolder holder, int position) {
        BookDetail bookDetail = bookDetailList.get(position);
        holder.rvBookDetailBinding.setBookingDetail(bookDetail);
        holder.rvBookDetailBinding.setUser(user);
        holder.rvBookDetailBinding.setHotelRoom(hotelRoom);


//        holder.rvBookDetailBinding.setHotelRoom(MyDatabaseClient.getInstance(binding.getRoot().getContext()).getDatabase().hotelDao().roomFromId(bookDetail.getFk_room_id()));

        holder.rvBookDetailBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return bookDetailList.size();
    };

    public class viewHolder extends RecyclerView.ViewHolder {
        RvBookDetailBinding rvBookDetailBinding;

        public viewHolder(RvBookDetailBinding rvBookDetailBinding) {
            super(rvBookDetailBinding.getRoot());
            this.rvBookDetailBinding = rvBookDetailBinding;

            rvBookDetailBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(rvBookDetailBinding.getBookingDetail());
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("id_booking",rvBookDetailBinding.getBookingDetail().getBook_detail_id());
//
//                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_urBookedRoom_to_detailBooking,bundle);
                }

            });

            rvBookDetailBinding.deleteBooking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteButtonClick(rvBookDetailBinding.getBookingDetail());
                }
            });
        }

    }

//    private void getHotelRoom(int fk_room_id) {
//
////            srHotelRoom.setRefreshing(true);
//            StringRequest stringRequest = new StringRequest(GET, RoomApi.GET_BY_ID_URL+fk_room_id, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Gson gson = new Gson();
//                    HotelRoomResponse hotelRoomResponse =
//                            gson.fromJson(response, HotelRoomResponse.class);
//
//                    binding.setHotelRoom(hotelRoomResponse.getHotelRoomList().get(0));
//
//                    Log.i("BOOKRV",hotelRoomResponse.getMessage());
////                    recyclerView.setAdapter(adapter);
////                    srHotelRoom.setRefreshing(false);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
////                    srHotelRoom.setRefreshing(false);
//                    try {
//                        String responseBody = new String(error.networkResponse.data,
//                                StandardCharsets.UTF_8);
//                        JSONObject errors = new JSONObject(responseBody);
//                        Log.i("BOOKRV",errors.getString("message"));
//                    } catch (JSONException e) {
//                        Log.i("BOOKRV",e.getMessage());
//
//                    }
//                }
//            }){
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    HashMap<String, String> headers = new HashMap<String, String>();
//                    headers.put("Accept", "application/json");
//                    return headers;
//                }
//            };
//            //add to queue req
//            queue.add(stringRequest);
//
//    }


}
