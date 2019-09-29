package com.tiunida.laundry0.ActivitySyaratKetentuan;

public class SyaratKetentuanInteractor implements SyaratKetentuanInteractorMvp {
    SyaratKetentuanRepositoryMvp mSyaratKetentuanRepositoryMvp;

    public SyaratKetentuanInteractor(){
        mSyaratKetentuanRepositoryMvp = new SyaratKetentuanRepository();
    }

    public void getData(){
        mSyaratKetentuanRepositoryMvp.getData();
    }
}
