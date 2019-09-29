package com.tiunida.laundry0.ActivityEditProfile.events;

public class EditProfileEvent{
    public static final int onInputError = 0;
    public static final int onInputSuccess = 1;
    public static final int onFIrestoreError = 2;
    public static final int onGetDataError = 3;
    public static final int onGetDataSuccess = 4;


    private int eventType;
    private String errorMessage;
    private String dataName;
    private String dataNim;
    private String dataDormitory;
    private String dataRoom;
    private String dataPhone;
    private String dataStatus;
    private String dataGender;


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

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataNim() {
        return dataNim;
    }

    public void setDataNim(String dataNim) {
        this.dataNim = dataNim;
    }

    public String getDataDormitory() {
        return dataDormitory;
    }

    public void setDataDormitory(String dataDormitory) {
        this.dataDormitory = dataDormitory;
    }

    public String getDataRoom() {
        return dataRoom;
    }

    public void setDataRoom(String dataRoom) {
        this.dataRoom = dataRoom;
    }

    public String getDataPhone() {
        return dataPhone;
    }

    public void setDataPhone(String dataPhone) {
        this.dataPhone = dataPhone;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getDataGender() {
        return dataGender;
    }

    public void setDataGender(String dataGender) {
        this.dataGender = dataGender;
    }
}
