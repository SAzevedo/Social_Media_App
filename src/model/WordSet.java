package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class WordSet implements Serializable {
	
	private static WordSet instance;
	private static HashSet<String> words;
	
//	Singleton Constructor
	private WordSet() {
		words = new HashSet<>(100000);
		populateWords();
	}
	
//	Singleton Instance
	public  static WordSet getInstance() {
		if(instance == null) {
			instance = new WordSet();
		}
		return instance;
	}

//	Getters and Setters
	public HashSet<String> getWords() {
		return words;
	}
	
	public void setWords(HashSet<String> s) {
		words = s;
	}
	
//	Add a Word to the List
	public void addWord(String s) {
		words.add(s);
	}
	
//	See if Word is in the List
	public boolean contains(String s) {
		return words.contains(s);
	}
	
//	Populate an Empty WordList
	public static void populateWords() {
		File file = new File("resources/dictionary.txt");
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String str = scanner.nextLine();
				words.add(str);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
}
