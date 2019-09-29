package com.tiunida.laundry0.ActivitySetup;

import android.net.Uri;

public interface SetupRepositoryMvp {
    void input(Uri image,String user_name, String user_dormitory, String user_room, String user_phone, String user_gender, String user_status);
    void storeFirestore(String user_name, String user_dormitory, String user_room, String user_phone, String gender, String status);
}
