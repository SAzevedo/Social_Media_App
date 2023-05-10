package controls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Post;
import model.PostList;
import model.User;
import model.UserList;
import util.Backup;
import util.ChangeScene;
import util.Utility;

public class NewPostController implements Initializable {

	private ChangeScene cs;
	private static PostList postList;
	private static UserList userList;
	private static User activeUser;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button exitBtn;

	@FXML
	private TextArea postArea;

	@FXML
	private Button submitBtn;

	@FXML
	private Label userLbl;

	@FXML
	void cancelBtnClicked(ActionEvent event) {
		cs.changeScene("Home.fxml");
	}

	@FXML
	void exitBtnClicked(ActionEvent event) {
		Platform.exit();
	}

	@SuppressWarnings("static-access")
	@FXML
	void submitBtnClicked(ActionEvent event) {
		if (postArea.getText().isEmpty()) {
			Utility.warningAlert("Enter Content of New Post in Text Area.");
		} else {
			String content = postArea.getText();
			Utility.spellCheck(content);
			Post newPost = new Post(activeUser, content);
			postList.addPost(newPost);
			activeUser.getPostList().add(newPost);
			Backup.backup(userList.getList(), postList.getList());
			Utility.infoAlert("You Have Successfully Created a New Post!");
			cs.changeScene("Home.fxml");
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cs = new ChangeScene();
		postList = PostList.getInstance();
		userList = UserList.getInstance();
		activeUser = userList.getActive();
		userLbl.setText(activeUser.getUsername());
	}

}
