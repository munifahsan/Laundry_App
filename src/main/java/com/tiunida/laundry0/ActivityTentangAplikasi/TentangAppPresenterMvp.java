package com.tiunida.laundry0.ActivityTentangAplikasi;

public interface TentangAppPresenterMvp {
    void onCreate();
    void onDestroy();
    void getData();
    void onGetDataSuccess(String info);
}
