package util;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Post;
import model.PostList;
import model.User;
import model.UserList;
import model.WordSet;

public class Utility {
	
//	Spell Check
	public static void spellCheck(String s) {
		WordSet words = WordSet.getInstance();
		 String[] splitArr = s.split("\\s|\\.|\\,|\\?|\\!\\n");
		 String[] misSpelled = new String[splitArr.length];
		 int count = 0;
		 for(String t : splitArr) {
			 if(!words.contains(t.toLowerCase())) {
				 misSpelled[count++] = t;
			 }
		 }
		 if(count > 0) {
			 String[] nonNullMispelled = Arrays.copyOfRange(misSpelled, 0, count);
			 Utility.warningAlert("Post Contains Misspelled Words: " + displayArray(nonNullMispelled));
		 }	 
	}
	
//	Display String Array
	public static String displayArray(String[] arr) {
		String s = "";
		for(String t : arr) {
			s += t + "   ";
		}
		return s;
	}
	
//	Display LinkedList<Post>
	public static VBox displayPostsLL(LinkedList<Post> list) {
		VBox view = new VBox();
		view.setMinSize(585, 400);
		view.setStyle("-fx-background-color: teal");
		for(Post p : list) {
			HBox title = new TitleDisplay().getTitleDisplay();
    		PostDisplay temp = new PostDisplay(p);
    		view.getChildren().addAll(title, temp.getPostDisplay());
    		if(!p.getReplies().isEmpty()) {
				LinkedList<Post> replies = p.getReplies();
				displayReplies(replies, view);
			}
    	}
		return view;
	}
	
//	DisplayLinkedList<Post> in Reverse
	public static VBox displayPostsLLReverse(LinkedList<Post> list) {
		VBox view = new VBox();
//		view.setMinSize(585, 400);
		view.setStyle("-fx-background-color: teal");
		for(int i = list.size()-1; i >= 0; i--) {
			HBox title = new TitleDisplay().getTitleDisplay();
			Post p = list.get(i);
    		PostDisplay temp = new PostDisplay(p);
    		view.getChildren().addAll(title, temp.getPostDisplay());
    		if(!p.getReplies().isEmpty()) {
				LinkedList<Post> replies = p.getReplies();
				displayReplies(replies, view);
			}
    	}
		return view;
	}
	
//	Display LinkedList<User>
	public static VBox displayUsersLL(LinkedList<User> list) {
		VBox view = new VBox(10);
		view.setMinSize(585, 400);
		view.setStyle("-fx-background-color: teal");
		for(User u : list) {
    		UserDisplay temp = new UserDisplay(u);
    		view.getChildren().add(temp.getUserDisplay());
    	}
    	return view;
	}
	
//	Display TreeMap<String, User>
	public static VBox displayUsersTM(TreeMap<String, User> list) {
		VBox view = new VBox(10);
		view.setMinSize(585, 400);
		view.setStyle("-fx-background-color: teal");
		for(Map.Entry<String, User> entry: list.entrySet()) {
			UserDisplay temp = new UserDisplay(entry.getValue());
    		view.getChildren().add(temp.getUserDisplay());
		}
		return view;
	}
	
//	Recursive Display of Replies for LinkedList<Post>
	public static void displayReplies(LinkedList<Post> list, VBox view) {
		for(Post p : list) {
			PostDisplay temp = new PostDisplay(p);
			view.getChildren().addAll(temp.getPostDisplay());
			if(p.getReplies().isEmpty()) {
				continue;
			} else {
				LinkedList<Post> list2 = p.getReplies();
				displayReplies(list2, view);
			}
		}
	}
	
//	Get or Create UserList Instance
	public static UserList loadUserList() {
		UserList u = UserList.getInstance();
		if(new File("backup/Users.dat").exists()) {
			u.setList(Restore.restoreUserList());
		}
		return u;
	}
	
//	Get or Create PostList Instance
	public static PostList loadPostList() {
		PostList p = PostList.getInstance();
		if(new File("backup/Posts.dat").exists()) {
			p.setList(Restore.restorePostList());
		}	
		return p;
	}
	
//	Get or Create a WordList Instance
	public static WordSet loadWordList() {
		WordSet w = WordSet.getInstance();
		if(new File("backup/Words.dat").exists()) {
			w.setWords(Restore.restoreWordList());
		}
		return w;
	}

//	Attempt a LogIn
	public static User attemptLogIn(UserList list, String username, String password) {
		String lowerCaseUsername = username.toLowerCase();
		if (list.contains(lowerCaseUsername)) {
			User active = list.get(lowerCaseUsername);
			if (active.getPassword().equals(password)) {
				Utility.infoAlert("You have successfully Logged In!");
				return active;
			} else {
				Utility.warningAlert("The password is incorrect!");
			}
		} else {
			Utility.warningAlert("The username does not exist!");
		}
		return null;
	}

	public static boolean stringsMatch(String s1, String s2) {
		if (s1.equals(s2)) {
			return true;
		} else {
			return false;
		}
	}

//	Warning Alert
	public static void warningAlert(String text) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText("Warning!");
		alert.setContentText(text);
		alert.showAndWait();
	}

//	Info Alert
	public static void infoAlert(String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Information");
		alert.setContentText(text);
		alert.showAndWait();
	}

}
