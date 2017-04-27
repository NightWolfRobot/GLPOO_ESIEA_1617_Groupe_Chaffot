package com.EggsHunting.controller;

import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.EggsHunting.model.Cell;
import com.EggsHunting.model.Child;
import com.EggsHunting.util.CSVChild;
import com.EggsHunting.util.CSVGarden;
import com.EggsHunting.view.DisplayGridSimulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SimulationController extends ControlledScreen implements Initializable {
	
	@FXML StackPane stackpane;
	
	private DisplayGridSimulation display;
	private Timeline loop = null;
	private static final Logger log = LoggerFactory.getLogger(SimulationController.class);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		display = new DisplayGridSimulation(CSVGarden.getGarden("/csv/garden.csv"));
		initGrid();
	}
	
    @FXML
    public void goToScreenHome(){
    	sm.setScreen(MainApp.screenHomeID);
    }
	
	private void initGrid(){
		stackpane.getChildren().clear();
		stackpane.getChildren().add(new Group(display));
		log.info("Reset grid");
	}
	
	@FXML
	private void resetWorld(){
		display = new DisplayGridSimulation(CSVGarden.getGarden("/csv/garden.csv"));
		initGrid();
		display.addItems();
	}
	
	@FXML
	private void resetGrid(){
		display.resetCells();
		if(display.getGarden().getChildren() != null){
			display.addChildren();
		}
		display.addItems();
		
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
	
	@FXML
	private void handleStop(){
		if (loop != null) {
            loop.stop();
        }
	}
	
	@FXML
	public void handlePlay(){
		loop = new Timeline(new KeyFrame(Duration.millis(300), e -> {
            playOneRound();
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
	}
	
	@FXML
	public void handleLoadGarden(){
		
	}
	
	@FXML
	public void handleLoadChildren(){
		
	}
	
	@FXML
	public void playOneRound() {
		ArrayList<Child> children = display.getGarden().getChildren();
		Cell[][] grid = display.getGarden().getGrid();
		for(Child child : children){
			//check if position is in the grid, if not colliding with a rock and if there are eggs
			Point currentPosition = child.getPosition();
			int posX = (int) currentPosition.getX(), posY = (int) currentPosition.getY();
			
			Point nextPosition = child.getNextPosition();
			if(nextPosition == null){
				log.debug("Next position is null");
				continue;
			}
			
			int nextPosX = (int) nextPosition.getX(), nextPosY = (int) nextPosition.getY();
			if(nextPosX<0 || nextPosY<0 || nextPosX>=grid.length || nextPosY>=grid[0].length){
				log.debug("Children trying to move out of the garden!");
				child.getPath().remove(0);
				child.turn();
				
			} else if(grid[nextPosX][nextPosY].isRock()){
				log.debug("Children trying to move on a rock!");
				child.getPath().remove(0);
				child.turn();
				
			} else {
				if(child.isPickingUpItem()){
					//remove one egg from the Cell add it to the child item list
					child.addItem(grid[posX][posY].removeEgg());
					//set boolean isPickingUpItem to false
					child.donePickingUpItem();
					log.info("Egg picked up");
					
				} else if(grid[nextPosX][nextPosY].hasChild()){
					child.turn();
					log.debug("Children trying to move on another child!");
					continue;
				} else {					
					child.move();
					grid[posX][posY].childLeaving();
					grid[nextPosX][nextPosY].childSteppingIn();
					if(grid[nextPosX][nextPosY].hasEggs()){
						//sets boolean isPickingUpItem to true
						child.pickUpItem();
					}
				}
			}
		}
		log.info("Resetting grid");
		resetGrid();	
		log.info("One step done");
	}
	
	@FXML
	public void saveGame(){
		CSVChild.saveChildren("saveChildren", display.getGarden().getChildren());
        CSVGarden.saveGarden("saveGarden", display.getGarden().getGrid());
        log.info("Done saving");
	}

}
