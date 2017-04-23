package com.EggsHunting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.EggsHunting.util.CSVChild;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    public static String screenHomeID = "Hello";
    public static String screenHomeFile = "/fxml/hello.fxml";

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

    	ScreenManager mainContainer = new ScreenManager();

    	mainContainer.loadScreen(MainApp.screenHomeID, MainApp.screenHomeFile, null);
    	mainContainer.setScreen(MainApp.screenHomeID);
    	
    	Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setTitle("Home");
        //stage.getIcons().add(new Image("file:img/icon.png"));
        stage.setScene(scene);
        stage.show();
    	
        CSVChild csv = new CSVChild();
        csv.getChildren("/csv/children.csv");
        log.info("Starting Hello JavaFX and Maven demonstration application");

    }
}
