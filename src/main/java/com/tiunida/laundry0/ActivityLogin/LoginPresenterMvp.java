package com.tiunida.laundry0.ActivityLogin;

import com.tiunida.laundry0.ActivityLogin.events.LoginEvent;

public interface LoginPresenterMvp {

    boolean isValifForm(String email, String password);
    void validateLogin(String email, String password);
    void onEventMainThread(LoginEvent event);
    void onSignInSuccess();
    void onSignInError();
    void onCreate();
    void onDestroy();
}
