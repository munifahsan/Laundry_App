package com.tiunida.laundry0.ActivityEditProfile.Presenter;

public interface EditProfilePresenterMvp {
    void onCreate();
    void onDestroy();
    void getProfileData();
    void validateInput(String name, String dormitory, String room, String phone, String gender, String status);
}
