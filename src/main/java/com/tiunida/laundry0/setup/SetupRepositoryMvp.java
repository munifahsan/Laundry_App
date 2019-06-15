package com.tiunida.laundry0.setup;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;

public interface SetupRepositoryMvp {
    void input(Uri image,String user_name, String user_nim, String user_dormitory, String user_room, String user_phone, String user_gender, String user_status);
    void storeFirestore(String user_name, String user_nim, String user_dormitory, String user_room, String user_phone, String gender, String status);
}
