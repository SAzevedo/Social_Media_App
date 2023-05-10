
package controls;

import java.awt.Dimension;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.Post;
import model.PostList;
import model.User;
import model.UserList;
import util.Backup;
import util.ChangeScene;
import util.Utility;

public class PostController implements Initializable {
	
	private ChangeScene cs;
	private static PostList postList;
	private static UserList userList;
	private static LinkedList<Post> replyList;
	private static Post thisPost;
	private static User thisUser;
	private static User activeUser;
	private static VBox view;

    @FXML
    private TextArea contentArea;

    @FXML
    private Button exitBtn;

    @FXML
    private Button homeBtn;
    
    @FXML
    private Button replyBtn;
    
    @FXML
    private Button editBtn;
    
    @FXML
    private Button deleteBtn;
    
    @FXML
    private ScrollPane scrollList;

    @FXML
    private Hyperlink userLink;

	@FXML
    void exitBtnClicked(ActionEvent event) {
    	Platform.exit();
    }
    
	@SuppressWarnings("static-access")
	@FXML
    private void editBtnClicked(ActionEvent event) {
    	if(thisUser == activeUser) {
    		UIManager.put ("OptionPane.minimumSize",new Dimension (600, 200));
    		String s = JOptionPane.showInputDialog(null, "Rewrite This Post");
        	if(s.isEmpty()) {
        		Utility.warningAlert("You Didn't Post Anything!");
        	} else {
        		Utility.spellCheck(s);
        		thisPost.setContent(s);
        		Utility.infoAlert("You Have Edited This Post");
        		cs.changeScene("Post.fxml");
        	}
        	Backup.backup(userList.getList(), postList.getList());
    	} else {
    		Utility.warningAlert("You Can Not Edit Another User's Post.");
    	}    
    }
    
    @SuppressWarnings("static-access")
	@FXML
    private void deleteBtnClicked(ActionEvent event) {
    	if(thisUser == activeUser) {
    		if(thisUser.getPostList().contains(thisPost)) {
        		thisUser.getPostList().remove(thisPost);
        	}
        	postList.deletePost(thisPost);
        	Utility.infoAlert("You Have Deleted This Post.");
        	Backup.backup(userList.getList(), postList.getList()); 
    	} else {
    		Utility.warningAlert("You Can Not Delete Another User's Post.");
    	}
    }
    
	@FXML
    void homeBtnClicked(ActionEvent event) {
		cs.changeScene("Home.fxml");
    }

	@FXML
    void userLinkClicked(ActionEvent event) {
    	userList.setClicked(thisUser);
    	cs.changeScene("User.fxml");
    }
    
    @SuppressWarnings("static-access")
	@FXML
    void replyBtnClicked(ActionEvent event) {
    	UIManager.put ("OptionPane.minimumSize",new Dimension (600, 200));
    	String s = JOptionPane.showInputDialog(null, "Enter A Reply");
    	if(s.isEmpty()) {
    		Utility.warningAlert("You Didn't Post Anything!");
    	} else {
    		Utility.spellCheck(s);
    		Post temp = new Post(activeUser, s);
    		postList.addPostReply(thisPost, temp);
    		Utility.infoAlert("You Have Replied To This Post!");
    		cs.changeScene("Post.fxml");
    	}
    	Backup.backup(userList.getList(), postList.getList());    
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cs = new ChangeScene();
		postList = PostList.getInstance();
		userList = UserList.getInstance();
		thisPost = postList.getClicked();
		thisUser = thisPost.getCreator();
		activeUser = userList.getActive();
		userLink.setText(thisUser.getUsername());
		contentArea.setText(thisPost.getContent());
		replyList = postList.getPost(thisPost).getReplies();
		view = Utility.displayPostsLL(replyList);
		scrollList.setContent(view);
	}

}
