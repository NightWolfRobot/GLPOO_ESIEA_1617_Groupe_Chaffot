package com.EggsHunting.view;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class DisplayCell extends StackPane{
	
	public DisplayCell(Image imgbg){
		super();
		ImageView iv = new ImageView();
		iv.setImage(imgbg);
		this.getChildren().add(iv);
	}
}
