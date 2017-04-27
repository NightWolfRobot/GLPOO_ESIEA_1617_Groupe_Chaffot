package com.EggsHunting.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.EggsHunting.model.Child;
import com.EggsHunting.util.CSVChild;
import com.EggsHunting.util.CSVGarden;
import com.EggsHunting.view.DisplayGridSimulation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;

public class SimulationController extends ControlledScreen implements Initializable {
	
	@FXML StackPane stackpane;
	
	private DisplayGridSimulation display;
	private static final Logger log = LoggerFactory.getLogger(SimulationController.class);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		display = new DisplayGridSimulation(CSVGarden.getGarden("/csv/garden.csv"));
		initGrid();
	}
	
	private void initGrid(){
		stackpane.getChildren().clear();
		stackpane.getChildren().add(new Group(display));
		log.info("Reset grid");
	}
	
	@FXML
	private void resetGrid(){
		display.resetCells();
		display.addItems();
		display.addChildren();
	}
	
	@FXML
	private void loadChildren(){
		stackpane.getChildren().clear();
		stackpane.getChildren().add(new Group(display));
		display.diplayBoard();
		ArrayList<Child> ac = CSVChild.getChildren("/csv/children.csv");
		display.getGarden().setChildren(ac);
		display.addChildren();
		log.info("Load and display childs");
	}
	
	@FXML
	private void addItems(){
		display.addItems();
	}

	@Override
	public void updateAfterLoadingScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDatas() {
		// TODO Auto-generated method stub

	}

}
