package com.tiunida.laundry0.editProfile;

public interface EditProfileRepositoryMvp {
    void getProfileData();
    void storeFirestore(String user_name, String user_nim, String user_dormitory, String user_room, String user_phone, String gender, String status);
}
