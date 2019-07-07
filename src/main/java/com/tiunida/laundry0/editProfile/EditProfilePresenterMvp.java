package com.tiunida.laundry0.editProfile;

public interface EditProfilePresenterMvp {
    void onCreate();
    void onDestroy();
    void getProfileData();
    void validateInput(String name, String nim, String dormitory, String room, String phone, String gender, String status);
}
