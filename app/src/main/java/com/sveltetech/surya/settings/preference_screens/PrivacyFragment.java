package com.sveltetech.surya.settings.preference_screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import com.sveltetech.surya.R;
import com.sveltetech.surya.constants.BaseFragment;
import com.sveltetech.surya.databinding.FragmentPrivacyBinding;


public class PrivacyFragment extends BaseFragment {

    FragmentPrivacyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentPrivacyBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
