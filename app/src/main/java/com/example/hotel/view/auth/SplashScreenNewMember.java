package com.example.hotel.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hotel.MainActivity;
import com.example.hotel.R;
import com.example.hotel.databinding.FragmentSplashScreenNewMemberBinding;
import com.example.hotel.model.User;
import com.example.hotel.preferences.UserLoginPreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SplashScreenNewMember#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashScreenNewMember extends Fragment {
    FragmentSplashScreenNewMemberBinding binding;
    UserLoginPreferences userLoginPreferences;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SplashScreenNewMember() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SplashScreenNewMember.
     */
    // TODO: Rename and change types and number of parameters
    public static SplashScreenNewMember newInstance(String param1, String param2) {
        SplashScreenNewMember fragment = new SplashScreenNewMember();
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_splash_screen_new_member, container, false);

        userLoginPreferences = new UserLoginPreferences(binding.getRoot().getContext());
//        user.setUser_status(false);

        binding.setUrlImage("https://cdn.pixabay.com/photo/2015/09/28/21/32/the-palm-962785_640.jpg");

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update user status di database
//                MyDatabaseClient.getInstance(binding.getRoot().getContext())
//                        .getDatabase()
//                        .userDao()
//                        .updateUser(user);
                // pindah ke activity Main
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return binding.getRoot();
    }
}