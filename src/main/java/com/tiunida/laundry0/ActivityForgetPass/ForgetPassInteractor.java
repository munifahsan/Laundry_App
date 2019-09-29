package com.tiunida.laundry0.ActivityForgetPass;

public class ForgetPassInteractor implements ForgetPassInteractorMvp {
    private ForgetPassRepositoryMvp mForgetPassRepositoryMvp;

    public ForgetPassInteractor(){
        mForgetPassRepositoryMvp = new ForgetPassRepository();
    }
}
