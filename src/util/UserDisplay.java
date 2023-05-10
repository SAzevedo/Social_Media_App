package util;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import model.User;
import model.UserList;

public class UserDisplay {

	private ChangeScene cs = new ChangeScene();
	private static UserList userList = UserList.getInstance();
	private static HBox view;

	public UserDisplay(User u) {

//		Create Elements
		DropShadow shadow = new DropShadow();
		Label user = new Label(u.getUsername());
		user.setStyle("-fx-text-fill: white; -fx-font-size: 24px;");
		user.setEffect(shadow);
		user.setAlignment(Pos.CENTER);

//		Create ViewBox
		view = new HBox();
//		view.setMaxSize(585, 50);
		view.getChildren().add(user);
		view.setAlignment(Pos.CENTER);
		view.setOnMouseClicked(event -> {
			userList.setClicked(u);
			cs.changeScene("User.fxml");
		});

	}

	public HBox getUserDisplay() {
		return view;
	}

}
