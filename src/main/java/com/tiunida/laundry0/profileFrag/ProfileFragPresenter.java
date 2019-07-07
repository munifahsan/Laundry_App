package com.tiunida.laundry0.profileFrag;

import android.util.Log;

import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.profileFrag.events.ProfileFragEvents;
import com.tiunida.laundry0.profileFrag.ui.ProfileFragmentViewMvp;

import org.greenrobot.eventbus.Subscribe;

public class ProfileFragPresenter implements ProfileFragPresenterMvp {
    private EventBus mEventBus;
    private ProfileFragmentViewMvp mProfileFragmentViewMvp;
    private ProfileFragInteractorMvp mProfileFragInteractorMvp;

    public ProfileFragPresenter(ProfileFragmentViewMvp profileFragmentViewMvp){
        mProfileFragmentViewMvp = profileFragmentViewMvp;
        mProfileFragInteractorMvp = new ProfileFragInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mProfileFragmentViewMvp = null;
        mEventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ProfileFragEvents events){
        Log.d("onEventMainThread","masuk");
        switch (events.getEventType()){
            case ProfileFragEvents.onGetDataError:
                onGetDataError(events.getErrorMessage());
                break;
            case ProfileFragEvents.onGetDataSuccess:
                Log.d("getDataSucces","masuk");
                onGetDataSuccess(events.getDataName(),events.getDataNim(),events.getDataDormitory(),events.getDataRoom(),events.getDataPhone(),events.getDataStatus(),events.getDataGender());
                break;
        }
    }

    public void getProfileData(){
        mProfileFragInteractorMvp.getData();
    }

    public void onGetDataError(String eroorMessage){
        if (mProfileFragmentViewMvp != null){
            mProfileFragmentViewMvp.showMessage(eroorMessage);
        }
    }

    public void onGetDataSuccess(String dataName, String dataNim, String dataDormitory, String dataRoom, String dataPhone, String dataStatus, String dataGender){
        if (mProfileFragmentViewMvp != null){
            //mProfileFragmentViewMvp.showProgress();
            mProfileFragmentViewMvp.setData(dataName,dataNim,dataDormitory,dataRoom,dataPhone,dataStatus,dataGender);
            mProfileFragmentViewMvp.hideProgress();
            mProfileFragmentViewMvp.setButtons(true);
        }
    }
}
