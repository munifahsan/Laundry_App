package com.tiunida.laundry0.ActivitySyaratKetentuan;

import android.util.Log;

import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;
import com.tiunida.laundry0.ActivitySyaratKetentuan.events.SyaratKetentuanEvents;
import com.tiunida.laundry0.ActivitySyaratKetentuan.ui.SyaratKetentuanViewMvp;

import org.greenrobot.eventbus.Subscribe;

public class SyaratKetentuanPersenter implements SyaratKetentuanPresenterMvp{
    private SyaratKetentuanInteractorMvp mSyaratKetentuanInteractorMvp;
    private SyaratKetentuanViewMvp mSyaratKetentuanViewMvp;

    private EventBus mEventBus;
    public SyaratKetentuanPersenter(SyaratKetentuanViewMvp syaratKetentuanViewMvp){
        mSyaratKetentuanViewMvp = syaratKetentuanViewMvp;
        mSyaratKetentuanInteractorMvp = new SyaratKetentuanInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mSyaratKetentuanViewMvp = null;
        mEventBus.unregister(this);
    }

    @Subscribe
    public void onEventMainThread(SyaratKetentuanEvents event) {
        switch (event.getEventType()) {
            case SyaratKetentuanEvents.onInputSuccess:

                break;
            case SyaratKetentuanEvents.onGetDataError:

                break;
            case SyaratKetentuanEvents.onGetDataSuccess:
                onGetDataSuccess(event.getText1(), event.getText2(), event.getText3(), event.getText4(), event.getText5(), event.getText6(), event.getText7(), event.getText8(), event.getText9(), event.getText10(), event.getText11(), event.getText12(), event.getText13(), event.getText14(), event.getText15());

                break;
            case SyaratKetentuanEvents.onInputError:

                break;
        }
    }

    public void getData(){
        mSyaratKetentuanInteractorMvp.getData();
    }

    public void onGetDataSuccess(String akad1, String akad2, String akad3, String akad4, String akad5, String akad6, String akad7, String akad8, String akad9, String akad10, String akad11, String akad12, String akad13, String akad14, String akad15){
        Log.d("aka1 ada ?",""+akad1);
        if (mSyaratKetentuanViewMvp != null){
            mSyaratKetentuanViewMvp.setRelAllVisible();
            mSyaratKetentuanViewMvp.hideProgress();
            if (!akad1.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad1(akad1);
            } else {
                mSyaratKetentuanViewMvp.setAkad1Gone();
            }

            if (!akad2.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad2(akad2);
            } else {
                mSyaratKetentuanViewMvp.setAkad2Gone();
            }

            if (!akad3.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad3(akad3);
            } else {
                mSyaratKetentuanViewMvp.setAkad3Gone();
            }

            if (!akad4.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad4(akad4);
            } else {
                mSyaratKetentuanViewMvp.setAkad4Gone();
            }

            if (!akad5.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad5(akad5);
            } else {
                mSyaratKetentuanViewMvp.setAkad5Gone();
            }

            if (!akad6.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad6(akad6);
            } else {
                mSyaratKetentuanViewMvp.setAkad6Gone();
            }

            if (!akad7.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad7(akad7);
            } else {
                mSyaratKetentuanViewMvp.setAkad7Gone();
            }

            if (!akad8.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad8(akad8);
            } else {
                mSyaratKetentuanViewMvp.setAkad8Gone();
            }

            if (!akad9.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad9(akad9);
            } else {
                mSyaratKetentuanViewMvp.setAkad9Gone();
            }

            if (!akad10.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad10(akad10);
            } else {
                mSyaratKetentuanViewMvp.setAkad10Gone();
            }

            if (!akad11.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad11(akad11);
            } else {
                mSyaratKetentuanViewMvp.setAkad11Gone();
            }

            if (!akad12.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad12(akad12);
            } else {
                mSyaratKetentuanViewMvp.setAkad12Gone();
            }

            if (!akad13.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad13(akad13);
            } else {
                mSyaratKetentuanViewMvp.setAkad13Gone();
            }

            if (!akad14.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad14(akad14);
            } else {
                mSyaratKetentuanViewMvp.setAkad14Gone();
            }

            if (!akad15.equals("")) {
                mSyaratKetentuanViewMvp.setTextAkad15(akad15);
            } else {
                mSyaratKetentuanViewMvp.setAkad15Gone();
            }
        }
    }
}
