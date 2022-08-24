package com.sveltetech.surya.settings.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sveltetech.surya.constants.BaseFragment;
import com.sveltetech.surya.databinding.FragmentDeleteMyAccountBinding;


public class DeleteMyAccountFragment extends BaseFragment {

    FragmentDeleteMyAccountBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentDeleteMyAccountBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
