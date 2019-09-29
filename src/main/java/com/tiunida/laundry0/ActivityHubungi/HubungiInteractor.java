package com.tiunida.laundry0.ActivityHubungi;

public class HubungiInteractor implements HubungiInteractorMvp{
    private HubungiRepositoryMvp mHubungiRepositoryMvp;

    public HubungiInteractor(){
        mHubungiRepositoryMvp = new HubungiRepository();
    }

    public void getData(){
        mHubungiRepositoryMvp.getData();
    }
}
