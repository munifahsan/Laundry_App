package com.tiunida.laundry0.ActivityTentangAplikasi;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;
import com.tiunida.laundry0.ActivityTentangAplikasi.events.TentangAppEvents;

public class TentangAppRepository implements TentangAppRepositoryMvp {
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    public TentangAppRepository(){
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void getInfoData() {
        Log.d("masuk get info data:", "masuk");
        firebaseFirestore.collection("Tentang").document("BnFBK532PL2N6KNiJIpo").collection("Tentang aplikasi")
                .document("XSiXy91rSzcifwpF4Tc2").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("repo onComplete suc:", "masuk");

                    if (task.getResult().exists()) {

                        String info = task.getResult().getString("info");

                        postEvent(TentangAppEvents.onGetDataSuccess, null, info);
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(TentangAppEvents.onGetDataError, errorMessage);
                    Log.d("error msg",""+errorMessage);
                }
            }
        });
    }

    public void postEvent(int type, String errorMessage, String info) {
        TentangAppEvents tentangAppEvents = new TentangAppEvents();
        tentangAppEvents.setEventType(type);
        Log.d("masuk post", "masuk post event succes not null = "+info);
        if (errorMessage == null && info != null) {
            tentangAppEvents.setErrorMessage(errorMessage);
            tentangAppEvents.setTentang(info);
        }
        //tentangAppEvents.setTentang(info);


        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(tentangAppEvents);
    }

    public void postEvent(int type, String errorMessage) {
        postEvent(type, errorMessage, null);
    }
}
