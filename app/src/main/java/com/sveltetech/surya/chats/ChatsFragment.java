package com.sveltetech.surya.chats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sveltetech.surya.constants.BaseFragment;
import com.sveltetech.surya.databinding.FragmentChatsBinding;

public class ChatsFragment extends BaseFragment {

    static final String TAG = ChatsFragment.class.getSimpleName();
    static final String ARG_PROFILE_PHOTO = "profile_photo";
    static final String ARG_DISPLAY_NAME = "display_name";
    static final String ARG_NUMBER = "number";
    static final String ARG_CONVO_ID = "convo_id";
    static final String ARG_UNREAD = "unread";
    static final String ARG_UNREAD_MESSAGE_ID = "unread_id";
    private FragmentChatsBinding binding;
  //  private ChatsViewModel chatsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChatsBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }


}
