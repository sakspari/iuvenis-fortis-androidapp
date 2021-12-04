package com.example.hotel.view.home;

import static com.android.volley.Request.Method.GET;
import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.hotel.R;
import com.example.hotel.api.UserApi;
import com.example.hotel.databinding.FragmentProfilBinding;
import com.example.hotel.model.User;
import com.example.hotel.model.UserResponse;
import com.example.hotel.preferences.UserLoginPreferences;
import com.example.hotel.view.auth.AuthActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment {
    private FragmentProfilBinding binding;
    private UserLoginPreferences userLoginPreferences;
    private User user;
    FirebaseAuth mAuth;
    private String foto;
    private RequestQueue queue;
    private LinearLayout layoutLoading;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilFragment newInstance(String param1, String param2) {
        ProfilFragment fragment = new ProfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profil, container, false);

        mAuth = FirebaseAuth.getInstance();
        queue = Volley.newRequestQueue(getContext());
//        layoutLoading = binding.getRoot().findViewById(R.id.layout_loading);
        userLoginPreferences = new UserLoginPreferences(binding.getRoot().getContext());

//        user = userLoginPreferences.getUserLogin();

        getUser();
//        binding.setUser(user);


        //Set Logout Action
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                userLoginPreferences.logout();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                startActivity(new Intent(binding.getRoot().getContext(), AuthActivity.class));
                Toast.makeText(binding.getRoot().getContext(), "Sayonara!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        return binding.getRoot();
    }

    private void getUser() {


//        setLoading(true);

        FirebaseUser fUser = mAuth.getCurrentUser();

        StringRequest stringRequest = new StringRequest(GET,
                UserApi.GET_BY_EMAIL_URL + fUser.getEmail(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                UserResponse userResponse =
                        gson.fromJson(response, UserResponse.class);
                user = userResponse.getUserList().get(0);
                foto = user.getProfile_picture();
                byte[] imageByteArray = Base64.decode(foto, Base64.DEFAULT);
//                etNama.setText(user.getNama());
//                etStok.setText(Integer.toString(user.getStok()));
//                etDeskripsi.setText(user.getDeskripsi());

                Glide.with(binding.getRoot().getContext())
                        .load(user.getProfile_picture())
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(binding.profileImg);

                binding.setUser(user);
//                etHarga.setText(Integer.toString(user.getHarga()));
//                Toast.makeText(AddEditActivity.this,
//                        produkResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                setLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(binding.getRoot().getContext(), errors.getString("message"),
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(binding.getRoot().getContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            // Menambahkan header pada request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);

    }

//    private void setLoading(boolean isLoading) {
//        if (isLoading) {
//            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            layoutLoading.setVisibility(View.VISIBLE);
//        } else {
//            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            layoutLoading.setVisibility(View.GONE);
//        }
//    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_baseline_person_24)
                .into(view);

    }

}