package com.tiunida.laundry0.ActivityHubungi;

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
import com.tiunida.laundry0.ActivityHubungi.events.HubungiEvents;

public class HubungiRepository implements HubungiRepositoryMvp {
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String user_id;

    public HubungiRepository() {
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void getData() {
        firebaseFirestore.collection("Tentang").document("BnFBK532PL2N6KNiJIpo")
                .collection("hubungi").document("M0mCfovnk90cfudvZOr4").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("repo onComplete suc:", "masuk");

                    if (task.getResult().exists()) {
                        String no = task.getResult().getString("no");
                        String desc = task.getResult().getString("desc");
                        String email = task.getResult().getString("email");


                        Log.d("akad1 repo",""+no);

                        postEvent(HubungiEvents.onGetDataSuccess, null, no, desc, email);
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    //postEvent(KilatEventsProfile.onGetDataError, errorMessage);
                }
            }
        });
    }

    @Override
    public void postEvent(int type, String errorMessage, String no, String desc, String email) {
        HubungiEvents hubungiEvents = new HubungiEvents();
        hubungiEvents.setEventType(type);
        Log.d("masuk post", "masuk post event succes not null");
//        if (errorMessage == null) {
//            kilatEvents.setErrorMessage(errorMessage);
//        }
        hubungiEvents.setNo(no);
        hubungiEvents.setDesc(desc);
        hubungiEvents.setEmail(email);

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(hubungiEvents);
    }
}
