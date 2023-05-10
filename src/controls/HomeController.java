package controls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.PostList;
import model.User;
import model.UserList;
import util.ChangeScene;

public class HomeController implements Initializable {
	
	private ChangeScene cs;
	private static PostList postList;
	private static UserList userList;
	private static User activeUser;
	private static VBox view;

    @FXML
    private Button exitBtn;

    @FXML
    private Button logInBtn;

    @FXML
    private Button newPostBtn;

    @FXML
    private Label titleLbl;

    @FXML
    private Hyperlink userLink;
    
    @FXML
    private ScrollPane scrollList;

	@FXML
    void exitBtnClicked(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void logInBtnClicked(ActionEvent event) {
    	LogInController.setActiveUser(null);
    	cs.changeScene("LogIn.fxml");
    }

    @FXML
    void newPostBtnClicked(ActionEvent event) {
    	cs.changeScene("NewPost.fxml");
    }

	@FXML
    void userLinkClicked(ActionEvent event) {
    	userList.setClicked(activeUser);
    	cs.changeScene("User.fxml");
    }
    		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cs = new ChangeScene();
    	postList = PostList.getInstance();
    	userList = UserList.getInstance();
		activeUser = userList.getActive();
		userLink.setText(activeUser.getUsername());
		view = new VBox();
		view = postList.displayPostsReverse();
		scrollList.setContent(view);
	}
    
}
