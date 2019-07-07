package com.tiunida.laundry0.order.biasa;

import android.util.Log;

import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.order.biasa.events.BiasaEvents;
import com.tiunida.laundry0.order.biasa.ui.BiasaViewMvp;

import org.greenrobot.eventbus.Subscribe;

public class BiasaPresenter implements BiasaPresenterMvp {
    private BiasaViewMvp mOrderViewMvp;
    private BiasaInteractorMvp mOrderInteractorMvp;

    private EventBus mEventBus;

    public BiasaPresenter(BiasaViewMvp orderViewMvp) {
        mOrderViewMvp = orderViewMvp;
        mOrderInteractorMvp = new BiasaInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void validateInputs(String desc, String time, String uniqId, String timeDone,
                               String bandana, String topi, String masker, String kupluk, String krudung, String peci,
                               String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk,
                               String sarung_tangan, String sapu_tangan,
                               String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki,
                               String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {

        String empty = "0";
        if (bandana.equals(empty) && topi.equals(empty) && masker.equals(empty) && kupluk.equals(empty) && krudung.equals(empty) && peci.equals(empty) && kaos.equals(empty) && kaos_dalam.equals(empty) && kemeja.equals(empty) && baju_muslim.equals(empty) && jaket.equals(empty) && sweter.equals(empty) && gamis.equals(empty) && handuk.equals(empty) && sarung_tangan.equals(empty) && sapu_tangan.equals(empty) && celana.equals(empty) && celana_dalam.equals(empty) && celana_pendek.equals(empty) && sarung.equals(empty) && celana_olahraga.equals(empty) && rok.equals(empty) && celana_levis.equals(empty) && kaos_kaki.equals(empty) && jas_almamater.equals(empty) && jas.equals(empty) && selimut_kecil.equals(empty) && selimut_besar.equals(empty) && bag_cover.equals(empty) && gordeng_kecil.equals(empty) && gordeng_besar.equals(empty) && sepatu.equals(empty) && bantal.equals(empty) && tas_kecil.equals(empty) && tas_besar.equals(empty) && sprei_kecil.equals(empty) && sprei_besar.equals(empty)) {
            mOrderViewMvp.showMessage("Anda belum memasukan data apapun");
        } else {
            mOrderInteractorMvp.doInputs(desc, time, uniqId, timeDone, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
        }
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mOrderViewMvp = null;
        mEventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(BiasaEvents events) {
        switch (events.getEventType()) {
            case BiasaEvents.onInputSuccess:
                Log.d("masuk onInputSucces ", "ya");
                onInputSuccess();
                break;
            case BiasaEvents.onInputError:

                break;
            case BiasaEvents.onGetDataSuccess:
                onGedDataSuccess(events.getDataRoom(), events.getDataDormitory());
                break;
        }
    }

    @Override
    public void getProfileData() {
        mOrderInteractorMvp.getData();
    }

    @Override
    public void onGedDataSuccess(String dataRoom, String dataDormitory) {
        mOrderViewMvp.setRoomDormitory(dataRoom, dataDormitory);
    }

    @Override
    public void onInputSuccess() {
        if (mOrderViewMvp != null) {
            mOrderViewMvp.navigateToMainScreen();
            mOrderViewMvp.showMessage("Order berhasil tersimpan");
            Log.d("navig main scrn ", "nice");
        }
    }

    @Override
    public void onInputError(String error) {
        if (mOrderViewMvp != null) {
            //mOrderViewMvp.hideProgress();
            //mOrderViewMvp.enableInputs();
            mOrderViewMvp.showMessage(error);
        }
    }

}
