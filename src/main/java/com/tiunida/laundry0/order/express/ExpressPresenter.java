package com.tiunida.laundry0.order.express;

import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.order.express.events.ExpressEvents;
import com.tiunida.laundry0.order.express.ui.ExpressViewMvp;

import org.greenrobot.eventbus.Subscribe;

public class ExpressPresenter implements ExpressPresenterMvp {

    private ExpressViewMvp mExpressViewMvp;
    private ExpressInteractorMvp mExpressInteractorMvp;

    private EventBus mEventBus;

    public ExpressPresenter(ExpressViewMvp expressViewMvp) {
        mExpressViewMvp = expressViewMvp;
        mExpressInteractorMvp = new ExpressInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void validateInputs(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {
        String empty = "0";
        if (bandana.equals(empty) && topi.equals(empty) && masker.equals(empty) && kupluk.equals(empty) && krudung.equals(empty) && peci.equals(empty) && kaos.equals(empty) && kaos_dalam.equals(empty) && kemeja.equals(empty) && baju_muslim.equals(empty) && jaket.equals(empty) && sweter.equals(empty) && gamis.equals(empty) && handuk.equals(empty) && sarung_tangan.equals(empty) && sapu_tangan.equals(empty) && celana.equals(empty) && celana_dalam.equals(empty) && celana_pendek.equals(empty) && sarung.equals(empty) && celana_olahraga.equals(empty) && rok.equals(empty) && celana_levis.equals(empty) && kaos_kaki.equals(empty) && jas_almamater.equals(empty) && jas.equals(empty) && selimut_kecil.equals(empty) && selimut_besar.equals(empty) && bag_cover.equals(empty) && gordeng_kecil.equals(empty) && gordeng_besar.equals(empty) && sepatu.equals(empty) && bantal.equals(empty) && tas_kecil.equals(empty) && tas_besar.equals(empty) && sprei_kecil.equals(empty) && sprei_besar.equals(empty)) {
            mExpressViewMvp.showMessage("Anda belum memasukan data apapun");
        } else {
            mExpressInteractorMvp.doInputs(desc, time, uniqId, timeDone, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(ExpressEvents event) {
        switch (event.getEventType()) {
            case ExpressEvents.onInputSuccess:
                onInputSuccess();
                break;
            case ExpressEvents.onGetDataSuccess:
                onGedDataSuccess(event.getDataRoom(), event.getDataDormitory());
                break;
        }
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mExpressViewMvp = null;
        mEventBus.unregister(this);
    }

    @Override
    public void getProfileData() {
        mExpressInteractorMvp.getData();
    }

    @Override
    public void onGedDataSuccess(String dataRoom, String dataDormitory) {
        mExpressViewMvp.setRoomDormitory(dataRoom, dataDormitory);
    }

    @Override
    public void onInputSuccess() {
        if (mExpressViewMvp != null) {
            mExpressViewMvp.navigateToMainScreen();
            mExpressViewMvp.showMessage("Order Berhasil Tersimpan");
        }
    }

    @Override
    public void onInputError(String error) {
        if (mExpressViewMvp != null) {
            mExpressViewMvp.showMessage(error);
        }
    }
}
