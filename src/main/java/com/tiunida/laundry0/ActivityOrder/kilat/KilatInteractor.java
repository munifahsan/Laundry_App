package com.tiunida.laundry0.ActivityOrder.kilat;

public class KilatInteractor implements KilatInteractorMvp {
    private KilatRepositoryMvp mKilatRepositoryMvp;

    public KilatInteractor() {
        mKilatRepositoryMvp = new KilatRepository();
    }

    @Override
    public void getAkadData() {
        mKilatRepositoryMvp.getAkadData();
    }

    @Override
    public void getProfileData() {
        mKilatRepositoryMvp.getProfileData();
    }

    @Override
    public void doInputs(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {
        mKilatRepositoryMvp.storeFirestore(desc, time, uniqId, timeDone, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
    }
}
