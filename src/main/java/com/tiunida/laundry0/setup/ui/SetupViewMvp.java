package com.tiunida.laundry0.setup.ui;

public interface SetupViewMvp {
    void BringImagePicker();
//    void setupImage();
    void phoneFieldError(String error);
    void roomFieldError(String errorMsg);
    void dormitoryFieldError(String errorMsg);
    void nimFieldError(String errorMsg);
    void nameFieldError(String errorMsg);
    void confirmInput();
    void navigateToMainScreen();
    void showMessage(String message);
    void enableInputs(boolean enabeled);
    void disableInputs(boolean enabeled);
    void showProgress();

}
