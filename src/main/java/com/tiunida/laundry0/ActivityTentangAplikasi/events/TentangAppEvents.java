package com.tiunida.laundry0.ActivityTentangAplikasi.events;

public class TentangAppEvents {
    public static final int onInputError = 0;
    public static final int onInputSuccess = 1;
    public static final int onGetDataSuccess = 2;
    public static final int onGetDataError = 3;

    private int eventType;
    private String errorMessage;
    private String tentang;

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
