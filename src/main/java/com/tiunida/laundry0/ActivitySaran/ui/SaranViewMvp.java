package com.tiunida.laundry0.ActivitySaran.ui;

public interface SaranViewMvp {
    void getData();
    void setName(String name);
    void hideProgress();
    void disableInputs();
    void enableinputs();
    void setInputs(Boolean enable);
    void setInputsEmpty();
    void showMessage(String message);
    void showProgress();
}
