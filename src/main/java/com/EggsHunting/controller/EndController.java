package com.EggsHunting.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.EggsHunting.model.Child;
import com.EggsHunting.view.DisplayCell;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class EndController extends ControlledScreen implements Initializable {
	
	@FXML TableView tableView;
	@FXML LineChart lineChart;
	@FXML ScrollPane scrollPane;
	
	public void goToScreenHome(){
		sm.setScreen(MainApp.screenHomeID);
	}
	
	public void goToScreenBack(){
		sm.setScreen(MainApp.screenSimulation);
	}
	
	/**
	 * @param lap the lap you want the state of
	 * @param result all the events of the simulation
	 * adds the children with no eggs at the end of the simulation
	 */
	public HashMap<String,Integer> eggsAtLap(int lap, ArrayList<ArrayList<Child>> result, ArrayList<Child> children){
	    HashMap<String,Integer> list = new HashMap<>();
	    for(int i=0; i<lap; i++){
	        ArrayList<Child> lapList = result.get(i);
	        for(int j=0; j<lapList.size(); j++){
	            Child child = lapList.get(i);
	            String name = child.getName();
	            if(list.containsKey(name)){
	                int oldValue = list.get(name);
	                list.put(name, ++oldValue);
	            } else { 
	                list.put(name, 1);
	            }
	        }
	    }
	    //adds all the losers to the list
	    addLosers(list, children);
	    return list;
	}
	
	public HashMap<Child, ArrayList<Integer>> getChartValues(){
		HashMap<Child, ArrayList<Integer>> hm = new HashMap<>();
		ArrayList<ArrayList<Child>> list = ((SimulationController)sm.getController(MainApp.screenSimulation)).getResult();
		ArrayList<Child> children = ((SimulationController)sm.getController(MainApp.screenSimulation)).getDisplay().getGarden().getChildren();
		
		for(Child c : children){
			hm.put(c, new ArrayList<>());
		}
		
		for(int i =0; i<list.size(); i++){
			for(Child c : children){
				if(list.get(i).contains(c)){
					hm.get(c).add(1);
				}else{
					hm.get(c).add(0);
				}
				
			}
		}
		
		return hm;
	}

	/**
	 * @param list of children with eggs at the end of the simulation
	 * @param children list of all the children
	 * adds the children with no eggs at the end of the simulation
	 */
	public void addLosers(HashMap<String,Integer> winners, ArrayList<Child> children){
	    for(Child child : children){
	        String name = child.getName();
	        if(!winners.containsKey(name)){
	        	winners.put(name, 0);
	        }
	    }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void updateAfterLoadingScreen() {
        //lineChart = new LineChart<Number,Number>(xAxis,yAxis);
     
        /*
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Enfant1");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        lineChart.getData().add(series);
		*/
	}

	@Override
	public void updateDatas() {
			lineChart.getData().clear();
		    lineChart.setTitle("Oeufs au cours du temps par enfant");
	        HashMap<Child, ArrayList<Integer>> chartValues = getChartValues();
	        HashMap<Child, Integer> resultTable = new HashMap();
	        for (Child c : chartValues.keySet()) {
	        	XYChart.Series series = new XYChart.Series();
	        	series.setName(c.getName());
	        	int cmpt = 0;
	        	ArrayList<Integer> values = chartValues.get(c);
	        	for(int i=0; i<values.size(); i++){
	        		cmpt += values.get(i);
	        		series.getData().add(new XYChart.Data(i+1, cmpt));
	        	}
	        	resultTable.put(c, cmpt);
	        	lineChart.getData().add(series);
	        	
	            //System.out.println("clé: "+mapentry.getKey() + " | valeur: " + mapentry.getValue());
	         }
	        
	        for(Child c: resultTable.keySet()){
	        	//tableView.getColumns().addAll(c.getName(), resultTable.get(c));
	        }
	        
	        GridPane gp = new GridPane();
	       ColumnConstraints col1 = new ColumnConstraints(70);
	       ColumnConstraints col2 = new ColumnConstraints(70);
	       ColumnConstraints col3 = new ColumnConstraints(60);
	       gp.getColumnConstraints().addAll(col1,col2,col3);
	        int cmpt = 0;
	        for(Child c : resultTable.keySet()){
	        	Image img = new Image(getClass().getResourceAsStream("/images/child"+c.getColor()+"front.png"));
	        	gp.add(new DisplayCell(img), 0, cmpt);
	        	gp.add(new Label(c.getName()), 1, cmpt);
	        	
	        	gp.add(new Label(resultTable.get(c)+""), 2, cmpt);
	        	cmpt ++;
	        }
	        
	        scrollPane.setContent(gp);
		
	}
	
}
