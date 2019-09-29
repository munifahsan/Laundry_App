package com.tiunida.laundry0.FragmentProfile;

import com.tiunida.laundry0.FragmentProfile.events.ProfileFragEvents;

public interface ProfileFragPresenterMvp {
    void onCreate();
    void onDestroy();
    void getProfileData();
    void onEventMainThread(ProfileFragEvents events);
}
