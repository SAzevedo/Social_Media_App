package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import model.Post;
import model.User;

public class Restore {

	@SuppressWarnings("unchecked")
	public static LinkedList<Post> restorePostList() {
		try {
			FileInputStream fis = new FileInputStream("backup/Posts.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			LinkedList<Post> pList = (LinkedList<Post>) ois.readObject();
			Post.setnElems((int) ois.readInt());
			ois.close();
			return pList;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static TreeMap<String, User> restoreUserList() {
		try {
			FileInputStream fis = new FileInputStream("backup/Users.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			TreeMap<String, User> list = (TreeMap<String, User>) ois.readObject();
			ois.close();
			return list;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static HashSet<String> restoreWordList() {
		try {
			FileInputStream fis = new FileInputStream("backup/Words.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			HashSet<String> list = (HashSet<String>) ois.readObject();
			ois.close();
			return list;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
