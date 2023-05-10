package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.PostDisplay;
import util.TitleDisplay;
import util.Utility;

@SuppressWarnings("serial")
public class PostList implements Serializable {

	private static PostList instance;
	private static LinkedList<Post> list;
	private static Post clicked;

//	Singleton Constructor
	private PostList() {
		list = new LinkedList<>();
	}

//	Singleton Instance
	public static PostList getInstance() {
		if (instance == null) {
			instance = new PostList();
		}
		return instance;
	}

//	Getters and Setters
	public Post getClicked() {
		return clicked;
	}

	public void setClicked(Post p) {
		clicked = getPost(p);
	}

	public static LinkedList<Post> getList() {
		return list;
	}

	public void setList(LinkedList<Post> list) {
		PostList.list = list;
	}
	
	public int size() {
		return list.size();
	}

	// Return A Post
	public Post getPost(Post p) {
		Iterator<Post> it = list.iterator();
		while (it.hasNext()) {
			Post qPost = it.next();
			if (qPost.getId() == p.getId()) {
				System.out.println("Post Found");
				return qPost;
			} else if (!qPost.getReplies().isEmpty()) {
				LinkedList<Post> qList = qPost.getReplies();
				Post sPost = checkReplies(qList, p.getId());
				if (sPost != null) {
					System.out.println("Post Found");
					return sPost;
				}
			}
		}
		return null;
	}

// Recursive Check for a Post in Replies
	public Post checkReplies(LinkedList<Post> p, int postId) {
		for (Post post : p) {
			if (post.getId() == postId) {
				System.out.println("Post Found");
				return post;
			} else if (!post.getReplies().isEmpty()) {
				LinkedList<Post> pList = post.getReplies();
				Post rPost = checkReplies(pList, postId);
				if (rPost != null) {
					System.out.println("Post Found");
					return rPost;
				}
			}
		}
		return null;
	}

//	Add a new Post
	public void addPost(Post p) {
		list.add(p);
	}
	
//	Add a Reply to a Post
	public void addPostReply(Post post, Post reply) {
		Post p = getPost(post);
		p.getReplies().add(reply);
	}
	
//	Remove a Post
	public void deletePost(Post p) {
		list.remove(p);
	}

//	Display All Posts and Replies
	public VBox displayPosts() {
		VBox view = new VBox();
		view.setMinSize(583, 400);
		view.setStyle("-fx-background-color: teal");
		Iterator<Post> it = list.iterator();
		while (it.hasNext()) {
			HBox title = new TitleDisplay().getTitleDisplay();
			Post p = it.next();
			PostDisplay display = new PostDisplay(p);
			view.getChildren().addAll(title, display.getPostDisplay());
			if (!p.getReplies().isEmpty()) {
				LinkedList<Post> replies = p.getReplies();
				Utility.displayReplies(replies, view);
			}
		}
		return view;
	}

//	Display All Posts in Reverse and Replies
	public VBox displayPostsReverse() {
		VBox view = new VBox();
		view.setMinSize(583, 400);
		view.setStyle("-fx-background-color: teal");
		for (int i = list.size() - 1; i >= 0; i--) {
			HBox title = new TitleDisplay().getTitleDisplay();
			Post p = list.get(i);
			PostDisplay display = new PostDisplay(p);
			view.getChildren().addAll(title, display.getPostDisplay());
			if (!p.getReplies().isEmpty()) {
				LinkedList<Post> replies = p.getReplies();
				Utility.displayReplies(replies, view);
			}
		}
		return view;
	}

}
