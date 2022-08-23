package com.sveltetech.surya.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sveltetech.surya.R;
import com.sveltetech.surya.chats.ChatsFragment;
import com.sveltetech.surya.constants.BaseFragment;
import com.sveltetech.surya.databinding.FragmentMainBinding;
import com.sveltetech.surya.settings.SettingsActivity;

import java.util.ArrayList;

public class MainFragment extends BaseFragment {

    static final String TAG = "MainActivity";
    static final String ARG_SIDE_KEY = "side";
    static final String ARG_CHATS_SIDE_VALUE = "Chats";
    static final String ARG_CALLS_SIDE_VALUE = "Calls";
    static final String ARG_CONVO_ID = "convo_id";
    private FragmentMainBinding binding;

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d(TAG, "onPageScrolled: Position: " + position + ". Position Offset: " + positionOffset + ". Position Offset Pixels: " + positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    binding.fab.show();
                    binding.fab1.hide();
                    binding.fab.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_comment_white_24dp));
//                    binding.toolbar.inflateMenu(R.menu.menu_main_activity_chats);
                    break;
                case 1:
                    binding.fab.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_add_ic_call_white_24dp));
                    binding.fab.show();
                    binding.fab1.hide();
//                    binding.toolbar.inflateMenu(R.menu.menu_main_activity_chats);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d(TAG, "onPageScrollStateChanged: State: " + state);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (binding.viewPager.getCurrentItem() == 0) {
                    requireActivity().finish();
                } else {
                    binding.viewPager.setCurrentItem(0);
                }
            }
        });


        binding.toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_sidemenu:
                     showSideMenuPopup(requireActivity().findViewById(R.id.menu_sidemenu));
                    return true;
                case R.id.menu_search:
                    showMessage(getString(R.string.underdev));
                 /*   Bundle bundle = new Bundle();
                    bundle.putString(ARG_CONVO_ID, StarredMessagesFragment.ALL_STARRED_MESSAGES);
                    Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_starredMessagesFragment, bundle);*/
                    return true;
            }

            return false;
        });

        setupViewPager();

        binding.fab.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            switch (binding.viewPager.getCurrentItem()) {
                case 1:
                    bundle.putString(ARG_SIDE_KEY, ARG_CHATS_SIDE_VALUE);
                   // Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_contactsFragment, bundle);
                    break;
                case 2:
                    //Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_contactsFragment);
                    break;
                case 3:
                    bundle.putString(ARG_SIDE_KEY, ARG_CALLS_SIDE_VALUE);
                   // Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_contactsFragment, bundle);
                    break;
            }

        });

        binding.fab1.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            switch (binding.viewPager.getCurrentItem()) {
                case 2:
                    //Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_contactsFragment);
                    break;
                case 3:
                    bundle.putString(ARG_SIDE_KEY, ARG_CALLS_SIDE_VALUE);
                   // Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_contactsFragment, bundle);
                    break;
            }

        });

    }
  /*Side Menu Options*/
    private void showSideMenuPopup(View actionView) {
        PopupMenu popupMenu = new PopupMenu(requireContext(),actionView);
        popupMenu.getMenuInflater().inflate(R.menu.menu_main_activity_side_options, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_main_new_group:
                         showMessage(getString(R.string.underdev));
                        break;
                    case R.id.menu_main_settings:
                        startActivity(new Intent(requireActivity(), SettingsActivity.class));
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.viewPager.removeOnPageChangeListener(onPageChangeListener);
    }

    void setupViewPager() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewPager.setOffscreenPageLimit(2);
        binding.viewPager.setAdapter(sectionsPagerAdapter);
        binding.viewPager.setCurrentItem(0);
        binding.fab1.hide();
        binding.tabs.setupWithViewPager(binding.viewPager);
        for (int i = 0; i < binding.tabs.getTabCount(); i++) {
            TabLayout.Tab tab = binding.tabs.getTabAt(i);
            switch(i){
                case 0: if (tab != null){
                    tab.setIcon(R.drawable.ic_tab_chat);
                }break;
                case 1: if (tab != null){
                    tab.setIcon(R.drawable.ic_tab_call);
                }break;
            }
            if (tab != null) tab.setCustomView(R.layout.customtab);
        }

    }

    static class SectionsPagerAdapter extends FragmentPagerAdapter {

        static final int NUMBER_OF_FRAGMENTS = 2;
        ArrayList<Fragment> mFragmentList = new ArrayList<>();

        public SectionsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            mFragmentList.add(new ChatsFragment());
            mFragmentList.add(new ChatsFragment());
            //mFragmentList.add(new CallsFragment());
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return NUMBER_OF_FRAGMENTS;
        }
    }
}
