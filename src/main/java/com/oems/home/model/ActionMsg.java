package com.oems.home.model;

public class ActionMsg {
    private boolean status;
    public ActionMsg(){
        status = false;
    }
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
