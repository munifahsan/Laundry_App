package com.tiunida.laundry0.profileFrag;


import android.util.Log;

public class ProfileFragInteractor implements ProfileFragInteractorMvp {
    private ProfileFragRepositoryMvp mProfileFragRepositoryMvp;
    public ProfileFragInteractor(){
        mProfileFragRepositoryMvp = new ProfileFragRepository();
    }

    public void getData(){
        mProfileFragRepositoryMvp.getProfileData();
        Log.d("interactor :","masuk");
    }
}
