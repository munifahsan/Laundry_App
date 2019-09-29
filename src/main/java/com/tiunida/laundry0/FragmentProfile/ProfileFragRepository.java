package com.tiunida.laundry0.FragmentProfile;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;
import com.tiunida.laundry0.FragmentProfile.events.ProfileFragEvents;

public class ProfileFragRepository implements ProfileFragRepositoryMvp{
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private String user_id;
    public ProfileFragRepository(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
    }

    public void getProfileData(){
        Log.d("repo : ", "masuk");
        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Log.d("repo onComplete : ", "masuk");
                if (task.isSuccessful()){
                    Log.d("repo onComplete suc:", "masuk");

                    if (task.getResult().exists()){
//                        Toast.makeText(SetupActivity.this,"datanya ada " , Toast.LENGTH_LONG).show();
                        String name = task.getResult().getString("a_name");
                        String nim = task.getResult().getString("b_nim");
                        String room = task.getResult().getString("d_room");
                        String image = task.getResult().getString("image");
                        String dormitory = task.getResult().getString("c_dormitory");
                        String phone = task.getResult().getString("e_phone number");
                        String gender = task.getResult().getString("g_gender");
                        String status = task.getResult().getString("f_status");

                        Log.d("data dapat","masuk" + name);
                        postEvent(ProfileFragEvents.onGetDataSuccess,null, name,dormitory,room,phone,status,gender);
//                        mainImageURI = Uri.parse(image);

//                        fragmentNameTxt.setText(name);
//                        fregmentNimTxt.setText(nim);
//                        fragmentRoomTxt.setText(room);
//                        RequestOptions placeHolderRequest = new RequestOptions();
//                        placeHolderRequest.placeholder(R.drawable.default_image);
//
//                        Glide.with(ProfileFragment.this).setDefaultRequestOptions(placeHolderRequest).load(image).into(fragmentProfileImage);

                    }

                } else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(ProfileFragEvents.onGetDataError,errorMessage);
//                    Toast.makeText(getActivity(),"FIRESTORE retrivew Error :" + errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void postEvent(int type, String errorMessage, String dataName,  String dataDormitory, String dataRoom, String dataPhone, String dataStatus, String dataGender) {
        Log.d("postEv","masuk");
        Log.d("masuk post","dapet " + dataName);
        ProfileFragEvents profileFragEvents = new ProfileFragEvents();
        profileFragEvents.setEventType(type);
        //if (errorMessage == null && dataName != null && dataNim != null && dataDormitory != null && dataRoom != null && dataPhone != null && dataStatus != null && dataGender != null) {
            profileFragEvents.setErrorMessage(errorMessage);
            profileFragEvents.setDataName(dataName);
            Log.d("masuk post if ", "dapet" + dataName);
            //profileFragEvents.setDataNim(dataNim);
            profileFragEvents.setDataDormitory(dataDormitory);
            profileFragEvents.setDataRoom(dataRoom);
            profileFragEvents.setDataPhone(dataPhone);
            profileFragEvents.setDataStatus(dataStatus);
            profileFragEvents.setDataGender(dataGender);
        //}
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(profileFragEvents);
    }

    private void postEvent(int type, String errorMessage) {
        postEvent(type, errorMessage, null, null, null, null, null, null);
    }

    private void postEvent(int type) {
        postEvent(type, null, null, null, null, null, null, null);
    }
}
