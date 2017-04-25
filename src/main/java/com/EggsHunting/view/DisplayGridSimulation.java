package com.EggsHunting.view;

import com.EggsHunting.model.Garden;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DisplayGridSimulation {
	GridPane GridPane;
	Garden garden;
	
	public DisplayGridSimulation(Garden garden){
		 GridPane = new GridPane();
		 //GridPane.setPrefRows(32);
		 //GridPane.setPrefColumns(32);
		 this.garden = garden;
		 
		 for(int i=0; i<garden.getX(); i++){
			 for(int j=0; j<garden.getY(); j++){
				 Color c = Color.web("ff0000");
				 Rectangle rectangle = new Rectangle(32,32, c);
				 GridPane.setHgap(2);
			     GridPane.setVgap(2);
				 GridPane.add(rectangle, i, j);
				 //FAIRE UNE CLASSE qui extend stackpane dans lequel on set le fond et on ajoute des images en fonction du contenu
				 // On remplace ensuite le rectangle par le stack et l'image avec les dimensions données.
			 }
		 }
	}
	
	public void diplayBoard(){
		for(int i=0; i<garden.getX(); i++){
			for(int j=0; j<garden.getY(); j++){
				Rectangle r = (Rectangle) GridPane.getChildren().get(boardToPaneCoords(i, j));
                r.setFill(Color.web("#00FF00"));
			}
		}
	}
	
	public int boardToPaneCoords(int i, int j) {
        return (i * garden.getY()) + j;
    }
	
	public Pane getPane(){
		return GridPane;
	}
}
