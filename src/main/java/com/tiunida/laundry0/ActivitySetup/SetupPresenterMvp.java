package com.tiunida.laundry0.ActivitySetup;

import com.tiunida.laundry0.ActivitySetup.events.SetupEvent;

public interface SetupPresenterMvp {

    void validateInput(String name, String dormitory, String room, String phone, String gender, String status);
    void onEventMainThread(SetupEvent event);
    void onCreate();
    void onDestroy();

}
