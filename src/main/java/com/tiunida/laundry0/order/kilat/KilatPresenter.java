package com.tiunida.laundry0.order.kilat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.order.kilat.events.KilatEvents;
import com.tiunida.laundry0.order.kilat.ui.KilatViewMvp;

import org.greenrobot.eventbus.Subscribe;

public class KilatPresenter implements KilatPresenterMvp {
    private KilatViewMvp mKilatViewMvp;
    private KilatInteractorMvp mKilatInteractorMvp;

    private EventBus mEventBus;

    public KilatPresenter(KilatViewMvp kilatViewMvp) {
        mKilatViewMvp = kilatViewMvp;
        mKilatInteractorMvp = new KilatInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void validateInputs(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {
        String empty = "0";
        if (bandana.equals(empty) && topi.equals(empty) && masker.equals(empty) && kupluk.equals(empty) && krudung.equals(empty) && peci.equals(empty) && kaos.equals(empty) && kaos_dalam.equals(empty) && kemeja.equals(empty) && baju_muslim.equals(empty) && jaket.equals(empty) && sweter.equals(empty) && gamis.equals(empty) && handuk.equals(empty) && sarung_tangan.equals(empty) && sapu_tangan.equals(empty) && celana.equals(empty) && celana_dalam.equals(empty) && celana_pendek.equals(empty) && sarung.equals(empty) && celana_olahraga.equals(empty) && rok.equals(empty) && celana_levis.equals(empty) && kaos_kaki.equals(empty) && jas_almamater.equals(empty) && jas.equals(empty) && selimut_kecil.equals(empty) && selimut_besar.equals(empty) && bag_cover.equals(empty) && gordeng_kecil.equals(empty) && gordeng_besar.equals(empty) && sepatu.equals(empty) && bantal.equals(empty) && tas_kecil.equals(empty) && tas_besar.equals(empty) && sprei_kecil.equals(empty) && sprei_besar.equals(empty)) {
            mKilatViewMvp.showDialogEmptyData(desc, time, uniqId, timeDone, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
        } else {
            mKilatViewMvp.showDialogConfirmData(desc, time, uniqId, timeDone, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
        }
    }

    @Override
    public void inputs(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            mKilatInteractorMvp.doInputs(desc, time, uniqId, timeDone, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(KilatEvents event) {
        switch (event.getEventType()) {
            case KilatEvents.onInputSuccess:
                onInputSuccess();
                break;
            case KilatEvents.onGetDataSuccess:
                onGetDataProfileSuccess(event.getDataRoom(), event.getDataDormitory());
                onGetDataAkadSuccess(event.getAkad1(),event.getAkad2(),event.getAkad3(),event.getAkad4(),event.getAkad5(),event.getAkad6(),event.getAkad7(),event.getAkad8(),event.getAkad9(),event.getAkad10(),event.getAkad11(),event.getAkad12(),event.getAkad13(),event.getAkad14(),event.getAkad15());
                break;
        }
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mKilatViewMvp = null;
        mEventBus.unregister(this);
    }

    @Override
    public void getProfileData() {
        mKilatInteractorMvp.getProfileData();
    }

    @Override
    public void getAkadData(){
        mKilatInteractorMvp.getAkadData();
    }

    @Override
    public void onGetDataProfileSuccess(String dataRoom, String dataDormitory) {
        mKilatViewMvp.setRoomDormitory(dataRoom, dataDormitory);
    }

    public void onGetDataAkadSuccess(String akad1, String akad2, String akad3, String akad4, String akad5, String akad6, String akad7, String akad8, String akad9, String akad10, String akad11, String akad12, String akad13, String akad14, String akad15){

    }

    @Override
    public void onInputSuccess() {
        if (mKilatViewMvp != null) {
            mKilatViewMvp.navigateToMainScreen();
            mKilatViewMvp.showMessage("Order berhasil tersimpan");
        }
    }

    @Override
    public void onInputError(String error) {
        if (mKilatViewMvp != null) {
            mKilatViewMvp.showMessage(error);
        }
    }
}
