package com.example.hotel.view.auth;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.hotel.R;
import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.databinding.FragmentRegisterBinding;
import com.example.hotel.model.User;

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
        user.setUser_profile_url("https://images.pexels.com/photos/1250426/pexels-photo-1250426.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=400");

        binding.setUser(user);

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
                passwordConfirm = binding.getPasswordConfirm();
                System.out.println("Pssword confirm is: "+passwordConfirm);
                System.out.println("UserPass confirm is: "+user.getPassword());
                if(isEmptyField()){
//                    Toast.makeText(binding.getRoot().getContext(), "Opps, there are some empty field!", Toast.LENGTH_SHORT).show();
                }
                else if(!isUsernameAvailable()){
                    binding.etUsername.setError("Username Already Taken");
                }else if(!isEmailAvailable()){
                    binding.etEmail.setError("Email Already Taken");
                }else if(isEmptyField()){
                    Toast.makeText(binding.getRoot().getContext(), "Opps, there are some empty field!", Toast.LENGTH_SHORT).show();
                }else if(!isPasswordMatch()){
                    binding.etConfirmPassword.setError("Password didnt match");
                }
                else
                {
                    insertUser(user);
                    Navigation.findNavController(view).navigateUp();
                }
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

    //inputan tidak boleh kosong
    private boolean isEmptyField(){
        if(user.getUsername()==null){
            binding.etUsername.setError("Must be Filled");
        }
        if(user.getEmail()==null){
            binding.etEmail.setError("Must be Filled");
        }
        if(user.getPassword()==null){
            binding.etPassword.setError("Must be Filled");
        }
        if(passwordConfirm ==null){
            binding.etConfirmPassword.setError("Must be Filled");
        }
        return user.getUsername()==null||user.getEmail()==null||user.getPassword()==null||passwordConfirm==null;
    }

    // cek apakah username tersedia
    private boolean isUsernameAvailable(){
       return MyDatabaseClient.getInstance(binding.getRoot().getContext())
                .getDatabase()
                .userDao()
                .getUser(user.getUsername()) == null;
    }
    // cek apakah email tersedia
    private boolean isEmailAvailable(){
        return MyDatabaseClient.getInstance(binding.getRoot().getContext())
                .getDatabase()
                .userDao()
                .getUserByEmail(user.getEmail()) == null;
    }
    // cek apakah password confirmation sesuai
    private boolean isPasswordMatch(){
        return user.getPassword().equals(passwordConfirm);
    }
}