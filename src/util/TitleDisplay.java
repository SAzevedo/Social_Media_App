package util;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TitleDisplay {
	
	private static HBox view;
	
	public TitleDisplay() {
		
		DropShadow shadow = new DropShadow();
		
//		Create Labels
		Label userLbl = new Label("Posted By");
		userLbl.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
	    userLbl.setEffect(shadow);
		userLbl.setAlignment(Pos.CENTER_LEFT);
		
		Label titleLbl = new Label("Original Post");
		titleLbl.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
	    titleLbl.setEffect(shadow);
		titleLbl.setAlignment(Pos.CENTER);
		
		Label dateLbl = new Label("Date Posted");
		dateLbl.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
	    dateLbl.setEffect(shadow);
		dateLbl.setAlignment(Pos.CENTER_RIGHT);
		
//		Create Boxes
		VBox box1 = new VBox();
		box1.setPrefSize(90, 20);
		box1.setAlignment(Pos.CENTER_LEFT);
		box1.getChildren().add(userLbl);
		
		VBox box2 = new VBox();
		box2.setPrefSize(360, 20);
		box2.setAlignment(Pos.CENTER);
		box2.getChildren().add(titleLbl);
		
		VBox box3 = new VBox();
		box3.setPrefSize(90, 20);
		box3.setAlignment(Pos.CENTER_RIGHT);
		box3.getChildren().add(dateLbl);
		
		view = new HBox(10);
		view.setPrefSize(200, 20);
		view.setAlignment(Pos.CENTER);
		view.getChildren().addAll(box1, box2, box3);
	}
	
	public HBox getTitleDisplay() {
		return view;
	}

}
