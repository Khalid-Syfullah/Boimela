package com.khalidsyfullah.boimela.ui.auth;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.khalidsyfullah.boimela.R;

import java.util.concurrent.TimeUnit;

public class PhoneVerificationFragment extends Fragment {

    private String verificationId, phonenumber="";

    private TextView getOtpBtn, verifyOtpBtn, otpTitleText, otpTimerText;
    private EditText phoneText, otpText1, otpText2, otpText3, otpText4, otpText5, otpText6;
    private CardView getOtpCardView, verifyOtpCardView;
    private FirebaseAuth mAuth;
    private boolean isVerificationCompleted = false,isTimerOn=false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_phone_verification, container, false);

        phoneText = root.findViewById(R.id.phone_verification_editTextPhone2);
        otpTitleText = root.findViewById(R.id.phone_verification_subtitle);
        otpTimerText = root.findViewById(R.id.phone_verification_2_otp_title2);
        otpText1 = root.findViewById(R.id.phone_verification_2_editTextPhone);
        otpText2 = root.findViewById(R.id.phone_verification_2_editTextPhone2);
        otpText3 = root.findViewById(R.id.phone_verification_2_editTextPhone3);
        otpText4 = root.findViewById(R.id.phone_verification_2_editTextPhone4);
        otpText5 = root.findViewById(R.id.phone_verification_2_editTextPhone5);
        otpText6 = root.findViewById(R.id.phone_verification_2_editTextPhone6);

        getOtpCardView = root.findViewById(R.id.phone_verification_getotp_cardview);
        verifyOtpCardView = root.findViewById(R.id.phone_verification_verifyotp_cardview);

        getOtpBtn = root.findViewById(R.id.phone_verification_btn);
        verifyOtpBtn = root.findViewById(R.id.phone_verification_2_btn);


        verifyOtpCardView.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("bn");


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        otpText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otpText1.getText().toString().length()==1)
                {
                    otpText2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otpText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otpText2.getText().toString().length()==1)     //size as per your requirement
                {
                    otpText3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otpText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otpText3.getText().toString().length()==1)     //size as per your requirement
                {
                    otpText4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otpText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otpText4.getText().toString().length()==1)     //size as per your requirement
                {
                    otpText5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otpText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otpText5.getText().toString().length()==1)     //size as per your requirement
                {
                    otpText6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otpText6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otpText6.getText().toString().length()==1)     //size as per your requirement
                {
                    otpText1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phoneText.getText().toString().isEmpty()) {
                    phoneText.setError("Phone Required");
                    phoneText.requestFocus();
                } else if (phoneText.getText().toString().length() < 11) {
                    phoneText.setError("Phone No must be 11 digits");
                    phoneText.requestFocus();
                } else {
                    phonenumber = phoneText.getText().toString();

                    sendVerificationCode("+88" + phonenumber);

                }

            }
        });

        verifyOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpText1.getText().toString().isEmpty()){
                    otpText1.setError("OTP required");
                    otpText1.requestFocus();
                }
                else if(otpText2.getText().toString().isEmpty()){
                    otpText2.setError("OTP required");
                    otpText2.requestFocus();
                }
                else if(otpText3.getText().toString().isEmpty()){
                    otpText3.setError("OTP required");
                    otpText3.requestFocus();
                }
                else if(otpText4.getText().toString().isEmpty()){
                    otpText4.setError("OTP required");
                    otpText4.requestFocus();
                }
                else if(otpText5.getText().toString().isEmpty()){
                    otpText5.setError("OTP required");
                    otpText5.requestFocus();
                }
                else if(otpText6.getText().toString().isEmpty()){
                    otpText6.setError("OTP required");
                    otpText6.requestFocus();
                }
                else {

                    verifyCodeAndSignIn(otpText1.getText().toString()+otpText2.getText().toString()+otpText3.getText().toString()
                    +otpText4.getText().toString()+otpText5.getText().toString()+otpText6.getText().toString());
                }
            }
        });
    }

    public void sendVerificationCode(String phoneNumber) {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(getActivity())
                .setCallbacks(mCallBack)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }


    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationId = s;

            otpTitleText.setText(getResources().getString(R.string.otp_via_sms));
            getOtpCardView.setVisibility(View.GONE);
            verifyOtpCardView.setVisibility(View.VISIBLE);

            new CountDownTimer(60000, 1000) {

                public void onTick(long millisUntilFinished) {
                    isTimerOn=true;

                    if(millisUntilFinished / 1000 > 9) {
                        otpTimerText.setText("00:" + millisUntilFinished / 1000);
                    }
                    else{
                        otpTimerText.setText("00:0" + millisUntilFinished / 1000);

                    }
                }

                public void onFinish() {


                }

            }.start();

        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
                otpText1.setText(code.charAt(0));
                otpText2.setText(code.charAt(1));
                otpText3.setText(code.charAt(2));
                otpText4.setText(code.charAt(3));
                otpText5.setText(code.charAt(4));
                otpText6.setText(code.charAt(5));

            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getActivity(), "Auto Phone Verification Failed!",Toast.LENGTH_SHORT).show();
        }
    };


    private void verifyCodeAndSignIn(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            Toast.makeText(getActivity(), "Phone Verification Successful!",Toast.LENGTH_SHORT).show();

                            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_auth).navigateUp();
                            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_auth).navigateUp();


                        } else {
                            Toast.makeText(getActivity(), "Phone Verification Failed!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }



}
