package controls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.PostList;
import model.User;
import model.UserList;
import model.WordSet;
import util.Backup;
import util.ChangeScene;
import util.Utility;

public class LogInController implements Initializable {
	
	private static User activeUser;
	private ChangeScene cs;
	private static UserList userList;
	private static PostList postList;
	private static WordSet words;

    @FXML
    private Hyperlink accountLink;

    @FXML
    private Button exitBtn;

    @FXML
    private Button logInBtn;

    @FXML
    private PasswordField passField;

    @FXML
    private TextField userField;

    @FXML
    void accountLinkClicked(ActionEvent event) {
    	cs.changeScene("SignUp.fxml");
    }

	@FXML
    void exitBtnClicked(ActionEvent event) {
    	Platform.exit();
    }

	@FXML
    void logInBtnClicked(ActionEvent event) {
    	if(userField.getText().isEmpty() || passField.getText().isEmpty()) {
    		Utility.warningAlert("Fields Are Left Empty! Fill Out All Fields.");
    	} else {
    		activeUser = Utility.attemptLogIn(userList, userField.getText(), passField.getText());
    		if(activeUser != null) {
    			userList.setActive(activeUser);
            	Backup.backupUserList(userList.getList());
    			cs.changeScene("Home.fxml");
    		}
    	}
    }
    
    public static User getActiveUser() {
    	return activeUser;
    }
    
    public static void setActiveUser(User u) {
    	activeUser = u;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userList = Utility.loadUserList();
		postList = Utility.loadPostList();
		words = Utility.loadWordList();
		cs  = new ChangeScene();
		System.out.println("Words: " + words.getWords().size());
		System.out.println("Posts: " + postList.size());
	}

}
