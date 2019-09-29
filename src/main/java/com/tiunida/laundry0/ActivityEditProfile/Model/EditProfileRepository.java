package com.tiunida.laundry0.ActivityEditProfile.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tiunida.laundry0.ActivityEditProfile.events.EditProfileEvent;
import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;

import java.util.HashMap;
import java.util.Map;

public class EditProfileRepository implements EditProfileRepositoryMvp {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private String user_id;

    public EditProfileRepository(){
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
                        //String nim = task.getResult().getString("b_nim");
                        String room = task.getResult().getString("d_room");
                        String image = task.getResult().getString("image");
                        String dormitory = task.getResult().getString("c_dormitory");
                        String phone = task.getResult().getString("e_phone number");
                        String gender = task.getResult().getString("g_gender");
                        String status = task.getResult().getString("f_status");

                        Log.d("data dapat","masuk" + name);
                        postEvent(EditProfileEvent.onGetDataSuccess,null, name,dormitory,room,phone,gender,status);

                    }

                } else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(EditProfileEvent.onGetDataError,errorMessage);
//                    Toast.makeText(getActivity(),"FIRESTORE retrivew Error :" + errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void storeFirestore(String user_name, String user_dormitory, String user_room, String user_phone, String gender, String status) {

        Map<String, String> userMap2 = new HashMap<>();
        userMap2.put("a_name", user_name);
        //userMap2.put("b_nim" , user_nim);
        userMap2.put("c_dormitory" , user_dormitory);
        userMap2.put("d_room" , user_room);
        userMap2.put("e_phone number" , user_phone);
        userMap2.put("g_gender" , gender);
        userMap2.put("f_status" , status);

        firebaseFirestore.collection("Users").document(user_id)
                .set(userMap2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    postEvent(EditProfileEvent.onInputSuccess);

                }else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(EditProfileEvent.onInputError,errorMessage);
                }
            }
        });
    }


    private void postEvent(int type, String errorMessage, String dataName, String dataDormitory, String dataRoom, String dataPhone, String dataGender, String dataStatus) {
        Log.d("postEv","masuk");
        Log.d("masuk post","dapet " + dataName);
        EditProfileEvent editProfileEvent = new EditProfileEvent();
        editProfileEvent.setEventType(type);
        //if (errorMessage == null && dataName != null && dataDormitory != null && dataRoom != null && dataPhone != null && dataStatus != null && dataGender != null) {
            editProfileEvent.setErrorMessage(errorMessage);
            editProfileEvent.setDataName(dataName);
            Log.d("masuk post if ", "dapet" + dataName);
            //editProfileEvent.setDataNim(dataNim);
            editProfileEvent.setDataDormitory(dataDormitory);
            editProfileEvent.setDataRoom(dataRoom);
            editProfileEvent.setDataPhone(dataPhone);
            editProfileEvent.setDataStatus(dataStatus);
            editProfileEvent.setDataGender(dataGender);
        //}
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(editProfileEvent);
    }

    private void postEvent(int type, String errorMessage) {
        postEvent(type, errorMessage, null, null, null, null, null, null);
    }

    private void postEvent(int type) {
        postEvent(type, null, null, null, null, null, null, null);
    }
}
