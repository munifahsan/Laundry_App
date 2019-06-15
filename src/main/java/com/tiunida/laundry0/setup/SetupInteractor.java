package com.tiunida.laundry0.setup;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;

public class SetupInteractor implements SetupInteractorMvp {
    private SetupRepositoryMvp mSetupRepositoryMvp;

    public SetupInteractor(){
        mSetupRepositoryMvp = new SetupRepository();
    }

    @Override
    public void doInput(Uri image,String user_name, String user_nim, String user_dormitory, String user_room, String user_phone, String user_gender, String user_status) {
        mSetupRepositoryMvp.input(image, user_name,user_nim,user_dormitory,user_room,user_phone,user_gender,user_status);
    }

    @Override
    public void doStoreFirestore(String user_name, String user_nim, String user_dormitory, String user_room, String user_phone, String gender, String status) {
        mSetupRepositoryMvp.storeFirestore(user_name, user_nim, user_dormitory, user_room, user_phone, gender, status);
    }
}
