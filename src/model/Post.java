package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Post implements Serializable {

	private User creator;
	private String userName;
	private String content;
	private LinkedList<Post> replies;
	private String date;
	private String time;
	private static int nElems = 0;
	private int id;

	public Post(User creator, String content) {
		super();
		this.creator = creator;
		this.content = content;
		this.userName = creator.getUsername();
		this.replies = new LinkedList<>();
		this.id = ++nElems;
		this.date = new SimpleDateFormat("MM.dd.yyyy").format(new java.util.Date());
		this.time = new SimpleDateFormat("HH.mm").format(new java.util.Date());
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LinkedList<Post> getReplies() {
		return replies;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public static int getnElems() {
		return nElems;
	}

	public static void setnElems(int nElems) {
		Post.nElems = nElems;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return content;
	}

}
