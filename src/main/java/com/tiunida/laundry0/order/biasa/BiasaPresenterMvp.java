package com.tiunida.laundry0.order.biasa;

import com.tiunida.laundry0.order.biasa.events.BiasaEventsAkad;
import com.tiunida.laundry0.order.biasa.events.BiasaEventsProfile;

public interface BiasaPresenterMvp {
    void validateInputs(String desc, String time, String uniqId, String timeDone,
                        String bandana, String topi, String masker, String kupluk, String krudung, String peci,
                        String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk,
                        String sarung_tangan, String sapu_tangan,
                        String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki,
                        String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar);

    void inputs(String desc, String time, String uniqId, String timeDone,
                String bandana, String topi, String masker, String kupluk, String krudung, String peci,
                String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk,
                String sarung_tangan, String sapu_tangan,
                String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki,
                String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar);

    void onEventMainThread(BiasaEventsProfile event);

    void onEventMainThread(BiasaEventsAkad event);

    void onCreate();

    void onDestroy();

    void getData();

    void onInputSuccess();

    void onInputError(String error);
}
