package com.tiunida.laundry0.ActivitySaran;

public interface SaranPresenterMvp {
    void onInputSuccess();
    void onGetDataSuccess(String name);
    void onCreate();
    void onDestroy();
    void getData();
    void validateInputs(String name, String uniqId, String saran);
}
