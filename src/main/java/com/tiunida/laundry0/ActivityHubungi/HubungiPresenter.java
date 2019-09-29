package com.tiunida.laundry0.ActivityHubungi;

import android.util.Log;

import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;
import com.tiunida.laundry0.ActivityHubungi.events.HubungiEvents;
import com.tiunida.laundry0.ActivityHubungi.ui.HubungiViewMvp;

import org.greenrobot.eventbus.Subscribe;

public class HubungiPresenter implements HubungiPresenterMvp{
    private HubungiInteractorMvp mHubungiInteractorMvp;
    private HubungiViewMvp mHubungiViewMvp;

    private EventBus mEventBus;

    public HubungiPresenter(HubungiViewMvp hubungiViewMvp){
        mHubungiInteractorMvp = new HubungiInteractor();
        mHubungiViewMvp = hubungiViewMvp;
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mHubungiViewMvp = null;
        mEventBus.unregister(this);
    }

    @Subscribe
    public void onEventMainThread(HubungiEvents event) {
        switch (event.getEventType()) {
            case HubungiEvents.onInputSuccess:

                break;
            case HubungiEvents.onGetDataError:

                break;
            case HubungiEvents.onGetDataSuccess:
                onGetDataSuccess(event.getNo(), event.getDesc(), event.getEmail());
                Log.d("data no ",""+event.getNo());
                break;
            case HubungiEvents.onInputError:

                break;
        }
    }

    @Override
    public void getData(){
        mHubungiInteractorMvp.getData();
    }

    public void onGetDataSuccess(String no, String desc, String email){
        if (mHubungiViewMvp != null){
            mHubungiViewMvp.setEmailTxt(email);
            mHubungiViewMvp.setNoDescTxt(desc);
            mHubungiViewMvp.setNoTxt(no);
            mHubungiViewMvp.hideProgress();
        }
    }
}
