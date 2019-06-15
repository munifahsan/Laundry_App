package com.tiunida.laundry0.setup;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.tiunida.laundry0.setup.events.SetupEvent;

public interface SetupPresenterMvp {

    void validateInput(String name, String nim, String dormitory, String room, String phone, String gender, String status);
    void onEventMainThread(SetupEvent event);
    void onCreate();
    void onDestroy();

}
