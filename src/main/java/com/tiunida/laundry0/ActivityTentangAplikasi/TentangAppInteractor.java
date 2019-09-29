package com.tiunida.laundry0.ActivityTentangAplikasi;

public class TentangAppInteractor implements TentangAppInteractorMvp {
    private TentangAppRepositoryMvp mTentangAppRepositoryMvp;

    public TentangAppInteractor(){
        mTentangAppRepositoryMvp = new TentangAppRepository();
    }

    public void getData(){
        mTentangAppRepositoryMvp.getInfoData();
    }
}
