package com.tiunida.laundry0.ActivityForgetPass;

import com.tiunida.laundry0.ActivityForgetPass.ui.ForgetPassViewMvp;
import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;

public class ForgetPassPresenter implements ForgetPassPresenterMvp{
    private ForgetPassInteractorMvp mForgetPassInteractorMvp;
    private ForgetPassViewMvp mForgetPassViewMvp;

    EventBus mEventBus;

    public ForgetPassPresenter(ForgetPassViewMvp forgetPassViewMvp){
        mForgetPassInteractorMvp = new ForgetPassInteractor();
        mForgetPassViewMvp = forgetPassViewMvp;
        mEventBus = GreenRobotEventBus.getInstance();
    }
}
