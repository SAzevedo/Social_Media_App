package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import model.Post;
import model.User;

public class Backup {
	
	public static void backupPostList(LinkedList<Post> pList) {
		try {
			FileOutputStream fos = new FileOutputStream("backup/Posts.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(pList);
			oos.writeInt(Post.getnElems());
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void backupUserList(TreeMap<String, User> list) {
		try {
			FileOutputStream fos = new FileOutputStream("backup/Users.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void backupWordList(HashSet<String> sList) {
		try {
			FileOutputStream fos = new FileOutputStream("backup/Words.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(sList);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void backup(TreeMap<String, User> list, LinkedList<Post> pList) {
		try {
			FileOutputStream fos = new FileOutputStream("backup/Users.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileOutputStream fos = new FileOutputStream("backup/Posts.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(pList);
			oos.writeInt(Post.getnElems());
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
