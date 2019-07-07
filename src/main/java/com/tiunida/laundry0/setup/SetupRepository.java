package com.tiunida.laundry0.setup;

import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.setup.events.SetupEvent;

import java.util.HashMap;
import java.util.Map;

public class SetupRepository implements SetupRepositoryMvp{

    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String user_id;
    private Uri mainImageURI = null;
    private Uri mainImageURI2 = null;
    private boolean isChanged = false;


    public SetupRepository(){
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void input(Uri image,String user_name, String user_nim, String user_dormitory, String user_room, String user_phone, String user_gender, String user_status){
        user_id = firebaseAuth.getCurrentUser().getUid();
        if (isChanged){
            if (!TextUtils.isEmpty(user_name) && mainImageURI != null){
                final StorageReference image_path = storageReference.child("profile_images").child(user_id + ".jpg");
                image_path.putFile(image).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {

                            throw task.getException();

                        }

                        // Continue with the task to get the download URL
                        return image_path.getDownloadUrl();
                    }
                })
                        .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {

                            mainImageURI = image;
                            mainImageURI2 = mainImageURI;
                            //storeFirestore(task, user_name, user_nim,user_dormitory, user_room, user_phone, user_gender, user_status);

                        } else {
                            // Handle failures
                            // ...
                            String errorMessage = task.getException().getMessage();
                            //Toast.makeText(SetupActivity.this, "Error :" + errorMessage, Toast.LENGTH_LONG).show();
                            //postEvent(SetupEvent.onInputError,errorMessage);

                            //setupProgress.setVisibility(View.INVISIBLE);

                        }
                    }
                });
            }

        }
        //storeFirestore(null, user_name, user_nim,user_dormitory, user_room, user_phone, user_gender, user_status);
    }

    @Override
    public void storeFirestore(String user_name, String user_nim, String user_dormitory, String user_room, String user_phone, String gender, String status) {

        Map<String, String> userMap2 = new HashMap<>();
        userMap2.put("a_name", user_name);
        userMap2.put("b_nim" , user_nim);
        userMap2.put("c_dormitory" , user_dormitory);
        userMap2.put("d_room" , user_room);
        userMap2.put("e_phone number" , user_phone);
        userMap2.put("f_gender" , gender);
        userMap2.put("g_status" , status);

        firebaseFirestore.collection("Users").document(user_id)
                .set(userMap2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    postEvent(SetupEvent.onInputSuccess);

                }else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(SetupEvent.onInputError,errorMessage);
                }
            }
        });
    }

    private void postEvent(int type, String errorMessage) {
        SetupEvent loginEvent = new SetupEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }
}
