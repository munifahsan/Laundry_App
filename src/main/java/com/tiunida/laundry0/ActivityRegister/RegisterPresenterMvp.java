package com.tiunida.laundry0.ActivityRegister;

import com.tiunida.laundry0.ActivityRegister.events.RegisterEvent;

public interface RegisterPresenterMvp {

   // void registerUser(String email, String password, String confirmPass);
    boolean isValifForm(String email, String password, String confirmPass);
    void onCreate();
    void onDestroy();
    void onEventMainThread(RegisterEvent event);
    void validateRegister(String email, String password);
}
