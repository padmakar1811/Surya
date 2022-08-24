package com.sveltetech.surya.settings.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.sveltetech.surya.R;
import com.sveltetech.surya.databinding.FragmentRecyclerviewBinding;
import com.sveltetech.surya.settings.model.SettingModel;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewFragment extends Fragment {

    static final String ARG_TITLE = "title";
    static final String ARG_TITLES = "titles";
    static final String ARG_SUBTITLES = "subtitles";
    static final String ARG_ICONS = "icons";
    static final String ARG_ACTIONS = "actions";
    private FragmentRecyclerviewBinding binding;
    private String title;
    private ArrayList<SettingModel> titles=new ArrayList<>();
    private List<Integer> subtitles;
    private List<Integer> icons;
    private List<Integer> actions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecyclerviewBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rlPrivacy.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.action_recycleViewFragment_to_privacyFragment));
        binding.rlDeleteMyAccount.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.action_recycleViewFragment_to_deletemyaccountFragment));

    }

    @Override
    public void onDetach() {
        super.onDetach();
        binding = null;
    }
}