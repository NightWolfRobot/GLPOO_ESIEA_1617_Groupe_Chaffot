package com.EggsHunting.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public abstract class ControlledScreen {
    
    //protected Mediatheque       mediatheque;
    protected ScreenManager    sm;
    
    public void setScreenParent(ScreenManager screenPage) {
        this.sm = screenPage;
    }

    public void setDatas(Object o) {
        //mediatheque = (Mediatheque) o;
        System.out.println("Mediatheque object loaded");
    }
    
    public abstract void updateAfterLoadingScreen();
    
    public abstract void updateDatas();
}
