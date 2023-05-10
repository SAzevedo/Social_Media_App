package util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Post;
import model.PostList;
import model.User;
import model.UserList;

public class PostDisplay {

	private ChangeScene cs = new ChangeScene();
	private static PostList postList = PostList.getInstance();
	private static UserList userList = UserList.getInstance();
	private static User activeUser = userList.getActive();;
	private static HBox view;

	public PostDisplay(Post p) {

//		Creating Elements
		Hyperlink name = new Hyperlink(p.getCreator().getUsername());
		name.setAlignment(Pos.CENTER);
		Hyperlink content = new Hyperlink(p.getContent());
		content.setAlignment(Pos.CENTER);
		content.setWrapText(true);
		Label date = new Label(p.getDate().toString());
		date.setAlignment(Pos.CENTER);
		Label time = new Label(p.getTime() + "");
		time.setAlignment(Pos.CENTER);
		time.setWrapText(true);

//		Post Creator Box
		VBox box1 = new VBox();
		box1.setPrefSize(90, 100);
		box1.getChildren().addAll(name);
		box1.setAlignment(Pos.CENTER);
		box1.setStyle("-fx-background-color: white");
		name.setOnAction(e -> {
			if (p.getCreator().getUsername().equals(activeUser.getUsername())) {
				userList.setClicked(userList.get(activeUser));
			} else {
				userList.setClicked(userList.get(p.getCreator()));
			}
			cs.changeScene("User.fxml");
		});

//		Post Content Box
		VBox box2 = new VBox();
		box2.setPrefSize(360, 100);
		box2.getChildren().add(content);
		box2.setAlignment(Pos.CENTER);
		box2.setStyle("-fx-background-color: white");
		content.setOnAction(e -> {
			postList.setClicked(postList.getPost(p));
			cs.changeScene("Post.fxml");
		});

//		Post Date and Time Box
		VBox box3 = new VBox();
		box3.setPrefSize(90, 100);
		box3.getChildren().addAll(date, time);
		box3.setAlignment(Pos.CENTER);
		box3.setStyle("-fx-background-color: white");

		view = new HBox(10);
		view.setPrefSize(580, 100);
		view.setStyle("-fx-background-color: teal");
		Insets in = new Insets(10, 0, 10, 10);
		view.setPadding(in);
		view.getChildren().addAll(box1, box2, box3);

	}

	public HBox getPostDisplay() {
		return view;
	}

}
