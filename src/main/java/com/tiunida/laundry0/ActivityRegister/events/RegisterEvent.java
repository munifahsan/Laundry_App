package com.tiunida.laundry0.ActivityRegister.events;

public class RegisterEvent {
    public static final int onSignUpError = 0;
    public static final int onSignUpSuccess = 1;

    private int eventType;
    private String errorMessage;

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
