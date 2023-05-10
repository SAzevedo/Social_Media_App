package controls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Name;
import model.User;
import model.UserList;
import util.Backup;
import util.ChangeScene;
import util.Utility;

public class SignUpController implements Initializable {
	
	private ChangeScene cs;
	private static UserList userList;

    @FXML
    private TextField firstField;

    @FXML
    private TextField lastField;

    @FXML
    private Button logInBtn;

    @FXML
    private PasswordField pass1Field;

    @FXML
    private PasswordField pass2Field;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField userField;

    @FXML
    void logInBtnClicked(ActionEvent event) {
    	cs.changeScene("LogIn.fxml");
    }

	@FXML
    void registerBtnClicked(ActionEvent event) {
    	if(firstField.getText().isEmpty() || lastField.getText().isEmpty() || userField.getText().isEmpty() ||
    			pass1Field.getText().isEmpty() || pass2Field.getText().isEmpty()) {
    		Utility.warningAlert("Fields Are Left Empty! Fill Out All Fields.");
    	} else if(userList.contains(userField.getText().toLowerCase())) {
    		Utility.warningAlert("Username Already Exists! Enter a Different Username.");
    		userField.clear();
    	} else if(!Utility.stringsMatch(pass1Field.getText(), pass2Field.getText())) {
    		Utility.warningAlert("Passwords Don't Match! Enter Matching Passwords.");
    		pass1Field.clear();
    		pass2Field.clear();
    	} else {
    		Name name = new Name(firstField.getText(), lastField.getText());
    		User user = new User(name, userField.getText(), pass1Field.getText());
    		userList.add(user);
    		Backup.backupUserList(userList.getList());
    		Utility.infoAlert("You Have Successfully Registered Username: " + user.getUsername());
    		cs.changeScene("LogIn.fxml");
    	}
    			
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cs = new ChangeScene();
		userList = UserList.getInstance();
	}

}

