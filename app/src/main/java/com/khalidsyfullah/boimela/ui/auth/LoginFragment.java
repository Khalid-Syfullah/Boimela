package com.khalidsyfullah.boimela.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.khalidsyfullah.boimela.R;


public class LoginFragment extends Fragment {



    TextView loginBtn, signupBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        loginBtn = root.findViewById(R.id.login_btn);
        signupBtn = root.findViewById(R.id.login_subtitle5);



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_auth).navigate(R.id.action_nav_login_to_phoneVerificationAFragment);
            }
        });
    }
}