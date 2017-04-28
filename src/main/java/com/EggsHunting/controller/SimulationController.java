package com.EggsHunting.controller;

import java.awt.Point;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.EggsHunting.model.Cell;
import com.EggsHunting.model.Child;
import com.EggsHunting.model.Garden;
import com.EggsHunting.util.CSVChild;
import com.EggsHunting.util.CSVGarden;
import com.EggsHunting.view.DisplayGridSimulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

public class SimulationController extends ControlledScreen implements Initializable {
	
	@FXML StackPane stackpane;
	
	private DisplayGridSimulation display;
	/**
	 * @return the display
	 */
	public DisplayGridSimulation getDisplay() {
		return display;
	}

	/**
	 * @param display the display to set
	 */
	public void setDisplay(DisplayGridSimulation display) {
		this.display = display;
	}

	private Timeline loop = null;
	private FileChooser fileChooser = new FileChooser();
	private static final Logger log = LoggerFactory.getLogger(SimulationController.class);
	private ArrayList<ArrayList<Child>> result = new ArrayList<>();

	/**
	 * @return the result
	 */
	public ArrayList<ArrayList<Child>> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(ArrayList<ArrayList<Child>> result) {
		this.result = result;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		display = new DisplayGridSimulation(CSVGarden.getGarden("/csv/garden.csv"));
		initGrid();
		loadChildren();
	}
	
    @FXML
    public void goToScreenHome(){
    	sm.setScreen(MainApp.screenHomeID);
    }
    
    public void goToScreenEnd(){
    	sm.setScreen(MainApp.screenEnd);
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
		fileChooser.setTitle("Select a file");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("CSV files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String path = selectedFile.getPath();
            log.debug("Path selected from fileChooser: "+path);
            display = new DisplayGridSimulation(CSVGarden.getGardenFromPath(path));
    		initGrid();
    		display.addItems();
            
        }
	}
	
	@FXML
	public void handleLoadChildren(){
		fileChooser.setTitle("Select a file");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("CSV files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String path = selectedFile.getPath();
            log.debug("Path selected from fileChooser: "+path);
            stackpane.getChildren().clear();
    		stackpane.getChildren().add(new Group(display));
    		display.diplayBoard();
    		ArrayList<Child> ac = CSVChild.getChildrenFromFile(path);
    		display.getGarden().setChildren(ac);
    		display.addChildren();
            
        }
	}
	
	@FXML
	public void playOneRound() {
		ArrayList<Child> children = display.getGarden().getChildren();
		Cell[][] grid = display.getGarden().getGrid();
		ArrayList<Child> gagnants = new ArrayList<>();
		result.add(gagnants);
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
					gagnants.add(child);
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
		if(isSimulationEnded()){
			handleStop();
			goToScreenEnd();
			sm.getController(MainApp.screenEnd).updateDatas();
			log.debug("call update after loading");
		}
	}
	
	public boolean isSimulationEnded(){
		if(lockedChildren() || hasNoMoreEggs()){
			return true;
		}
		return false;
	}
	
	public boolean lockedChildren(){
		Garden g = display.getGarden();
		for(Child c : g.getChildren()){
			if(!c.getPath().isEmpty()){
				return false;
			}
		}
		return true;
	}
	
	public boolean hasNoMoreEggs(){
		Garden g = display.getGarden();
		for(int i=0; i<g.getX(); i++){
			for(int j=0; j<g.getY(); j++){
				if(g.getCell(i, j).hasEggs()){
					return false;
				}
			}
		}
		return true;
	}
	
	@FXML
	public void saveGame(){
		
		fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("CSV files", "*.csv"));
        fileChooser.setInitialFileName("save.csv");
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            String path = selectedFile.getPath();
            //SaveManager.saveBoard(path, board);
            String str = path.substring(0, path.length()-4);
            //log.info(str);
            CSVChild.saveChildren(str+"_children.csv", display.getGarden().getChildren());
            CSVGarden.saveGarden(str+"_garden.csv", display.getGarden().getGrid());
        }
		
		//CSVChild.saveChildren("saveChildren", display.getGarden().getChildren());
        //CSVGarden.saveGarden("saveGarden", display.getGarden().getGrid());
        log.info("Done saving");
	}

}
