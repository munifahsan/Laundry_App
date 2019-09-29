package com.tiunida.laundry0.ActivitySaran;

public class SaranInteractor implements SaranInteractorMvp{
    private SaranRepositoryMvp mSaranRepositoryMvp;

    public SaranInteractor(){
        mSaranRepositoryMvp = new SaranRepository();
    }

    public void getData(){
        mSaranRepositoryMvp.getProfileData();
    }

    public void inputData(String name, String uniqId, String saran){
        mSaranRepositoryMvp.storeFirestore(name, uniqId, saran);
    }
}
