package com.sveltetech.surya.settings.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sveltetech.surya.constants.BaseFragment;
import com.sveltetech.surya.databinding.FragmentViewprofileBinding;

public class ViewProfileFragment extends BaseFragment {

    FragmentViewprofileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentViewprofileBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }
}
