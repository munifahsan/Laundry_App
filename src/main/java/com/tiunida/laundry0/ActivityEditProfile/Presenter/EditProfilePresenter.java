package com.tiunida.laundry0.ActivityEditProfile.Presenter;

import com.tiunida.laundry0.ActivityEditProfile.Interactor.EditProfileInteractor;
import com.tiunida.laundry0.ActivityEditProfile.Interactor.EditProfileInteractorMvp;
import com.tiunida.laundry0.ActivityEditProfile.events.EditProfileEvent;
import com.tiunida.laundry0.ActivityEditProfile.View.EditProfileViewMvp;
import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

public class EditProfilePresenter implements EditProfilePresenterMvp{

    private EventBus mEventBus;
    private EditProfileViewMvp mEditProfileViewMvp;
    private EditProfileInteractorMvp mEditProfileInteractorMvp;

    public EditProfilePresenter (EditProfileViewMvp editProfileViewMvp){
        mEditProfileViewMvp = editProfileViewMvp;
        mEditProfileInteractorMvp = new EditProfileInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate(){
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mEditProfileViewMvp = null;
        mEventBus.unregister(this);
    }

    @Subscribe
    public void onEventMainThread(EditProfileEvent event){
        switch (event.getEventType()){
            case EditProfileEvent.onInputSuccess:
                onInputSuccess();
                break;
            case EditProfileEvent.onInputError:
                onInputError(event.getErrorMessage());
                break;
            case EditProfileEvent.onFIrestoreError:
                onInputError(event.getErrorMessage());
                break;
            case EditProfileEvent.onGetDataSuccess:
                onGetDataSuccess(event.getDataName(),event.getDataDormitory(),event.getDataRoom(),event.getDataPhone(),event.getDataStatus(),event.getDataGender());
                break;
            case EditProfileEvent.onGetDataError:
                onGetDataError(event.getErrorMessage());
                break;
        }
    }

    public void getProfileData(){
        mEditProfileInteractorMvp.getData();
    }

    public void onGetDataError(String eroorMessage){
        if (mEditProfileViewMvp != null){
            mEditProfileViewMvp.showMessage(eroorMessage);
        }
    }


    public void onGetDataSuccess(String dataName, String dataDormitory, String dataRoom, String dataPhone, String dataStatus, String dataGender){
        if (mEditProfileViewMvp != null){
            //mProfileFragmentViewMvp.showProgress();
            mEditProfileViewMvp.setData(dataName,dataDormitory,dataRoom,dataPhone,dataStatus,dataGender);
            mEditProfileViewMvp.hideProgress();
        }
    }

    public void onInputSuccess(){
        if (mEditProfileViewMvp != null){
            mEditProfileViewMvp.navigateToMainScreen();
            //mEditProfileViewMvp.finishActivity();
        }
    }

    public void onInputError(String error){
        if (mEditProfileViewMvp != null){
            mEditProfileViewMvp.showMessage(error);
        }
    }

    public void validateInput(String name, String dormitory, String room, String phone, String gender, String status){
        if (!validateName(name) | !validateDormitory(dormitory) | !validateRoom(room) | !validatePhoneNumber(phone)) {
            return;
        }

        if (mEditProfileViewMvp != null){
            mEditProfileViewMvp.showProgress();
            mEditProfileViewMvp.disableInputs(true);
        }
        // if (isChanged){
        //if (!TextUtils.isEmpty(name) && mainImageURI != null){
        //mSetupInteractorMvp.doInput(image,name,nim,dormitory,room,phone,gender,status);
        //}
        // }
        mEditProfileInteractorMvp.doStoreFirestore(name, dormitory, room, phone, gender, status);
    }

    private boolean validateName(String nameInput) {
        if (nameInput.isEmpty()) {
            mEditProfileViewMvp.nameFieldError("Field can't be empty");
            return false;
        } else {
            mEditProfileViewMvp.nameFieldError(null);
            return true;
        }
    }

    private boolean validateNim(String nimInput) {
        if (nimInput.isEmpty()) {
            mEditProfileViewMvp.nimFieldError("Field can't be empty");
            return false;
        } else if (nimInput.length() > 12) {
            mEditProfileViewMvp.nimFieldError("Nim too long");
            return false;
        } else {
            mEditProfileViewMvp.nimFieldError(null);
            return true;
        }
    }

    private boolean validateDormitory(String dormitoryInput) {
        if (dormitoryInput.isEmpty()) {
            mEditProfileViewMvp.dormitoryFieldError("Field can't be empty");
            return false;
        } else {
            mEditProfileViewMvp.dormitoryFieldError(null);
            return true;
        }
    }

    private boolean validatePhoneNumber(String phoneNumberInput) {
        if (phoneNumberInput.isEmpty()) {
            mEditProfileViewMvp.phoneFieldError("Field can't be empty");
            return false;
        }else if (phoneNumberInput.length() > 12){
            mEditProfileViewMvp.phoneFieldError("Phone number too long");
            return false;
        } else {
            mEditProfileViewMvp.phoneFieldError(null);
            return true;
        }
    }

    private boolean validateRoom(String roomInput) {
        if (roomInput.isEmpty()) {
            mEditProfileViewMvp.roomFieldError("Field can't be empty");
            return false;
        }else if (roomInput.length() > 3){
            mEditProfileViewMvp.roomFieldError("Room number too long");
            return false;

        }else {
            mEditProfileViewMvp.roomFieldError(null);
            return true;
        }
    }



}
