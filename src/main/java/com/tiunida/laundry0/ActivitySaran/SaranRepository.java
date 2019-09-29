package com.tiunida.laundry0.ActivitySaran;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tiunida.laundry0.ActivitySaran.saran.SaranEvents;
import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;

import java.util.HashMap;
import java.util.Map;


public class SaranRepository implements SaranRepositoryMvp{
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String user_id;

    public SaranRepository() {
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void getProfileData() {
        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("repo onComplete suc:", "masuk");

                    if (task.getResult().exists()) {
                        String name = task.getResult().getString("a_name");

                        postEvent(SaranEvents.onGetDataSuccess, null, name);
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(SaranEvents.onGetDataError, errorMessage);
                }
            }
        });
    }

    @Override
    public void storeFirestore(String name, String uniqId, String saran) {
        int uniqTimeId = Integer.valueOf(uniqId);
        Map<String, Object> userMap2 = new HashMap<>();
        userMap2.put("a_user_id", user_id);
        userMap2.put("a_name", name);
        userMap2.put("b_time", uniqTimeId);
        userMap2.put("c_saran", saran);

        firebaseFirestore.collection("Tentang").document("BnFBK532PL2N6KNiJIpo").collection("saran")
                .add(userMap2).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Log.d("masuk isSuccess ", " ya");
                    postEvent(SaranEvents.onInputSuccess);
                } else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(SaranEvents.onInputError, errorMessage);
                    Log.d("error nya ", "" + errorMessage);
                }
            }
        });
    }

    @Override
    public void postEvent(int type, String errorMessage, String dataName) {
        SaranEvents saranEvents = new SaranEvents();
        saranEvents.setEventType(type);
        Log.d("masuk post", "masuk post event succes not null");
        if (errorMessage == null) {
            saranEvents.setErrorMessage(errorMessage);
        }
        saranEvents.setA_name(dataName);

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(saranEvents);
    }

    @Override
    public void postEvent(int type, String errorMessage) {
        postEvent(type, errorMessage, null);
    }

    @Override
    public void postEvent(int type) {
        postEvent(type, null, null);
    }
}
