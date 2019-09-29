package com.tiunida.laundry0.ActivitySaran.saran;

public class SaranEvents {
    public static final int onInputError = 0;
    public static final int onInputSuccess = 1;
    public static final int onGetDataSuccess = 2;
    public static final int onGetDataError = 3;

    private int eventType;
    private String errorMessage;
    private String a_name;

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

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }
}
