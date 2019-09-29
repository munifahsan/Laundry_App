package com.tiunida.laundry0.ActivityRegister.ui;

public interface RegisterViewMvp {
    void navigateToLoginScreen();
    void navigateToSetupScreen();
    void handleSignUp();
    void showMessage(String message);
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();
}
