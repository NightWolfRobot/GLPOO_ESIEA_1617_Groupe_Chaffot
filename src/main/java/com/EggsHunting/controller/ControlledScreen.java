package com.EggsHunting.controller;
import org.apache.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public abstract class ControlledScreen {
	private static Logger log = Logger.getLogger(ControlledScreen.class);
    //protected Mediatheque       mediatheque;
    protected ScreenManager    sm;
    
    public void setScreenParent(ScreenManager screenPage) {
        this.sm = screenPage;
    }

    public void setDatas(Object o) {
        //mediatheque = (Mediatheque) o;
        log.info("Datas from controlled screen has been set");
        
    }
    
    public abstract void updateAfterLoadingScreen();
    
    public abstract void updateDatas();
}
