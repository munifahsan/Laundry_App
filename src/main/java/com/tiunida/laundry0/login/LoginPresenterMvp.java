package com.tiunida.laundry0.login;

import com.tiunida.laundry0.login.events.LoginEvent;

public interface LoginPresenterMvp {

    boolean isValifForm(String email, String password);
    void validateLogin(String email, String password);
    void onEventMainThread(LoginEvent event);
    void onSignInSuccess();
    void onSignInError();
    void onCreate();
    void onDestroy();
}
