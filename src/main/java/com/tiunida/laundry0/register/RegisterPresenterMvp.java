package com.tiunida.laundry0.register;

import com.tiunida.laundry0.login.events.LoginEvent;
import com.tiunida.laundry0.register.events.RegisterEvent;

public interface RegisterPresenterMvp {

   // void registerUser(String email, String password, String confirmPass);
    boolean isValifForm(String email, String password, String confirmPass);
    void onCreate();
    void onDestroy();
    void onEventMainThread(RegisterEvent event);
    void validateRegister(String email, String password);
}
