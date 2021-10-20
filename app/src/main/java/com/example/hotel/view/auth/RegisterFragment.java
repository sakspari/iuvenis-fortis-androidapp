package com.example.hotel.view.auth;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.R;
import com.example.hotel.databinding.FragmentRegisterBinding;
import com.example.hotel.model.User;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    User user;
    String passwordConfirm;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);

        getActivity().setTitle("Register");

        user = new User();
        user.setUser_status(true);
        user.setUser_profile_url("#");

        passwordConfirm = "";

        binding.setUser(user);
        binding.setPasswordConfirm(passwordConfirm);

        // action untuk btnCancel (pindah ke fragment Login)
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        // action untuk btnSignUp (insert user dan kembali ke fragment login)
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isUsernameAvailable()){
                    Toast.makeText(binding.getRoot().getContext(), "Useranme sudah digunakan", Toast.LENGTH_SHORT).show();
                }else if(!isPasswordMatch()){
                    Toast.makeText(binding.getRoot().getContext(), "Password Confirm tidak sesuai", Toast.LENGTH_SHORT).show();
                }else
                {
                    insertUser(user);
                    Navigation.findNavController(view).navigateUp();
                }
//                    insertUser(user);
                getAllUser();
//                Navigation.findNavController(view).navigateUp();
            }
        });



        return binding.getRoot();
    }

    public void insertUser(User user) {
        class InsertUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                MyDatabaseClient.getInstance(binding.getRoot().getContext())
                        .getDatabase()
                        .userDao()
                        .insertUser(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(binding.getRoot().getContext(), "Register Success!", Toast.LENGTH_SHORT).show();
            }
        }
        InsertUser insertUser = new InsertUser();
        insertUser.execute();
    }

    public void getAllUser() {
        class GetUser extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = MyDatabaseClient.getInstance(binding.getRoot().getContext())
                        .getDatabase()
                        .userDao()
                        .getAllUser();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                //ganti bagian ini sesuai kebutuhan
                //jangan bandingkan dengan null object bagian if-nya
                if (users.size()!=0) {
                    for (int i = 0; i < users.size(); i++) {
                        System.out.println(users.get(i).getUsername());
                    }
                }
            }
        }
        GetUser getUser = new GetUser();
        getUser.execute();
    }

    // cek apakah username tersedia
    private boolean isUsernameAvailable(){
       return MyDatabaseClient.getInstance(binding.getRoot().getContext())
                .getDatabase()
                .userDao()
                .getUser(user.getUsername()) == null;
    }
    // cek apakah password confirmation sesuai
    private boolean isPasswordMatch(){
        return user.getPassword().equalsIgnoreCase(passwordConfirm);
    }

    //TODO: email harus unique
    //TODO: inputan tidak boleh kosong

}