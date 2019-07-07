package com.tiunida.laundry0.order.express;

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
import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.order.express.events.ExpressEvents;

import java.util.HashMap;
import java.util.Map;

public class ExpressRepository implements ExpressRepositoryMvp {

    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String user_id;

    public ExpressRepository() {
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
                        String room = task.getResult().getString("3 room");
                        String dormitory = task.getResult().getString("2 dormitory");
                        String test = task.getResult().getString("done");

                        Log.d("dona data ", "" + test);

                        postEvent(ExpressEvents.onGetDataSuccess, null, dormitory, room);
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(ExpressEvents.onGetDataError, errorMessage);
                }
            }
        });
    }

    @Override
    public void storeFirestore(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {
        Map<String, Object> userMap2 = new HashMap<>();
        int uniqTimeId = Integer.valueOf(uniqId);
        userMap2.put("a_user_id", user_id);
        userMap2.put("a_catatan", desc);
        userMap2.put("a_uniq_id",uniqTimeId);
        userMap2.put("a_time", time);
        userMap2.put("a_jenis", "Kilat");
        userMap2.put("a_waktu_selesai", timeDone);
        userMap2.put("a_weight", "--");
        userMap2.put("a_price2", "0");
        userMap2.put("a_price_diskon", "0");
        userMap2.put("a_diskon", "0");

        userMap2.put("b_bandana", bandana);
        userMap2.put("b_topi", topi);
        userMap2.put("b_masker", masker);
        userMap2.put("b_kupluk", kupluk);
        userMap2.put("b_krudung", krudung);
        userMap2.put("b_peci", peci);

        userMap2.put("c_kaos", kaos);
        userMap2.put("c_kaos_dalam", kaos_dalam);
        userMap2.put("c_kemeja", kemeja);
        userMap2.put("c_baju_muslim", baju_muslim);
        userMap2.put("c_jaket", jaket);
        userMap2.put("c_sweter", sweter);
        userMap2.put("c_gamis", gamis);
        userMap2.put("c_handuk", handuk);

        userMap2.put("d_sarung_tangan", sarung_tangan);
        userMap2.put("d_sapu_tangan", sapu_tangan);

        userMap2.put("f_celana", celana);
        userMap2.put("f_celana_dalam", celana_dalam);
        userMap2.put("f_celana_pendek", celana_pendek);
        userMap2.put("f_sarung", sarung);
        userMap2.put("f_celana_olahraga", celana_olahraga);
        userMap2.put("f_rok", rok);
        userMap2.put("f_celana_evis", celana_levis);
        userMap2.put("f_kaos_kaki", kaos_kaki);

        userMap2.put("g_jas_almamater", jas_almamater);
        userMap2.put("g_jas", jas);
        userMap2.put("g_selimut_kecil", selimut_kecil);
        userMap2.put("g_selimut_besar", selimut_besar);
        userMap2.put("g_bag_cover", bag_cover);
        userMap2.put("g_gordeng_kecil", gordeng_kecil);
        userMap2.put("g_gordeng_besar", gordeng_besar);
        userMap2.put("g_sepatu", sepatu);
        userMap2.put("g_bantal", bantal);
        userMap2.put("g_tas_kecil", tas_kecil);
        userMap2.put("g_tas_besar", tas_besar);
        userMap2.put("g_sprei_kecil", sprei_kecil);
        userMap2.put("g_sprei_besar", sprei_besar);

        userMap2.put("h_accepted2", "");
        userMap2.put("h_on_proses2", "");
        userMap2.put("h_done2", "");
        userMap2.put("h_paid2", "");
        userMap2.put("h_delivered2", "");

        firebaseFirestore.collection("Orders").add(userMap2).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Log.d("masuk isSuccess ", " ya");
                    postEvent(ExpressEvents.onInputSuccess);
                } else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(ExpressEvents.onInputError, errorMessage);
                    Log.d("error nya ", "" + errorMessage);
                }
            }
        });
    }

    @Override
    public void postEvent(int type, String errorMessage, String dataRoom, String dataDormitory) {
        ExpressEvents expressEvents = new ExpressEvents();
        expressEvents.setEventType(type);
        Log.d("masuk post", "masuk post event succes not null");
        if (errorMessage == null && dataRoom != null && dataDormitory != null) {
            expressEvents.setErrorMessage(errorMessage);
            expressEvents.setDataRoom(dataRoom);
            expressEvents.setDataDormitory(dataDormitory);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(expressEvents);
    }

    @Override
    public void postEvent(int type, String errorMessage) {
        postEvent(type, errorMessage, null, null);
    }

    @Override
    public void postEvent(int type) {
        postEvent(type, null, null, null);
    }
}
