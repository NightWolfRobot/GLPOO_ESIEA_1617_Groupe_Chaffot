package com.EggsHunting.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.EggsHunting.model.Child;
import com.EggsHunting.model.Garden;
import com.EggsHunting.model.Orientation;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class DisplayGridSimulation extends GridPane {
	//GridPane GridPane;
	Garden garden;
	private static final Logger log = LoggerFactory.getLogger(DisplayGridSimulation.class);
	
	public DisplayGridSimulation(Garden garden){
		 super();
		 //GridPane.setPrefRows(32);
		 //GridPane.setPrefColumns(32);
		 this.garden = garden;
		 
		 for(int i=0; i<garden.getX()+2; i++){
			 for(int j=0; j<garden.getY()+2; j++){
				 //ImageView iv = new ImageView();
				 String background="";
				 	if(i ==0 || i== garden.getX()+1){
	                    background ="/images/treeside.png";
	                } 
				 	else if(j ==0){
	                    background ="/images/treetop.png";
	                }
				 	else if(j == garden.getY() +1){
	                	background ="/images/treedown.png";
	                }
	                else{
	                	background ="/images/groundg.png";
	                }
				 //iv.setImage(new Image(getClass().getResourceAsStream(background)));
				 this.add(new DisplayCell(new Image(getClass().getResourceAsStream(background))), i, j);
				 //StackPane sp = new StackPane();
				 
				 
			 }
		 }
		 addItems();
	}
	
	public void diplayBoard(){
		for(int i=0; i<garden.getX(); i++){
			for(int j=0; j<garden.getY(); j++){
				//Rectangle r = (Rectangle) this.getChildren().get(boardToPaneCoords(i, j));
                //r.setFill(Color.web("#00FF00"));
			}
		}
	}
	
	public void addItems(){
		for(int i =0; i<garden.getX(); i++){
			for(int j=0; j<garden.getY(); j++){
				if(garden.getCell(i, j).isRock()){
					ImageView iv = new ImageView();
					iv.setImage(new Image(getClass().getResourceAsStream("/images/rock.png")));
					((StackPane)getNodeFromGridPane(i+1,j+1)).getChildren().add(iv);
				 }else if(garden.getCell(i, j).hasEggs()){
					ImageView iv = new ImageView();
					iv.setImage(new Image(getClass().getResourceAsStream("/images/egg.png")));
					log.debug("Egg "+i+" "+j);
					((StackPane)getNodeFromGridPane(i+1,j+1)).getChildren().add(iv);
					Label lbl = new Label(garden.getCell(i, j).getNbEggs()+"");
					lbl.setTextFill(Color.web("#ffffff"));
					((StackPane)getNodeFromGridPane(i+1,j+1)).getChildren().add(lbl);
				 }
			}
		}
		
	}
	
	public void addChildren(){
		for(Child c: garden.getChildren()){
			if(c.getPosition().x >= 0 && c.getPosition().x <= garden.getX() && c.getPosition().y >=0 && c.getPosition().y <= garden.getY()){
				
				DisplayCell sp = (DisplayCell) this.getNodeFromGridPane(c.getPosition().x+1, c.getPosition().y+1);
				ImageView iv = new ImageView();
				String imgPath="/images/child"+c.getColor();
				if(c.getOrientation() == Orientation.NORTH){
					imgPath += "back.png";
				}else if(c.getOrientation() == Orientation.EAST){
					imgPath += "right.png";
				}else if(c.getOrientation() == Orientation.WEST){
					imgPath += "left.png";
				}else if(c.getOrientation() == Orientation.SOUTH){
					imgPath += "front.png";
				}
				
				log.debug(imgPath);
				iv.setImage(new Image(getClass().getResourceAsStream(imgPath)));
				sp.getChildren().add(iv);
				
			}
		}
	}
	
	public void resetCells(){
		this.getChildren().clear();
		for(int i=0; i<garden.getX()+2; i++){
			 for(int j=0; j<garden.getY()+2; j++){
				 //DisplayCell sp = (DisplayCell) this.getNodeFromGridPane(i, j);
				 
				 
				 //ImageView iv = new ImageView();
				 String background="";
				 	if(i ==0 || i== garden.getX()+1){
	                    background ="/images/treeside.png";
	                } 
				 	else if(j ==0){
	                    background ="/images/treetop.png";
	                }
				 	else if(j == garden.getY() +1){
	                	background ="/images/treedown.png";
	                }
	                else{
	                	background ="/images/groundg.png";
	                }
				 //iv.setImage(new Image(getClass().getResourceAsStream(background)));
				 this.add(new DisplayCell(new Image(getClass().getResourceAsStream(background))), i, j);
			 }
		 }
	}
	
	private Node getNodeFromGridPane(int row, int col) {
	    for (Node node : this.getChildren()) {
	        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	        	//log.debug(row+" "+col);
	            return node;
	        }
	    }
	    
	    return null;
	}
	
	/**
	 * @return the garden
	 */
	public Garden getGarden() {
		return garden;
	}

	/**
	 * @param garden the garden to set
	 */
	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	public int boardToPaneCoords(int i, int j) {
        return (i * garden.getY()) + j;
    }
	
}
