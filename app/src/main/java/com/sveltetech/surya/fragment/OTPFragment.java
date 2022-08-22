package com.sveltetech.surya.fragment;

import static com.sveltetech.surya.app.AppConfig.PAYLOAD_BUNDLE;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.sveltetech.surya.R;
import com.sveltetech.surya.app.PreferencesManager;
import com.sveltetech.surya.constants.BaseFragment;
import com.sveltetech.surya.databinding.FragmentOtpverifyBinding;

public class OTPFragment extends BaseFragment {
    FragmentOtpverifyBinding binding;
    String mobile="",otp="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentOtpverifyBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                Navigation.findNavController(v).navigate(R.id.action_OTPFragment_to_profileFragment);
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
