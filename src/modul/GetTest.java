package modul;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Gets the necessary information out of the test file
 * 
 * @author GiBr03
 *
 */
public class GetTest {
	getStandartPath gtp = new getStandartPath();
	public static ArrayList<String[]> questionsArrayList = new ArrayList();
	static String fileName;
	static char[] fileContent;
	static String nameOfTest;
	static String signaturOfAuthor;


	/**
	 * Starts the whole class
	 * 
	 * @param fileNameFromTestController
	 *            is the file name of the test
	 */
	public static void startClass(String fileNameFromTestController) {
		String filePath = getStandartPath.getStandartPath();
		fileName = fileNameFromTestController;

		getFileContent(filePath + fileName);
		getNameAndSignatur();
		getTestChapters();
		extractQuestions();
	}

	/**
	 * Extracts the text of the file as char array
	 * 
	 * @param fileContent
	 *            is the char array
	 */
	private static void getFileContent(String fullname) {
		try {
			fileContent = new Scanner(new File(fullname + ".txt")).useDelimiter("\\Z").next().toCharArray();
		} catch (FileNotFoundException e) {
			System.out.println(">>>File content '" + fullname + "' not found!");
			e.printStackTrace();
		}
	}

	/**
	 * gets the name and signatur. Searches first for the name and then secondly for
	 * the signature
	 */
	private static void getNameAndSignatur() {
		String Name = ""; // Temp string for name
		String Signature = ""; // Temp string for signature
		boolean gotName = false; // Bool to chekc if the name is already found
		try {
			for (int i = 0; i < fileContent.length; i++) {
				if (gotName == false) { // If the name is not already found search here
					if (fileContent[i] == '[') {
						while (fileContent[i] != ']') {
							i++;
							Name += (char) fileContent[i];
						}
						gotName = true;
						Name = Name.replace("]", "");// Removes the brackets at the end of the string
					}

				} else {
					if (fileContent[i] == '[') {
						while (fileContent[i] != ']') {
							i++;
							Signature += (char) fileContent[i];
						}
						Signature = Signature.replace("]", "");// Removes the brackets at the end of the string
					}
				}
			}
		} catch (Exception e) {
			System.out.println(">>>File getNameAndSign() '" + Name + "' not possible!");
			e.printStackTrace();
		}
		nameOfTest = Name;
		signaturOfAuthor = Signature;
	}

	/**
	 * Searches for chapters and splits them
	 * 
	 * @return returns an Array list with each chapter on it
	 */
	private static ArrayList<String> getTestChapters() {
		ArrayList<String> chaptersArrayList = new ArrayList();
		String tempChapter = "";
		Integer j = -1;
		for (int i = 0; i < fileContent.length; i++) {
			if (fileContent[i] == '(') {
				while (fileContent[i] != ')') {
					i++;
					tempChapter += fileContent[i];
					tempChapter = tempChapter.replace(")", ""); // Removes the brackets at the end of the string
				}
				j += 1;
				chaptersArrayList.add(j, tempChapter); // Adds the chapter to an arraylist
				tempChapter = ""; // Sets the chapter to be empty again
			}
		}
		return chaptersArrayList;
	}

	/**
	 * Fills the arraylist with string arrays
	 * 
	 */
	private static void extractQuestions() {
		String tempString = null;
		for (int i = 0; i < getTestChapters().size(); i++) {
			String[] tempQuestions;
			tempString = getTestChapters().get(i).toString();
			tempQuestions = tempString.split(";");
			questionsArrayList.add(i, tempQuestions);
			tempString = "";
		}
	}
}