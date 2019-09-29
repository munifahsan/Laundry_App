package com.tiunida.laundry0.ActivityOrderDetail;

public interface OrderDetailReposritoryMvp {
    void getUserData();
    void getOrdersData(String order_id);
    void postEvent(int type, String errorMessage, String jenis, String desc, String timeNow, String timeDpne, String weight, String price, String priceDiskon, String diskon, String dataBandana, String dataTopi, String dataMasker, String dataKupluk, String dataKrudung, String dataPeci,
                   String dataKaos, String dataKaosDalam, String dataKemeja, String dataBajuMuslim, String dataJaket, String dataSweter, String dataGamis,
                   String dataHanduk,
                   String dataSarungTangan, String dataSapuTangan,
                   String dataCelana, String dataCelanaDalam, String dataCelanaPendek, String dataSrung, String dataCelanaOlahraga, String dataRok,
                   String dataCelanaLevis, String dataKaosKaki,
                   String dataJasAlmamater, String dataJas, String dataSelimutBesar, String dataSelimutKecil, String dataBagCover,
                   String dataGordengKecil, String dataGordengBesar, String dataSepatu, String dataBantal, String dataTasKecil, String dataTasBesar,
                   String dataSpreiKecil, String dataSpreiBesar, String dataAccept, String dataOnProses, String dataDone, String dataPaid, String delivered);
    void postEvent(int type, String errorMessage);
    void postEvent(int type);
}
