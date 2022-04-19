package com.khalidsyfullah.boimela.ui.auth;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.Repo.AuthRepo;
import com.khalidsyfullah.boimela.Repo.RemoteRequestInterface;
import com.khalidsyfullah.boimela.datamodel.UserDataModel;
import com.khalidsyfullah.boimela.global.StaticData;

public class ResetPasswordFragment extends Fragment {


    private TextView resetBtn;
    private EditText phoneText, passwordText, confirmPasswordText;
    private ImageView passwordVisibility, confirmPasswordVisibility;
    private AuthRepo authRepo;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_reset_password, container, false);

        phoneText = root.findViewById(R.id.reset_password_phone_edit_text);
        passwordText = root.findViewById(R.id.reset_password_password_edit_text);
        confirmPasswordText = root.findViewById(R.id.reset_password_confirm_password_edit_text);
        passwordVisibility = root.findViewById(R.id.reset_password_password_visibility);
        confirmPasswordVisibility = root.findViewById(R.id.reset_password_confirm_password_visibility);
        resetBtn = root.findViewById(R.id.reset_password_btn);

        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("bn");
        authRepo = new AuthRepo();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();

        if(bundle.getString("phone") != null){

            phoneText.setText(bundle.getString("phone"));
        }


        passwordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordText.getTransformationMethod() == null) {
                    passwordVisibility.setImageResource(R.drawable.ic_visibility_off);
                    passwordText.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    passwordVisibility.setImageResource(R.drawable.ic_visibility_on);
                    passwordText.setTransformationMethod(null);
                }
            }
        });

        confirmPasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (confirmPasswordText.getTransformationMethod() == null) {
                    confirmPasswordVisibility.setImageResource(R.drawable.ic_visibility_off);
                    confirmPasswordText.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    confirmPasswordVisibility.setImageResource(R.drawable.ic_visibility_on);
                    confirmPasswordText.setTransformationMethod(null);
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignUp();
            }
        });



    }




    private void verifySignUp() {
        String phone = "", password = "";




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
        } else if (!passwordText.getText().toString().equals(confirmPasswordText.getText().toString())) {
            passwordText.setError("Password does not match!");
            confirmPasswordText.requestFocus();
        } else if (passwordText.getText().length() < 6) {
            passwordText.setError("Password should be at least 6 characters!");
            passwordText.requestFocus();
        } else {
            password = passwordText.getText().toString();

        }

        if (!phone.isEmpty() && !password.isEmpty()) {

            UserDataModel userDataModel = new UserDataModel("+88"+phone, password);

            AuthCredential credential = EmailAuthProvider.getCredential(phone+"@localhost.com", password);

            mAuth.getCurrentUser().linkWithCredential(credential)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = task.getResult().getUser();
                                Log.d("LinkWithCredential", "linkWithCredential:success");
                                Toast.makeText(getActivity(), "Authentication successful.", Toast.LENGTH_SHORT).show();

                                resetPasswordCall(userDataModel);


                            }

                            else {
                                Log.w("LinkWithCredential", "linkWithCredential:failure", task.getException());
                                Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });




        }

    }

    private void resetPasswordCall(UserDataModel userDataModel){

        authRepo.resetPassword(userDataModel, new RemoteRequestInterface() {
            @Override
            public void onSuccess(String msg) {
                StaticData.successAlertDialog(getActivity(),"Reset Successful: "+msg);
            }

            @Override
            public void onFailure(String msg) {
                StaticData.failureAlertDialog(getActivity(),"Reset Failed: "+msg);

            }
        });
    }
}
