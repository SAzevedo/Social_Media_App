package util;

import java.io.IOException;
import app.Demo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ChangeScene {
	
//	Changing Scene
	public void changeScene(String fxml){
		try {
			Parent pane = FXMLLoader.load(
			       getClass().getResource("/views/" + fxml));
			Demo.getMainStage().getScene().setRoot(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	}

}
