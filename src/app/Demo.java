package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Demo extends Application {
	
	private static Stage mainStage = new Stage();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mainStage = primaryStage;
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/LogIn.fxml"));
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
			mainStage.setTitle("Social Media Application");
			mainStage.setResizable(false);
			mainStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getMainStage() {
		return mainStage;
	}
}
