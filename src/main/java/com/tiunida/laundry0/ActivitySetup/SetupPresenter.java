package com.tiunida.laundry0.ActivitySetup;

import android.net.Uri;

import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;
import com.tiunida.laundry0.ActivitySetup.events.SetupEvent;
import com.tiunida.laundry0.ActivitySetup.ui.SetupViewMvp;

import org.greenrobot.eventbus.Subscribe;

public class SetupPresenter implements SetupPresenterMvp {

    private boolean isChanged = false;
    private Uri mainImageURI = null;
    private EventBus mEventBus;
    private SetupViewMvp mSetupViewMvp;
    private SetupInteractorMvp mSetupInteractorMvp;

    public SetupPresenter(SetupViewMvp setupViewMvp){
        mSetupViewMvp = setupViewMvp;
        mSetupInteractorMvp = new SetupInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate(){
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mSetupViewMvp = null;
        mEventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(SetupEvent event){
        switch (event.getEventType()){
            case SetupEvent.onInputSuccess:
                onInputSuccess();
                break;
            case SetupEvent.onFIrestoreError:
                onInputError(event.getErrorMessage());
                break;
            case SetupEvent.onInputError:
                break;
        }
    }

    public void onInputSuccess(){
        if (mSetupViewMvp != null){
            mSetupViewMvp.navigateToMainScreen();
        }
    }

    public void onInputError(String error){
        if (mSetupViewMvp != null){
            mSetupViewMvp.showMessage(error);
        }
    }

    public void validateInput(String name, String dormitory, String room, String phone, String gender, String status){
        if (!validateName(name)  | !validateDormitory(dormitory) | !validateRoom(room) | !validatePhoneNumber(phone)) {
            return;
        }

        if (mSetupViewMvp != null){
            mSetupViewMvp.showProgress();
            mSetupViewMvp.enableInputs(true);
        }
       // if (isChanged){
            //if (!TextUtils.isEmpty(name) && mainImageURI != null){
                //mSetupInteractorMvp.doInput(image,name,nim,dormitory,room,phone,gender,status);
            //}
       // }
       mSetupInteractorMvp.doStoreFirestore(name, dormitory, room, phone, gender, status);
    }

    private boolean validateName(String nameInput) {
        if (nameInput.isEmpty()) {
            mSetupViewMvp.nameFieldError("Field can't be empty");
            return false;
        } else {
            mSetupViewMvp.nameFieldError(null);
            return true;
        }
    }

    private boolean validateNim(String nimInput) {
        if (nimInput.isEmpty()) {
            mSetupViewMvp.nimFieldError("Field can't be empty");
            return false;
        } else if (nimInput.length() > 12) {
            mSetupViewMvp.nimFieldError("Nim too long");
            return false;
        } else {
            mSetupViewMvp.nimFieldError(null);
            return true;
        }
    }

    private boolean validateDormitory(String dormitoryInput) {
        if (dormitoryInput.isEmpty()) {
            mSetupViewMvp.dormitoryFieldError("Field can't be empty");
            return false;
        } else {
            mSetupViewMvp.dormitoryFieldError(null);
            return true;
        }
    }

    private boolean validatePhoneNumber(String phoneNumberInput) {
        if (phoneNumberInput.isEmpty()) {
            mSetupViewMvp.phoneFieldError("Field can't be empty");
            return false;
        }else if (phoneNumberInput.length() > 12){
            mSetupViewMvp.phoneFieldError("Phone number too long");
            return false;
        } else {
            mSetupViewMvp.phoneFieldError(null);
            return true;
        }
    }

    private boolean validateRoom(String roomInput) {
        if (roomInput.isEmpty()) {
            mSetupViewMvp.roomFieldError("Field can't be empty");
            return false;
        }else if (roomInput.length() > 3){
            mSetupViewMvp.roomFieldError("Room number too long");
            return false;

        }else {
            mSetupViewMvp.roomFieldError(null);
            return true;
        }
    }

}
