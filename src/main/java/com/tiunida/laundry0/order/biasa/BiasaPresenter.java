package com.tiunida.laundry0.order.biasa;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.order.biasa.events.BiasaEventsAkad;
import com.tiunida.laundry0.order.biasa.events.BiasaEventsProfile;
import com.tiunida.laundry0.order.biasa.ui.BiasaViewMvp;

import org.greenrobot.eventbus.Subscribe;

public class BiasaPresenter implements BiasaPresenterMvp {
    private BiasaViewMvp mBiasaViewMvp;
    private BiasaInteractorMvp mBiasaInteractorMvp;

    private EventBus mEventBus;

    public BiasaPresenter(BiasaViewMvp orderViewMvp) {
        mBiasaViewMvp = orderViewMvp;
        mBiasaInteractorMvp = new BiasaInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void validateInputs(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {
        String empty = "0";
        if (bandana.equals(empty) && topi.equals(empty) && masker.equals(empty) && kupluk.equals(empty) && krudung.equals(empty) && peci.equals(empty) && kaos.equals(empty) && kaos_dalam.equals(empty) && kemeja.equals(empty) && baju_muslim.equals(empty) && jaket.equals(empty) && sweter.equals(empty) && gamis.equals(empty) && handuk.equals(empty) && sarung_tangan.equals(empty) && sapu_tangan.equals(empty) && celana.equals(empty) && celana_dalam.equals(empty) && celana_pendek.equals(empty) && sarung.equals(empty) && celana_olahraga.equals(empty) && rok.equals(empty) && celana_levis.equals(empty) && kaos_kaki.equals(empty) && jas_almamater.equals(empty) && jas.equals(empty) && selimut_kecil.equals(empty) && selimut_besar.equals(empty) && bag_cover.equals(empty) && gordeng_kecil.equals(empty) && gordeng_besar.equals(empty) && sepatu.equals(empty) && bantal.equals(empty) && tas_kecil.equals(empty) && tas_besar.equals(empty) && sprei_kecil.equals(empty) && sprei_besar.equals(empty)) {
            mBiasaViewMvp.showDialogEmptyData(desc, time, uniqId, timeDone, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
        } else {
            mBiasaViewMvp.showDialogConfirmData(desc, time, uniqId, timeDone, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
        }
    }

    @Override
    public void inputs(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            mBiasaViewMvp.showProgress();
            mBiasaViewMvp.disableInputs();
        }
        mBiasaInteractorMvp.doInputs(desc, time, uniqId, timeDone, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
    }

    @Override
    @Subscribe
    public void onEventMainThread(BiasaEventsProfile event) {
        switch (event.getEventType()) {
            case BiasaEventsProfile.onInputSuccess:
                onInputSuccess();
                break;
            case BiasaEventsProfile.onInputError:

                break;
            case BiasaEventsProfile.onGetDataSuccess:
                onGetDataSuccess(event.getDataRoom(), event.getDataDormitory());
                Log.d("akad1 room", "" + event.getDataRoom());
                break;
            case BiasaEventsProfile.onGetDataError:

                break;
        }
    }

    @Subscribe
    public void onEventMainThread(BiasaEventsAkad event) {
        switch (event.getEventType()) {
            case BiasaEventsAkad.onInputSuccess:

                break;
            case BiasaEventsAkad.onGetDataError:

                break;
            case BiasaEventsAkad.onGetDataSuccess:
                onGetDataSuccess(event.getAkad1(), event.getAkad2(), event.getAkad3(), event.getAkad4(), event.getAkad5(), event.getAkad6(), event.getAkad7(), event.getAkad8(), event.getAkad9(), event.getAkad10(), event.getAkad11(), event.getAkad12(), event.getAkad13(), event.getAkad14(), event.getAkad15());
                Log.d("akad1", "" + event.getAkad1());
                break;
            case BiasaEventsAkad.onInputError:

                break;
        }
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mBiasaViewMvp = null;
        mEventBus.unregister(this);
    }

    @Override
    public void getData() {
        mBiasaInteractorMvp.getProfileData();
        mBiasaInteractorMvp.getAkadData();
    }

    public void onGetDataSuccess(String dataRoom, String dataDormitory) {
        mBiasaViewMvp.setRoomDormitory(dataRoom, dataDormitory);
    }

    public void onGetDataSuccess(String akad1, String akad2, String akad3, String akad4, String akad5, String akad6, String akad7, String akad8, String akad9, String akad10, String akad11, String akad12, String akad13, String akad14, String akad15) {

        if (mBiasaViewMvp != null) {
            mBiasaViewMvp.hideProgress();
            if (!akad1.equals("")) {
                mBiasaViewMvp.setTextAkad1(akad1);
            } else {
                mBiasaViewMvp.setAkad1Gone();
            }

            if (!akad2.equals("")) {
                mBiasaViewMvp.setTextAkad2(akad2);
            } else {
                mBiasaViewMvp.setAkad2Gone();
            }

            if (!akad3.equals("")) {
                mBiasaViewMvp.setTextAkad3(akad3);
            } else {
                mBiasaViewMvp.setAkad3Gone();
            }

            if (!akad4.equals("")) {
                mBiasaViewMvp.setTextAkad4(akad4);
            } else {
                mBiasaViewMvp.setAkad4Gone();
            }

            if (!akad5.equals("")) {
                mBiasaViewMvp.setTextAkad5(akad5);
            } else {
                mBiasaViewMvp.setAkad5Gone();
            }

            if (!akad6.equals("")) {
                mBiasaViewMvp.setTextAkad6(akad6);
            } else {
                mBiasaViewMvp.setAkad6Gone();
            }

            if (!akad7.equals("")) {
                mBiasaViewMvp.setTextAkad7(akad7);
            } else {
                mBiasaViewMvp.setAkad7Gone();
            }

            if (!akad8.equals("")) {
                mBiasaViewMvp.setTextAkad8(akad8);
            } else {
                mBiasaViewMvp.setAkad8Gone();
            }

            if (!akad9.equals("")) {
                mBiasaViewMvp.setTextAkad9(akad9);
            } else {
                mBiasaViewMvp.setAkad9Gone();
            }

            if (!akad10.equals("")) {
                mBiasaViewMvp.setTextAkad10(akad10);
            } else {
                mBiasaViewMvp.setAkad10Gone();
            }

            if (!akad11.equals("")) {
                mBiasaViewMvp.setTextAkad11(akad11);
            } else {
                mBiasaViewMvp.setAkad11Gone();
            }

            if (!akad12.equals("")) {
                mBiasaViewMvp.setTextAkad12(akad12);
            } else {
                mBiasaViewMvp.setAkad12Gone();
            }

            if (!akad13.equals("")) {
                mBiasaViewMvp.setTextAkad13(akad13);
            } else {
                mBiasaViewMvp.setAkad13Gone();
            }

            if (!akad14.equals("")) {
                mBiasaViewMvp.setTextAkad14(akad14);
            } else {
                mBiasaViewMvp.setAkad14Gone();
            }

            if (!akad15.equals("")) {
                mBiasaViewMvp.setTextAkad15(akad15);
            } else {
                mBiasaViewMvp.setAkad15Gone();
            }
        }
    }
    @Override
    public void onInputSuccess() {
        if (mBiasaViewMvp != null) {
            mBiasaViewMvp.navigateToMainScreen();
            mBiasaViewMvp.showMessage("Order berhasil tersimpan");
            Log.d("navig main scrn ", "nice");
        }
    }

    @Override
    public void onInputError(String error) {
        if (mBiasaViewMvp != null) {
            //mBiasaViewMvp.hideProgress();
            //mBiasaViewMvp.enableInputs();
            mBiasaViewMvp.showMessage(error);
        }
    }

}
