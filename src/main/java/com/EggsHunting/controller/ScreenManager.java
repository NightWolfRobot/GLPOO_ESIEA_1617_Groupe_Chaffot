package com.EggsHunting.controller;

import java.util.HashMap;

import org.apache.log4j.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ScreenManager extends StackPane{
	private static Logger log = Logger.getLogger(ScreenManager.class);
	private HashMap<String, Node> screens = new HashMap<>();
    private HashMap<String, ControlledScreen> controllers = new HashMap<>();
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ScreenManager(){
        super();
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    //Add the screen to the collection
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }
    
    public void addController(String name, ControlledScreen screen) {
        controllers.put(name, screen);
    }

    //Returns the Node with the appropriate name
    public Node getScreen(String name) {
        return screens.get(name);
    }

    public ControlledScreen getController(String name){
        return controllers.get(name);
    }
    
    //Loads the fxml file, add the screen to the screens collection and
    //finally injects the screenPane to the controller.
    public boolean loadScreen(String name, String resource, Object datas) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenControler = ((ControlledScreen) myLoader.getController()); 
            myScreenControler.setScreenParent(this);
            myScreenControler.setDatas(datas);
            addScreen(name, loadScreen);
            myScreenControler.updateAfterLoadingScreen();
            addController(name, myScreenControler);
            return true;
        } catch (Exception e) {
            log.fatal(e.getMessage()+" Le screen ne s'est pas charge");
            e.printStackTrace(System.out);
            return false;
        }
    }


    public boolean setScreen(String name) {       
        if (screens.get(name) != null) {   //screen loaded
            if (!getChildren().isEmpty()) {    //if there is more than one screen
                getChildren().remove(0);                    //remove the displayed screen
                getChildren().add(0, screens.get(name));     //add the screen
                        
            } else {
                //setOpacity(0.0);
                getChildren().add(screens.get(name));       //no one else been displayed, then just show
            }
            return true;
        } else {
            log.fatal("The screen hasn't been loaded !");
            return false;
        }



    }

    //This method will remove the screen with the given name from the collection of screens
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            log.error("Screen "+ name +" didn't exist");
            return false;
        } else {
            return true;
        }
    }
    
    public void updateDatas(String name){
        getController(name).updateDatas();
    }

}
