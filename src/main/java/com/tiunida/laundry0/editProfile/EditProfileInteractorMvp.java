package com.tiunida.laundry0.editProfile;

public interface EditProfileInteractorMvp {
    void getData();
    void doStoreFirestore(String user_name, String user_nim, String user_dormitory, String user_room, String user_phone, String gender, String status);
}
