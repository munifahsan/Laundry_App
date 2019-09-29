package com.tiunida.laundry0.ActivitySyaratKetentuan;

public interface SyaratKetentuanRepositoryMvp {
    void postEvent(int type, String errorMessage, String akad1, String akad2, String akad3, String akad4, String akad5, String akad6, String akad7, String akad8, String akad9, String akad10, String akad11, String akad12, String akad13, String akad14, String akad15);

    void getData();

}
