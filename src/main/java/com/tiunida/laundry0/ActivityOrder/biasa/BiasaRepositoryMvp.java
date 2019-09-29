package com.tiunida.laundry0.ActivityOrder.biasa;

public interface BiasaRepositoryMvp {

    void getProfileData();

    void getAkadData();

    void storeFirestore(String desc, String time, String uniqId, String timeDone,
                        String bandana, String topi, String masker, String kupluk, String krudung, String peci,
                        String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk,
                        String sarung_tangan, String sapu_tangan,
                        String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki,
                        String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar);

    void postEvent(int type, String errorMessage, String dataRoom, String dataDormitory);

    void postEvent(int type, String errorMessage, String akad1, String akad2, String akad3, String akad4, String akad5, String akad6, String akad7, String akad8, String akad9, String akad10, String akad11, String akad12, String akad13, String akad14, String akad15);

    void postEvent(int type, String errorMessage);

    void postEvent(int type);
}
