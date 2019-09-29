package com.tiunida.laundry0.ActivityEditProfile.Interactor;

import com.tiunida.laundry0.ActivityEditProfile.Model.EditProfileRepository;
import com.tiunida.laundry0.ActivityEditProfile.Model.EditProfileRepositoryMvp;

public class EditProfileInteractor implements EditProfileInteractorMvp {
    private EditProfileRepositoryMvp mEditProfileRepositoryMvp;

    public EditProfileInteractor(){
        mEditProfileRepositoryMvp = new EditProfileRepository();
    }

    public void getData(){
        mEditProfileRepositoryMvp.getProfileData();
    }

    public void doStoreFirestore(String user_name, String user_dormitory, String user_room, String user_phone, String gender, String status){
        mEditProfileRepositoryMvp.storeFirestore(user_name, user_dormitory, user_room, user_phone, gender, status);
    }
}
