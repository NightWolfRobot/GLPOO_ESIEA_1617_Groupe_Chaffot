package com.EggsHunting.controller;
import org.apache.log4j.Logger;



public abstract class ControlledScreen {
	private static Logger log = Logger.getLogger(ControlledScreen.class);
    //Here you can declare your object of datas you want to share with all your controllers
    protected ScreenManager    sm;
    
    public void setScreenParent(ScreenManager screenPage) {
        this.sm = screenPage;
    }

    public void setDatas(Object o) {
        //Here you set your object of datas by casting the object received
        log.info("Datas from controlled screen has been set");
        
    }
    
    public abstract void updateAfterLoadingScreen();
    
    public abstract void updateDatas();
}
