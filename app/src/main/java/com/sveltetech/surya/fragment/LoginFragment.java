package com.sveltetech.surya.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.rilixtech.Country;
import com.rilixtech.CountryCodePicker;
import com.sveltetech.surya.R;
import com.sveltetech.surya.app.PreferencesManager;
import com.sveltetech.surya.common.NetworkUtils;
import com.sveltetech.surya.constants.BaseFragment;
import com.sveltetech.surya.databinding.FragmentLoginBinding;

import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.Phonenumber;

public class LoginFragment extends BaseFragment {

    FragmentLoginBinding binding;
    String usermobile="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkUtils.getConnectivityStatus(context)!=0){
                    if(validation()){
                        PreferencesManager.getInstance(context).setContact(usermobile);
                        Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_verifyNoFragment);
                    }
                }
                else{
                    showMessage(getResources().getString(R.string.alert_internet));
                }
            }
        });

        binding.countrycode.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                Toast.makeText(getContext(), "Updated " + selectedCountry.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validation() {
        usermobile=binding.editMobile.getText().toString().trim();
        if(TextUtils.isEmpty(usermobile)) {
           showMessage("Enter valid Mobile Number");
           return false;
        }
        return true;
    }

}
