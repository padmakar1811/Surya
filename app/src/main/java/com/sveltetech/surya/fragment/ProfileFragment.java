package com.sveltetech.surya.fragment;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.sveltetech.surya.R;
import com.sveltetech.surya.app.PreferencesManager;
import com.sveltetech.surya.auth.CreatePinActivity;
import com.sveltetech.surya.auth.dialog.AlertDialogFragment;
import com.sveltetech.surya.auth.dialog.ProfilePictureBottomSheetDialog;
import com.sveltetech.surya.common.Constants;
import com.sveltetech.surya.common.FileUtil;
import com.sveltetech.surya.constants.BaseFragment;
import com.sveltetech.surya.databinding.FragmentProfileBinding;

import java.io.File;
import java.io.IOException;

public class ProfileFragment extends BaseFragment {

    FragmentProfileBinding binding;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_PICK_IMAGE = 2;
    static final int READ_CONTACTS_REQUEST_CODE = 1;
    static final int CAMERA_REQUEST_CODE = 2;
    static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 3;
    static final int RECORD_AUDIO_REQUEST_CODE = 4;
    static final Long SECONDS_IN_A_MINUTE = 60L;
    static final Long NUMBER_OF_MINUTES = 60L;
    static final Long NUMBER_OF_HOURS = 12L;
    static final Long SYNC_INTERVAL = NUMBER_OF_HOURS * NUMBER_OF_MINUTES * SECONDS_IN_A_MINUTE;
    static final String TAG = "ProfileInfoFragment";
    private Boolean profileSet = false;
    private String file;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });

        if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS},
                    READ_CONTACTS_REQUEST_CODE);
        } else {
            //addAppAccount();
        }

        if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }

        if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    RECORD_AUDIO_REQUEST_CODE);
        }

        if (requireActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    new String[]{(Manifest.permission.CAMERA)},
                    CAMERA_REQUEST_CODE);
        }

        binding.profilePicture.setOnClickListener(v -> {
            ProfilePictureBottomSheetDialog dialog = new ProfilePictureBottomSheetDialog(profileSet);
            dialog.showNow(getChildFragmentManager(), ProfilePictureBottomSheetDialog.class.getSimpleName());
            dialog.setDialogClickListener(new ProfilePictureBottomSheetDialog.DialogClickListener() {
                @Override
                public void onDialogGalleryClick() {
                    dispatchGalleryImagePickIntent();
                }

                @Override
                public void onDialogCameraClick() {
                    dispatchTakePictureIntent();
                }

                @Override
                public void onDialogRemoveClick() {
                   // viewModel.removeProfilePicture(requireActivity().getApplicationContext());
                }
            });
        });

        binding.submit.setOnClickListener(v -> {
            if (!binding.editfullname.getText().toString().isEmpty()) {
                goToActivityWithFinish(CreatePinActivity.class,null);
               // viewModel.updateProfile(binding.nameEditText.getText().toString());
            } else {
                showAlertDialog(getString(R.string.required_to_enter_name));
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case READ_CONTACTS_REQUEST_CODE:
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        addAppAccount();
                    } else {
                        showPermissionsAlert();
                    }
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_PICK_IMAGE){
            assert data != null;
            Uri imageUri = data.getData();
            assert imageUri != null;
           // viewModel.uploadProfilePicture(imageUri.toString(), requireActivity().getApplicationContext());
            updateUIafterUpdate();
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            switch (resultCode) {
                case RESULT_OK:
                   // viewModel.uploadProfilePicture(file, requireActivity().getApplicationContext());
                    updateUIafterUpdate();
                    FileUtil.scanMedia(file, requireActivity().getApplicationContext());
                    break;
                case RESULT_CANCELED:
                    FileUtil.deleteFile(file);
                    break;
            }
        }
    }

    public void updateUIafterUpdate(){
        WorkManager.getInstance(requireActivity().getApplicationContext())
                .getWorkInfosForUniqueWorkLiveData(PreferencesManager.getInstance(context).getContact())
                .observe(getViewLifecycleOwner(), workInfos -> {
                    if (!workInfos.isEmpty()){
                        if (workInfos.get(0).getState() == WorkInfo.State.ENQUEUED){
                            binding.profilePicture.setVisibility(View.INVISIBLE);
                            binding.addPhotoIc.setVisibility(View.INVISIBLE);
                            binding.progressBar.setVisibility(View.VISIBLE);
                        } else if (workInfos.get(0).getState() == WorkInfo.State.RUNNING){
                            binding.profilePicture.setVisibility(View.INVISIBLE);
                            binding.addPhotoIc.setVisibility(View.INVISIBLE);
                            binding.progressBar.setVisibility(View.VISIBLE);
                        } else if (workInfos.get(0).getState() == WorkInfo.State.SUCCEEDED){
                            binding.profilePicture.setVisibility(View.VISIBLE);
                            binding.addPhotoIc.setVisibility(View.INVISIBLE);
                            binding.progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(requireActivity(), "Profile picture updated", Toast.LENGTH_LONG).show();
                        } else if (workInfos.get(0).getState() == WorkInfo.State.FAILED){
                            binding.profilePicture.setVisibility(View.VISIBLE);
                            binding.progressBar.setVisibility(View.INVISIBLE);
                            binding.addPhotoIc.setVisibility(View.VISIBLE);
                            Toast.makeText(requireActivity(), "Profile picture failed to update.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void showPermissionsAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        builder.setMessage("")
                .setCancelable(false)
                .setPositiveButton(getString(R.string.go_to_settings), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", requireActivity().getApplicationContext().getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });

//        Uri uri = Uri.fromParts("package", packageName, null);

        AlertDialog alert = builder.create();
//        alert.setTitle(getString(R.string.permissions_alert));
        alert.show();
    }


    private Boolean checkIfAppAccountExists() {
        boolean accountExists = false;
        for(Account account: AccountManager.get(requireActivity()).getAccounts()) {
            if (account.type.equals(Constants.ACCOUNT_TYPE)) {
                accountExists = true;
                break;
            }
        }
        return accountExists;
    }

    private void addAppAccount() {
        Account mAccount = new Account(Constants.ACCOUNT_NAME, Constants.ACCOUNT_TYPE);

        if (!checkIfAppAccountExists()) {
            if (AccountManager.get(requireActivity()).addAccountExplicitly(mAccount, null, null)) {
                ContentResolver.setSyncAutomatically(mAccount, ContactsContract.AUTHORITY, true);
                ContentResolver.addPeriodicSync(mAccount, ContactsContract.AUTHORITY, new Bundle(), SYNC_INTERVAL);
            }
        }
    }
    private void dispatchGalleryImagePickIntent() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        if (gallery.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivityForResult(gallery, REQUEST_PICK_IMAGE);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = FileUtil.createMediaFile(FileUtil.FILE_TYPE_PROFILE_PHOTO);
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = Uri.fromFile(photoFile);
                file = photoURI.toString();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        }
    }

    private void showAlertDialog(String message) {
        DialogFragment dialog = new AlertDialogFragment(message);
        dialog.show(getChildFragmentManager(), AlertDialogFragment.class.getSimpleName());
    }
}