package com.tiunida.laundry0.profileFrag.ui;

public interface ProfileFragmentViewMvp {
    void onLogOut();
    void onEditProfile();
    void setData(String dataName, String dataNim, String dataDormitory, String dataRoom, String dataPhone, String dataStatus, String dataGender);
    void showMessage(String message);
    void hideProgress();
    void showProgress();
    void setButtons(boolean enabeled);

}
