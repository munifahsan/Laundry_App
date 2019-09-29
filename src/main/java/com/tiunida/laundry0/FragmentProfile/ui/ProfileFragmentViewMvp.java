package com.tiunida.laundry0.FragmentProfile.ui;

public interface ProfileFragmentViewMvp {
    void onLogOut();
    void onEditProfile();
    void setData(String dataName, String dataDormitory, String dataRoom, String dataPhone, String dataStatus, String dataGender);
    void showMessage(String message);
    void hideProgress();
    void showProgress();
    void setButtons(boolean enabeled);

}
