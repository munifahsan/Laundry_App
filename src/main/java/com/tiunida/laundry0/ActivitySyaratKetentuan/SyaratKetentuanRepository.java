package com.tiunida.laundry0.ActivitySyaratKetentuan;

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
import com.tiunida.laundry0.ActivitySyaratKetentuan.events.SyaratKetentuanEvents;

public class SyaratKetentuanRepository implements SyaratKetentuanRepositoryMvp{

    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String user_id;

    public SyaratKetentuanRepository(){
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void getData() {
        firebaseFirestore.collection("Tentang").document("BnFBK532PL2N6KNiJIpo")
                .collection("Syarat dan ketentuan").document("nsjdhHkJfoxGdNgRBWiV").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("repo onComplete suc:", "masuk");

                    if (task.getResult().exists()) {
                        String akad1 = task.getResult().getString("1");
                        String akad2 = task.getResult().getString("2");
                        String akad3 = task.getResult().getString("3");
                        String akad4 = task.getResult().getString("4");
                        String akad5 = task.getResult().getString("5");
                        String akad6 = task.getResult().getString("6");
                        String akad7 = task.getResult().getString("7");
                        String akad8 = task.getResult().getString("8");
                        String akad9 = task.getResult().getString("9");
                        String akad10 = task.getResult().getString("10");
                        String akad11 = task.getResult().getString("11");
                        String akad12 = task.getResult().getString("12");
                        String akad13 = task.getResult().getString("13");
                        String akad14 = task.getResult().getString("14");
                        String akad15 = task.getResult().getString("15");

                        Log.d("akad1 repo",""+akad1);

                        postEvent(SyaratKetentuanEvents.onGetDataSuccess, null, akad1, akad2, akad3, akad4, akad5, akad6, akad7, akad8, akad9, akad10, akad11, akad12, akad13, akad14, akad15);
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    //postEvent(KilatEventsProfile.onGetDataError, errorMessage);
                }
            }
        });
    }

    @Override
    public void postEvent(int type, String errorMessage, String akad1, String akad2, String akad3, String akad4, String akad5, String akad6, String akad7, String akad8, String akad9, String akad10, String akad11, String akad12, String akad13, String akad14, String akad15) {
        SyaratKetentuanEvents syaratKetentuanEvents = new SyaratKetentuanEvents();
        syaratKetentuanEvents.setEventType(type);
        Log.d("masuk post", "masuk post event succes not null");
//        if (errorMessage == null) {
//            kilatEvents.setErrorMessage(errorMessage);
//        }
        syaratKetentuanEvents.setText1(akad1);
        syaratKetentuanEvents.setText2(akad2);
        syaratKetentuanEvents.setText3(akad3);
        syaratKetentuanEvents.setText4(akad4);
        syaratKetentuanEvents.setText5(akad5);
        syaratKetentuanEvents.setText6(akad6);
        syaratKetentuanEvents.setText7(akad7);
        syaratKetentuanEvents.setText8(akad8);
        syaratKetentuanEvents.setText9(akad9);
        syaratKetentuanEvents.setText10(akad10);
        syaratKetentuanEvents.setText11(akad11);
        syaratKetentuanEvents.setText12(akad12);
        syaratKetentuanEvents.setText13(akad13);
        syaratKetentuanEvents.setText14(akad14);
        syaratKetentuanEvents.setText15(akad15);

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(syaratKetentuanEvents);
    }
}
