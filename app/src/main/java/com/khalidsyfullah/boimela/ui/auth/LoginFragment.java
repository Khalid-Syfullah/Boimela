package com.khalidsyfullah.boimela.ui.auth;

import static android.content.Context.MODE_PRIVATE;

import static com.khalidsyfullah.boimela.global.StaticData.LOGIN_SHARED_PREFS;
import static com.khalidsyfullah.boimela.global.StaticData.LOGIN_USER_PASS;
import static com.khalidsyfullah.boimela.global.StaticData.LOGIN_USER_PHONE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.Repo.AuthRepo;
import com.khalidsyfullah.boimela.Repo.RemoteRequestInterface;
import com.khalidsyfullah.boimela.datamodel.LoginDataModel;
import com.khalidsyfullah.boimela.datamodel.ResponseDataModel;
import com.khalidsyfullah.boimela.datamodel.UserDataModel;
import com.khalidsyfullah.boimela.global.StaticData;
import com.khalidsyfullah.boimela.ui.navigation.NavigationActivity;


public class LoginFragment extends Fragment {



    private TextView loginBtn, signupBtn, resetBtn;
    private EditText phoneText, passwordText;
    private ImageView passVisibility;
    private AuthRepo authRepo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        phoneText = root.findViewById(R.id.phone_edit_text);
        passwordText = root.findViewById(R.id.password_edit_text);
        passVisibility = root.findViewById(R.id.login_password_visibility);
        loginBtn = root.findViewById(R.id.login_btn);
        signupBtn = root.findViewById(R.id.login_subtitle5);
        resetBtn = root.findViewById(R.id.login_subtitle3);

        authRepo = new AuthRepo();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        passVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordText.getTransformationMethod() == null) {
                    passVisibility.setImageResource(R.drawable.ic_visibility_off);
                    passwordText.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    passVisibility.setImageResource(R.drawable.ic_visibility_on);
                    passwordText.setTransformationMethod(null);
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyLogin();

            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("task","login");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_auth).navigate(R.id.action_nav_login_to_phoneVerificationAFragment, bundle);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("task","reset");
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_auth).navigate(R.id.action_nav_login_to_phoneVerificationAFragment, bundle);


            }
        });
    }



    private void verifyLogin() {
        String phone = "";
        String password = "";


        if (phoneText.getText().toString().isEmpty()) {
            phoneText.setError("Phone Required");
            phoneText.requestFocus();
        } else if (phoneText.getText().toString().length() < 11) {
            phoneText.setError("Phone No must be 11 digits");
            phoneText.requestFocus();
        } else {
            phone = phoneText.getText().toString();
        }

        if (passwordText.getText().toString().isEmpty()) {
            passwordText.setError("Password Required");
            passwordText.requestFocus();
        }  else if (passwordText.getText().length() < 6) {
            passwordText.setError("Password should be at least 6 characters!");
            passwordText.requestFocus();
        } else {
            password = passwordText.getText().toString();

        }

        if (!phone.isEmpty() && !password.isEmpty()) {

            UserDataModel userDataModel = new UserDataModel("+88"+phone, password);


            String finalPhone = phone;
            String finalPassword = password;

            authRepo.loginUser(userDataModel, new RemoteRequestInterface() {
                @Override
                public void onSuccess(String msg) {

                    Log.d("Cookie","Saved Cookie: "+StaticData.cookie.getValue());


                    authRepo.refreshToken(StaticData.cookie.getValue(), new RemoteRequestInterface() {
                        @Override
                        public void onSuccess(String msg) {

                            SharedPreferences loginSharedPrefs = getActivity().getSharedPreferences(LOGIN_SHARED_PREFS, MODE_PRIVATE);
                            SharedPreferences.Editor loginPrefsEditor = loginSharedPrefs.edit();

                            loginPrefsEditor.putString(LOGIN_USER_PHONE, finalPhone);
                            loginPrefsEditor.putString(LOGIN_USER_PASS, finalPassword);
                            loginPrefsEditor.apply();

                            authRepo.userProfile(StaticData.accessToken.getValue(), new RemoteRequestInterface() {
                                @Override
                                public void onSuccess(String msg) {
                                    StaticData.successAlertDialog(getActivity(),"Login Successful: "+msg);

                                    getActivity().finishAffinity();
                                    Intent intent = new Intent(getActivity(), NavigationActivity.class);
                                    getActivity().startActivity(intent);


                                }

                                @Override
                                public void onFailure(String msg) {
                                    StaticData.failureAlertDialog(getActivity(),"Login Failed: "+msg);

                                }
                            });


                        }

                        @Override
                        public void onFailure(String msg) {
                            Log.d("LoginFragment","Login Failed: "+msg);
                            StaticData.failureAlertDialog(getActivity(),"Login Failed: "+msg);

                        }
                    });
                }

                @Override
                public void onFailure(String msg) {
                    Log.d("LoginFragment","Login Failed: "+msg);
                    StaticData.failureAlertDialog(getActivity(),"Connection failed: "+msg);

                }
            });

        }

    }

}