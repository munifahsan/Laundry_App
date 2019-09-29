package com.tiunida.laundry0.ActivityHubungi.events;

public class HubungiEvents {
    public static final int onInputError = 0;
    public static final int onInputSuccess = 1;
    public static final int onGetDataSuccess = 2;
    public static final int onGetDataError = 3;

    private int eventType;
    private String errorMessage;
    private String no, desc, email;

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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
