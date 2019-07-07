package com.tiunida.laundry0.orderDetail;

import android.util.Log;

import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.orderDetail.events.OrderDetailEvents;
import com.tiunida.laundry0.orderDetail.ui.OrderDetailViewMvp;

import org.greenrobot.eventbus.Subscribe;

public class OrderDetailPresenter implements OrderDetailPresenterMvp {
    private OrderDetailInteractorMvp mOrderDetailInteractorMvp;
    private OrderDetailViewMvp mOrderDetailViewMvp;
    private EventBus mEventBus;
    String string2 = "0";

    public OrderDetailPresenter(OrderDetailViewMvp orderDetailViewMvp) {
        mOrderDetailViewMvp = orderDetailViewMvp;
        mOrderDetailInteractorMvp = new OrderDetailInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    @Subscribe
    public void onEventMainThread(OrderDetailEvents event) {
        switch (event.getEventType()) {
            case OrderDetailEvents.onGetDataSuccess:
                onGedDataSuccess(event.getDataJenis(), event.getDataTimeNow(), event.getDataTimeDone(), event.getDataWeight(), event.getDataPrice(), event.getDataPriceDiskon(), event.getDataDiskon(), event.getDataBandana(), event.getDataTopi(),
                        event.getDataMasker(), event.getDataKupluk(), event.getDataKrudung(), event.getDataPeci(), event.getDataKaos(), event.getDataKaosDalam(),
                        event.getDataKemeja(), event.getDataBajuMuslim(), event.getDataJaket(), event.getDataSweter(), event.getDataGamis(), event.getDataHanduk(),
                        event.getDataSarungTangan(), event.getDataSapuTangan(), event.getDataCelana(), event.getDataCelanaDalam(), event.getDataCelanaPendek(),
                        event.getDataSrung(), event.getDataCelanaOlahraga(), event.getDataRok(), event.getDataCelanaLevis(), event.getDataKaosKaki(), event.getDataJasAlmamater(),
                        event.getDataJas(), event.getDataSelimutBesar(), event.getDataSelimutKecil(), event.getDataBagCover(), event.getDataGordengKecil(), event.getDataGordengBesar(),
                        event.getDataSepatu(), event.getDataBantal(), event.getDataTasKecil(), event.getDataTasBesar(), event.getDataSpreiKecil(), event.getDataSpreiBesar(), event.getDataAccept(), event.getDataOnProses(), event.getDataDone(), event.getDataPaid(), event.getDataDelivered());
                break;
            case OrderDetailEvents.onGetDataError:

                break;
        }
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mOrderDetailViewMvp = null;
        mEventBus.unregister(this);
    }

    @Override
    public void getOrderData(String order_id) {
        mOrderDetailInteractorMvp.getOrderData(order_id);
    }

    @Override
    public void onGedDataSuccess(String jenis,
                                 String timeNow, String timeDpne,
                                 String weight, String price,

                                 String priceDiskon, String diskon,
                                 String dataBandana, String dataTopi,
                                 String dataMasker, String dataKupluk,
                                 String dataKrudung, String dataPeci,

                                 String dataKaos, String dataKaosDalam,
                                 String dataKemeja, String dataBajuMuslim,
                                 String dataJaket, String dataSweter,
                                 String dataGamis, String dataHanduk,

                                 String dataSarungTangan, String dataSapuTangan,

                                 String dataCelana, String dataCelanaDalam,
                                 String dataCelanaPendek, String dataSrung,
                                 String dataCelanaOlahraga, String dataRok,
                                 String dataCelanaLevis, String dataKaosKaki,

                                 String dataJasAlmamater, String dataJas,
                                 String dataSelimutBesar, String dataSelimutKecil,
                                 String dataBagCover, String dataGordengKecil,
                                 String dataGordengBesar, String dataSepatu,
                                 String dataBantal, String dataTasKecil, String dataTasBesar,
                                 String dataSpreiKecil, String dataSpreiBesar,

                                 String dataAccept, String dataOnProses,
                                 String dataDone, String dataPaid,
                                 String delivered) {

        //mOrderDetailViewMvp.showProgress();
        if (mOrderDetailViewMvp != null) {
            setInfo(jenis, timeNow, timeDpne, weight, price, priceDiskon, diskon);
            setIndicator(dataAccept, dataOnProses, dataDone, dataPaid, delivered);
            setHeadCard(dataBandana, dataTopi, dataMasker, dataKupluk, dataKrudung, dataPeci);
            setBodyCard(dataKaos, dataKaosDalam, dataKemeja, dataBajuMuslim, dataJaket, dataSweter, dataGamis, dataHanduk);
            setHandCard(dataSarungTangan, dataSapuTangan);
            setFeetCard(dataCelana, dataCelanaDalam, dataCelanaPendek, dataSrung, dataCelanaOlahraga, dataRok, dataCelanaLevis, dataKaosKaki);
            setOtherCard(dataJasAlmamater, dataJas, dataSelimutBesar, dataSelimutKecil, dataBagCover, dataGordengKecil, dataGordengBesar, dataSepatu, dataBantal, dataTasKecil, dataTasBesar, dataSpreiKecil, dataSpreiBesar);
            mOrderDetailViewMvp.hideProgress();
        }
    }

    public void setInfo(String jenis, String timeNow, String timeDpne, String weight, String price, String priceDiskon, String diskon) {
        if (jenis != null) {
            mOrderDetailViewMvp.setLaundryType(jenis);
        }

        if (timeNow != null) {
            mOrderDetailViewMvp.setTglPesan(timeNow);
        }

        if (timeDpne != null) {
            mOrderDetailViewMvp.setTglSelesai(timeDpne);
        }

        if (weight != null) {
            mOrderDetailViewMvp.setLaundryWeight(weight);
        }

        if (price != null) {
            mOrderDetailViewMvp.setLaundryOriginalPrice(price);
        }

        if (diskon != null) {
            mOrderDetailViewMvp.setLaundryDiskon(diskon);
        }

        if (priceDiskon != null) {
            int priceInt = Integer.valueOf(price);
            int diskonInt = Integer.valueOf(diskon);
            int hasil = priceInt * diskonInt / 100;
            int totalPrice = priceInt - hasil;
            Log.e("total price", "" + priceInt);
            mOrderDetailViewMvp.setLaundryPrice(String.valueOf(totalPrice));
        }
    }

    public void setIndicator(String dataAccept, String dataOnProses, String dataDone, String dataPaid, String delivered) {
        String string1 = "1";
        if (dataAccept.equals(string1)) {
            mOrderDetailViewMvp.setAcceptIndicatorCheck();
        } else {
            mOrderDetailViewMvp.setAcceptIndicatorUnCheck();
        }

        if (dataOnProses.equals(string1)) {
            mOrderDetailViewMvp.setProsesIndicatorCheck();
        } else {
            mOrderDetailViewMvp.setProsesIndicatorUnCheck();
        }

        if (dataDone.equals(string1)) {
            mOrderDetailViewMvp.setDoneIndicatorCheck();
        } else {
            mOrderDetailViewMvp.setProsesIndicatorUnCheck();
        }

        if (dataPaid.equals(string1)) {
            mOrderDetailViewMvp.setPaidIndicatorCheck();
        } else {
            mOrderDetailViewMvp.setPaidIndicatorUnCheck();
        }

        if (delivered.equals(string1)) {
            mOrderDetailViewMvp.setDeliveredIndicatorCheck();
        } else {
            mOrderDetailViewMvp.setDeliveredIndicatorUnCheck();
        }
    }

    public void setHeadCard(String dataBandana, String dataTopi, String dataMasker, String dataKupluk, String dataKrudung, String dataPeci) {
        if (dataBandana.equals(string2)) {
            mOrderDetailViewMvp.setBandanaCardGone();
        } else {
            mOrderDetailViewMvp.setBandanaNumTxt(dataBandana);
        }

        if (dataTopi.equals(string2)) {
            mOrderDetailViewMvp.setTopiCardGone();
        } else {
            mOrderDetailViewMvp.setTopiNumTxt(dataTopi);
        }

        if (dataMasker.equals(string2)) {
            mOrderDetailViewMvp.setMaskerCardGone();
        } else {
            mOrderDetailViewMvp.setMaskerNumTxt(dataMasker);
        }

        if (dataKupluk.equals(string2)) {
            mOrderDetailViewMvp.setKuplukCardGone();
        } else {
            mOrderDetailViewMvp.setKuplukNumTxt(dataKupluk);
        }

        if (dataKrudung.equals(string2)) {
            mOrderDetailViewMvp.setKrudungCardGone();
        } else {
            mOrderDetailViewMvp.setKrudungNumTxt(dataKrudung);
        }

        if (dataPeci.equals(string2)) {
            mOrderDetailViewMvp.setPeciCardGone();
        } else {
            mOrderDetailViewMvp.setPeciNumTxt(dataPeci);
        }
    }

    public void setBodyCard(String dataKaos, String dataKaosDalam, String dataKemeja, String dataBajuMuslim, String dataJaket, String dataSweter, String dataGamis, String dataHanduk) {
        if (dataKaos.equals(string2)) {
            mOrderDetailViewMvp.setKaosCardGone();
        } else {
            mOrderDetailViewMvp.setKaosNumTxt(dataKaos);
        }

        if (dataKaosDalam.equals(string2)) {
            mOrderDetailViewMvp.setKaosDalamCardGone();
        } else {
            mOrderDetailViewMvp.setKaosDalamNumTxt(dataKaosDalam);
        }

        if (dataKemeja.equals(string2)) {
            mOrderDetailViewMvp.setKemajaCardGone();
        } else {
            mOrderDetailViewMvp.setKemejaNumTxt(dataKemeja);
        }

        if (dataBajuMuslim.equals(string2)) {
            mOrderDetailViewMvp.setBajuMuslimCardGone();
        } else {
            mOrderDetailViewMvp.setBajuMuslimNumTxt(dataBajuMuslim);
        }

        if (dataJaket.equals(string2)) {
            mOrderDetailViewMvp.setJaketCardGone();
        } else {
            mOrderDetailViewMvp.setJaketNumTxt(dataJaket);
        }

        if (dataSweter.equals(string2)) {
            mOrderDetailViewMvp.setSweterCardGone();
        } else {
            mOrderDetailViewMvp.setSweterNumTxt(dataSweter);
        }

        if (dataGamis.equals(string2)) {
            mOrderDetailViewMvp.setGamisCardGone();
        } else {
            mOrderDetailViewMvp.setGamisNumTxt(dataGamis);
        }

        if (dataHanduk.equals(string2)) {
            mOrderDetailViewMvp.setHandukCardGone();
        } else {
            mOrderDetailViewMvp.setHandukNumTxt(dataHanduk);
        }
    }

    public void setHandCard(String dataSarungTangan, String dataSapuTangan) {
        if (dataSarungTangan.equals(string2)) {
            mOrderDetailViewMvp.setSarungTanganCardGone();
        } else {
            mOrderDetailViewMvp.setSarungTanganNumTxt(dataSarungTangan);
        }

        if (dataSapuTangan.equals(string2)) {
            mOrderDetailViewMvp.setSapuTanganCardGone();
        } else {
            mOrderDetailViewMvp.setSapuTanganNumTxt(dataSapuTangan);
        }
    }

    public void setFeetCard(String dataCelana, String dataCelanaDalam, String dataCelanaPendek, String dataSrung, String dataCelanaOlahraga, String dataRok, String dataCelanaLevis, String dataKaosKaki) {
        if (dataCelana.equals(string2)) {
            mOrderDetailViewMvp.setCelanaCardGone();
        } else {
            mOrderDetailViewMvp.setCelanaNumTxt(dataCelana);
        }

        if (dataCelanaOlahraga.equals(string2)) {
            mOrderDetailViewMvp.setCelanaOlahragaCardGone();
        } else {
            mOrderDetailViewMvp.setCelanaOlahragaNumTxt(dataCelanaOlahraga);
        }

        if (dataCelanaDalam.equals(string2)) {
            mOrderDetailViewMvp.setCelanaDalamCardGone();
        } else {
            mOrderDetailViewMvp.setCelanaDalamNumTxt(dataCelanaDalam);
        }

        if (dataCelanaPendek.equals(string2)) {
            mOrderDetailViewMvp.setCelanaPendekCardGone();
        } else {
            mOrderDetailViewMvp.setCelanaPendekNumTxt(dataCelanaPendek);
        }

        if (dataSrung.equals(string2)) {
            mOrderDetailViewMvp.setSarungCardGone();
        } else {
            mOrderDetailViewMvp.setSarungNumTxt(dataSrung);
        }

        if (dataRok.equals(string2)) {
            mOrderDetailViewMvp.setRokCardGone();
        } else {
            mOrderDetailViewMvp.setRokNumTxt(dataRok);
        }

        if (dataCelanaLevis.equals(string2)) {
            mOrderDetailViewMvp.setCelanaLevisCardGone();
        } else {
            mOrderDetailViewMvp.setCelanaLevisNumTxt(dataCelanaLevis);
        }

        if (dataKaosKaki.equals(string2)) {
            mOrderDetailViewMvp.setKaosKakiCardGone();
        } else {
            mOrderDetailViewMvp.setKaosKakiNumTxt(dataKaosKaki);
        }
    }

    public void setOtherCard(String dataJasAlmamater, String dataJas, String dataSelimutBesar, String dataSelimutKecil, String dataBagCover, String dataGordengKecil, String dataGordengBesar, String dataSepatu, String dataBantal, String dataTasKecil, String dataTasBesar, String dataSpreiKecil, String dataSpreiBesar) {
        if (dataJasAlmamater.equals(string2)) {
            mOrderDetailViewMvp.setJasAlmamaterCardGone();
        } else {
            mOrderDetailViewMvp.setJasAlmamaterNumTxt(dataJasAlmamater);
        }

        if (dataJas.equals(string2)) {
            mOrderDetailViewMvp.setJasCardGone();
        } else {
            mOrderDetailViewMvp.setJasNumTxt(dataJas);
        }

        if (dataSelimutKecil.equals(string2)) {
            mOrderDetailViewMvp.setSelimutKecilCardGone();
        } else {
            mOrderDetailViewMvp.setSelimutKecilNumTxt(dataSelimutKecil);
        }

        if (dataSelimutBesar.equals(string2)) {
            mOrderDetailViewMvp.setSelimutBesarCardGone();
        } else {
            mOrderDetailViewMvp.setSelimutBesarNumTxt(dataSelimutBesar);
        }

        if (dataBagCover.equals(string2)) {
            mOrderDetailViewMvp.setBagCoverCardGone();
        } else {
            mOrderDetailViewMvp.setBagCoverNumTxt(dataBagCover);
        }

        if (dataGordengKecil.equals(string2)) {
            mOrderDetailViewMvp.setGordengKecilCardGone();
        } else {
            mOrderDetailViewMvp.setGordengKecilNumTxt(dataGordengKecil);
        }

        if (dataGordengBesar.equals(string2)) {
            mOrderDetailViewMvp.setGordengBesarCardGone();
        } else {
            mOrderDetailViewMvp.setGordengBesarNumTxt(dataGordengBesar);
        }

        if (dataSepatu.equals(string2)) {
            mOrderDetailViewMvp.setSepatuCardGone();
        } else {
            mOrderDetailViewMvp.setSepatuNumTxt(dataBagCover);
        }

        if (dataBantal.equals(string2)) {
            mOrderDetailViewMvp.setBantalCardGone();
        } else {
            mOrderDetailViewMvp.setBantalNumTxt(dataBantal);
        }

        if (dataTasKecil.equals(string2)) {
            mOrderDetailViewMvp.setTasKecilCardGone();
        } else {
            mOrderDetailViewMvp.setTaskecilNumTxt(dataBagCover);
        }

        if (dataTasBesar.equals(string2)) {
            mOrderDetailViewMvp.setTasBesarCardGone();
        } else {
            mOrderDetailViewMvp.setTasBesarNumTxt(dataTasBesar);
        }

        if (dataSpreiKecil.equals(string2)) {
            mOrderDetailViewMvp.setSpreiKecilCardGone();
        } else {
            mOrderDetailViewMvp.setSreiKecilNumTxt(dataSpreiKecil);
        }

        if (dataSpreiBesar.equals(string2)) {
            mOrderDetailViewMvp.setSpreiBesarCardGone();
        } else {
            mOrderDetailViewMvp.setSpreiBesarNumTxt(dataSpreiBesar);
        }
    }
}
