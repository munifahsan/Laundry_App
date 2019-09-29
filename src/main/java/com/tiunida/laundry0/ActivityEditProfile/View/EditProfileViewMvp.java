package com.tiunida.laundry0.ActivityEditProfile.View;

public interface EditProfileViewMvp {
    void hideProgress();
    void showProgress();
    void nameFieldError(String errorMsg);
    void nimFieldError(String errorMsg);
    void dormitoryFieldError(String errorMsg);
    void roomFieldError(String errorMsg);
    void phoneFieldError(String errorMsg);
    void navigateToMainScreen();
    void enableInputs(boolean enabeled);
    void disableInputs(boolean enabeled);
    void setData(String dataName, String dataDormitory, String dataRoom, String dataPhone, String dataStatus, String dataGender);
    void showMessage(String message);
}
