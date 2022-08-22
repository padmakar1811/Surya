package com.sveltetech.surya.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.sveltetech.surya.R;
import com.sveltetech.surya.app.PreferencesManager;
import com.sveltetech.surya.constants.BaseActivity;
import com.sveltetech.surya.databinding.ActivityForgotPinBinding;

public class ForgotPinActivity extends BaseActivity {
    ActivityForgotPinBinding binding;
    String mobile="",otp="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityForgotPinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvOtptitletext.setText("We will verify the OTP sent\nto "+ PreferencesManager.getInstance(context).getContact());
        otpCountDownTimer();
        binding.tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpCountDownTimer();
            }
        });

        binding.btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToActivityWithFinish(context,CreatePinActivity.class,null);
            }
        });
    }
    /*OTP Countdowntimer*/
    private void otpCountDownTimer() {
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.tvResend.setVisibility(View.GONE);
                binding.tvOtptext.setClickable(false);
                binding.tvOtptext.setText("Resend OTP in "+millisUntilFinished / 1000 + " seconds");
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                binding.tvOtptext.setClickable(false);
                binding.tvOtptext.setText("Didn't receive the OTP ? ");
                binding.tvResend.setText("Click to resend");
                binding.tvResend.setVisibility(View.VISIBLE);
            }

        }.start();
    }
}