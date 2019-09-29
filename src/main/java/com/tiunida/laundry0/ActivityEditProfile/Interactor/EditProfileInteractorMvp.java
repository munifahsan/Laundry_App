package com.tiunida.laundry0.ActivityEditProfile.Interactor;

public interface EditProfileInteractorMvp {
    void getData();
    void doStoreFirestore(String user_name, String user_dormitory, String user_room, String user_phone, String gender, String status);
}
