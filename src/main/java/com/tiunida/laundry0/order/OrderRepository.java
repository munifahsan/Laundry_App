package com.tiunida.laundry0.order;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firestore.v1.StructuredQuery;
import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.login.events.LoginEvent;
import com.tiunida.laundry0.order.events.OrderEvents;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository implements OrderRepositoryMvp {
    private OrderPresenterMvp mOrderPresenterMvp;
    private FirebaseAuth mAuth;
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String user_id;


    public OrderRepository(){
        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void storeFirestore(String desc, String time,
                                String bandana, String topi, String masker, String kupluk, String krudung, String peci,
                                String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk,
                                String sarung_tangan, String sapu_tangan,
                                String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki,
                                String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {

        Map<String, String> userMap2 = new HashMap<>();
        userMap2.put("1 catatan         ", desc);
        userMap2.put("0 time            ", time);

        userMap2.put("2 bandana         ", bandana);
        userMap2.put("2 topi            ", topi);
        userMap2.put("2 masker          ", masker);
        userMap2.put("2 kupluk          ", kupluk);
        userMap2.put("2 krudung         ", krudung);
        userMap2.put("2 peci            ", peci);

        userMap2.put("3 kaos            ", kaos);
        userMap2.put("3 kaos dalam      ", kaos_dalam);
        userMap2.put("3 kemeja          ", kemeja);
        userMap2.put("3 baju muslim     ", baju_muslim);
        userMap2.put("3 jaket           ", jaket);
        userMap2.put("3 sweter          ", sweter);
        userMap2.put("3 gamis           ", gamis);
        userMap2.put("3 handuk          ", handuk);

        userMap2.put("4 sarung tangan   ", sarung_tangan);
        userMap2.put("4 sapu tangan     ", sapu_tangan);

        userMap2.put("5 celana          ", celana);
        userMap2.put("5 celana dalam    ", celana_dalam);
        userMap2.put("5 celana pendek   ", celana_pendek);
        userMap2.put("5 sarung          ", sarung);
        userMap2.put("5 celana olahraga ", celana_olahraga);
        userMap2.put("5 rok             ", rok);
        userMap2.put("5 celana levis    ", celana_levis);
        userMap2.put("5 kaos kaki       ", kaos_kaki);

        userMap2.put("6 jas almamater   ", jas_almamater);
        userMap2.put("6 jas             ", jas);
        userMap2.put("6 selimut kecil   ", selimut_kecil);
        userMap2.put("6 selimut besar   ", selimut_besar);
        userMap2.put("6 bag cover       ", bag_cover);
        userMap2.put("6 gordeng kecil   ", gordeng_kecil);
        userMap2.put("6 gordeng besar   ", gordeng_besar);
        userMap2.put("6 sepatu          ", sepatu);
        userMap2.put("6 bantal          ", bantal);
        userMap2.put("6 tas kecil       ", tas_kecil);
        userMap2.put("6 tas besar       ", tas_besar);
        userMap2.put("6 sprei kecil     ", sprei_kecil);
        userMap2.put("6 sprei besar     ", sprei_besar);

        firebaseFirestore.collection("Users").document(user_id)
                .collection("order").document().set(userMap2)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            postEvent(OrderEvents.onInputSuccess);
                        }else {
                            String errorMessage = task.getException().getMessage();
                            //Toast.makeText(SetupActivity.this,"FIRESTORE Error :" + errorMessage, Toast.LENGTH_LONG).show();
                            postEvent(OrderEvents.onInputError,errorMessage);
                        }
                    }
                });
    }

    private void postEvent(int type, String errorMessage) {
        OrderEvents orderEvent = new OrderEvents();
        orderEvent.setEventType(type);
        if (errorMessage != null) {
            orderEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(orderEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null);
        Log.d("masuk post","masuk post event");
    }

}
