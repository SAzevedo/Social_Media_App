package controls;

import java.net.URL;
import java.util.LinkedList;
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
import model.Post;
import model.PostList;
import model.User;
import model.UserList;
import util.Backup;
import util.ChangeScene;
import util.UserDisplay;
import util.Utility;

public class UserController implements Initializable {

	private ChangeScene cs;
	private static UserList userList;
	private static PostList postList;
	private static LinkedList<Post> userPosts;
	private static LinkedList<User> following;
	private static User activeUser;
	private static User clickedUser;
	private static VBox view;

	@FXML
	private Hyperlink activeLink;

	@FXML
	private Hyperlink userLink;

	@FXML
	private Hyperlink followLink;

	@FXML
	private Hyperlink addFollowLink;
	
	@FXML
	private Hyperlink followPosts;

	@FXML
	private Button exitBtn;

	@FXML
	private Button homeBtn;

	@FXML
	private Label nameLbl;

	@FXML
	private Label dateLbl;

	@FXML
	private Label listLbl;

	@FXML
	private ScrollPane scrollList;

	@FXML
	void userLinkClicked(ActionEvent event) {
		listLbl.setText(clickedUser.getUsername() + "'s Posts");
		userPosts = clickedUser.getPostList();
		view = Utility.displayPostsLL(userPosts);
		scrollList.setContent(view);
	}

	@FXML
	void followingLinkClicked(ActionEvent event) {
		listLbl.setText("Users " + clickedUser.getUsername() + " is Following");
		following = clickedUser.getFollowing();
		view = Utility.displayUsersLL(following);
		scrollList.setContent(view);
	}

	@FXML
	void activeLinkClicked(ActionEvent event) {
		userList.setClicked(activeUser);
		cs.changeScene("User.fxml");
	}
	
	@FXML
	void followPostsClicked(ActionEvent event) {
		view = new VBox();
		listLbl.setText("Posts that " + clickedUser.getUsername() + " Follows");
		following = clickedUser.getFollowing();
		for(User u : following) {
			UserDisplay uD = new UserDisplay(u);
			LinkedList<Post> pList = u.getPostList();
			view.getChildren().addAll(uD.getUserDisplay() ,Utility.displayPostsLLReverse(pList));
		}
		scrollList.setContent(view);
	}

	@FXML
	void exitBtnClicked(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
	void homeBtnClicked(ActionEvent event) {
		cs.changeScene("Home.fxml");
	}

	@SuppressWarnings("static-access")
	@FXML
	void addFollowLinkClicked(ActionEvent event) {
		following = activeUser.getFollowing();
		if(activeUser.getUsername() == clickedUser.getUsername()) {
			Utility.warningAlert("You Can Not Follow Yourself!");
		} else if(following.contains(clickedUser)) {
			Utility.infoAlert("You Are Already Following This User!");
		} else {
			Utility.infoAlert("You Are Now Following: " + clickedUser.getUsername());
			following.add(clickedUser);
			Backup.backup(userList.getList(), postList.getList());
		}	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cs = new ChangeScene();
		userList = UserList.getInstance();
		postList = PostList.getInstance();
		activeUser = userList.getActive();
		clickedUser = userList.getClicked();
		activeLink.setText(activeUser.getUsername());
		nameLbl.setText(clickedUser.getUsername());
		dateLbl.setText(clickedUser.getDate().toString());
		listLbl.setText(clickedUser.getUsername() + "'s Posts");
		userPosts = clickedUser.getPostList();
		view = Utility.displayPostsLL(userPosts);
		scrollList.setContent(view);
	}
}
