package com.tiunida.laundry0.ActivitySaran;

import android.util.Log;

import com.tiunida.laundry0.ActivitySaran.saran.SaranEvents;
import com.tiunida.laundry0.ActivitySaran.ui.SaranViewMvp;
import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

public class SaranPresenter implements SaranPresenterMvp {
    private SaranInteractorMvp mSaranInteractorMvp;
    private SaranViewMvp mSaranViewMvp;

    private EventBus mEventBus;

    public SaranPresenter(SaranViewMvp saranViewMvp) {
        mSaranInteractorMvp = new SaranInteractor();
        mSaranViewMvp = saranViewMvp;
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Subscribe
    public void onEventMainThread(SaranEvents event) {
        switch (event.getEventType()) {
            case SaranEvents.onInputSuccess:
                onInputSuccess();
                break;
            case SaranEvents.onInputError:

                break;
            case SaranEvents.onGetDataSuccess:
                onGetDataSuccess(event.getA_name());
                Log.d("akad1 room", "" + event.getA_name());
                break;
            case SaranEvents.onGetDataError:

                break;
        }
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mSaranViewMvp = null;
        mEventBus.unregister(this);
    }

    public void getData() {
        mSaranInteractorMvp.getData();
    }

    public void validateInputs(String name, String uniqId, String saran) {
        String empty = "";
        if (name != null && uniqId != null && !saran.equals(empty)) {
            mSaranViewMvp.showProgress();
            mSaranInteractorMvp.inputData(name, uniqId, saran);
        }else {
            mSaranViewMvp.showMessage("Anda belum memasukan saran");
        }
    }

    public void onInputSuccess() {
        if (mSaranViewMvp != null) {
            mSaranViewMvp.hideProgress();
            mSaranViewMvp.setInputsEmpty();
            mSaranViewMvp.showMessage("Saran anda terkirim");
        }
    }

    public void onGetDataSuccess(String name) {
        if (mSaranViewMvp != null) {
            mSaranViewMvp.setName(name);
            mSaranViewMvp.hideProgress();
        }
    }
}
