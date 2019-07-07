package com.tiunida.laundry0.profileFrag;

import com.tiunida.laundry0.profileFrag.events.ProfileFragEvents;

public interface ProfileFragPresenterMvp {
    void onCreate();
    void onDestroy();
    void getProfileData();
    void onEventMainThread(ProfileFragEvents events);
}
