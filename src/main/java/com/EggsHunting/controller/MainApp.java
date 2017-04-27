package com.EggsHunting.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.EggsHunting.model.Child;
import com.EggsHunting.model.Garden;
import com.EggsHunting.util.CSVChild;
import com.EggsHunting.util.CSVGarden;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    public static String screenHomeID = "Hello";
    public static String screenHomeFile = "/fxml/main.fxml";
    public static String screenSimulation ="Simulation";
    public static String screenSimulationFile="/fxml/simulation.fxml";
    

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

    	ScreenManager mainContainer = new ScreenManager();

    	mainContainer.loadScreen(MainApp.screenHomeID, MainApp.screenHomeFile, null);
    	mainContainer.loadScreen(screenSimulation, screenSimulationFile, null);
    	mainContainer.setScreen(MainApp.screenHomeID);
    	
    	Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setTitle("EggsHunting Simulator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logoIcon64.png")));
        scene.getStylesheets().addAll(this.getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    	/*
        ArrayList<Child> children = CSVChild.getChildren("/csv/children.csv");
        log.info("Starting Hello JavaFX and Maven demonstration application");
        Garden g = CSVGarden.getGarden("/csv/garden.csv");
        */
    }
}
