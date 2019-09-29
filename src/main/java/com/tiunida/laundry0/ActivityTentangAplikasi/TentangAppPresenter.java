package com.tiunida.laundry0.ActivityTentangAplikasi;

import android.util.Log;

import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;
import com.tiunida.laundry0.ActivityTentangAplikasi.events.TentangAppEvents;
import com.tiunida.laundry0.ActivityTentangAplikasi.ui.TentangAppViewMvp;

import org.greenrobot.eventbus.Subscribe;

public class TentangAppPresenter implements TentangAppPresenterMvp {
    private TentangAppInteractorMvp mTentangAppInteractorMvp;
    private TentangAppViewMvp mTentangAppViewMvp;

    private EventBus mEventBus;
    public TentangAppPresenter(TentangAppViewMvp tentangAppViewMvp){
        mTentangAppViewMvp = tentangAppViewMvp;
        mTentangAppInteractorMvp = new TentangAppInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Subscribe
    public void onEventMainThread(TentangAppEvents event) {
        switch (event.getEventType()) {
            case TentangAppEvents.onInputSuccess:

                break;
            case TentangAppEvents.onInputError:

                break;
            case TentangAppEvents.onGetDataSuccess:
                onGetDataSuccess(event.getTentang());
                Log.d("on get data success =",""+event.getTentang());
                break;
            case TentangAppEvents.onGetDataError:

                break;
        }
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mTentangAppViewMvp = null;
        mEventBus.unregister(this);
    }

    @Override
    public void getData(){
        mTentangAppInteractorMvp.getData();
    }

    @Override
    public void onGetDataSuccess(String info){
        if (mTentangAppViewMvp != null){
            mTentangAppViewMvp.setInfoTxt(info);
            mTentangAppViewMvp.hideProgress();
        }
    }

}
