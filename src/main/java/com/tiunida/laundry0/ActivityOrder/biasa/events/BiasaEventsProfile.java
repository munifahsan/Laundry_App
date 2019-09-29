package com.tiunida.laundry0.ActivityOrder.biasa.events;

public class BiasaEventsProfile {
    public static final int onInputError = 0;
    public static final int onInputSuccess = 1;
    public static final int onGetDataSuccess = 2;
    public static final int onGetDataError = 3;

    private int eventType;
    private String errorMessage;
    private String dataRoom;
    private String dataDormitory;

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

    public String getDataRoom() {
        return dataRoom;
    }

    public void setDataRoom(String dataRoom) {
        this.dataRoom = dataRoom;
    }

    public String getDataDormitory() {
        return dataDormitory;
    }

    public void setDataDormitory(String dataDormitory) {
        this.dataDormitory = dataDormitory;
    }
}
