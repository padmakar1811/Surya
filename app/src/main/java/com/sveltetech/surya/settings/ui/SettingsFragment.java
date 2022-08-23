package com.sveltetech.surya.settings.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sveltetech.surya.R;
import com.sveltetech.surya.common.LoggerUtil;
import com.sveltetech.surya.constants.BaseFragment;
import com.sveltetech.surya.databinding.FragmentMainBinding;
import com.sveltetech.surya.databinding.FragmentSettingsBinding;
import com.sveltetech.surya.settings.adapter.IconTitleSubtitleAdapter;
import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends BaseFragment {

    static final String ARG_TITLE = "title";
    static final String ARG_TITLES = "titles";
    static final String ARG_SUBTITLES = "subtitles";
    static final String ARG_ICONS = "icons";
    static final String ARG_ACTIONS = "actions";
    private FragmentSettingsBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        List<Integer> titles = new ArrayList<>();
        List<Integer> subtitles = new ArrayList<>();
        List<Integer> icons = new ArrayList<>();

        titles.add(R.string.account);
        titles.add(R.string.chats);
        titles.add(R.string.help);

        subtitles.add(R.string.privacy_security_change_number);
        subtitles.add(R.string.theme_wallpapers_chat_history);
        subtitles.add(R.string.faq_contact_us_privacy_policy);

        icons.add(R.drawable.ic_account);
        icons.add(R.drawable.ic_chat);
        icons.add(R.drawable.ic_help);

        IconTitleSubtitleAdapter adapter = new IconTitleSubtitleAdapter(titles, subtitles, icons);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));

       // LoggerUtil.logItem(titles.toString() +" - "+subtitles.toString()+" - "+ icon.to);
        binding.recyclerView.setAdapter(adapter);

        adapter.setItemActionListener(title -> {
            switch (title) {
                case R.string.account:
                    break;
                case R.string.chats:
                  //  Navigation.findNavController(requireView()).navigate(R.id.action_settingsFragment_to_recycleViewFragment);
                    break;
                case R.string.help:
                    break;
            }
        });

      /*  binding.profileName.setOnClickListener(v ->
              //  Navigation.findNavController(requireView()).navigate(R.id.action_settingsFragment_to_profileFragment));
        binding.profileStatus.setOnClickListener(v ->
              //  Navigation.findNavController(requireView()).navigate(R.id.action_settingsFragment_to_profileFragment));
        binding.profilePicture.setOnClickListener(v ->
             //   Navigation.findNavController(requireView()).navigate(R.id.action_settingsFragment_to_profileFragment));*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        binding = null;
    }
}