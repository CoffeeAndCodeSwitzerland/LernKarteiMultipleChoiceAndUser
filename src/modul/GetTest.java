package modul;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GetTest {
	getStandartPath gtp = new getStandartPath();
	static String fileName;
	static char[] fileContent;
	static String nameOfTest;
	static String signaturOfAuthor;

	public static void main(String[] args) {

		String currentFilePath = getStandartPath.getStandartPath();
		String testName = "Test1.txt";
		fileName = currentFilePath + "src\\Tests\\" + testName;

		getFileContent(fileName);
		getNameAndSignatur();
		System.out.println(nameOfTest + " " + signaturOfAuthor);
		getTestChapters();
	}
	/**
	 * Extracts the text of the file as char array
	 * @param fileContent is the char array
	 */
	private static void getFileContent(String name) {
		try {
			fileContent = new Scanner(new File(fileName)).useDelimiter("\\Z").next().toCharArray();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * gets the name and signatur.
	 * Searches first for the name and then secondly for the signature
	 */
	private static void getNameAndSignatur() {
		String Name = "";				//Temp string for name
		String Signature = "";			//Temo string for signatur
		boolean gotName = false;		//Bool to chekc if the name is already found
		for (int i = 0; i < fileContent.length; i++) { 
			if (gotName == false) {		//If the name is not already found search here
				if (fileContent[i] == '[') {
					while (fileContent[i] != ']') {
						i++;
						Name += (char) fileContent[i];
					}
					gotName = true;
					Name = Name.replace("]","");//Removes the brackets at the end of the string
				}

			} else {				
				if (fileContent[i] == '[') {
					while(fileContent[i] != ']') {
						i++;
						Signature += (char) fileContent[i];
					}
					Signature = Signature.replace("]","");//Removes the brackets at the end of the string
				}
			}
		}
		nameOfTest = Name;
		signaturOfAuthor = Signature;
	}
	/**
	 * Searches for chapters and splits them
	 * @return returns an Array list with each chapter on it 
	 */
	private static ArrayList<String> getTestChapters() {
		ArrayList<String> chaptersArrayList = new ArrayList();
		String tempChapter = "";
		Integer j = -1;
		for (int i = 0; i < fileContent.length; i++) {
			if(fileContent[i] == '(') {
				while(fileContent[i] != ')') {
					i++;
					tempChapter += fileContent[i];
					tempChapter = tempChapter.replace(")", ""); //Removes the brackets at the end of the string
				}
				j += 1;
				chaptersArrayList.add(j,tempChapter);	//Adds the chapter to an arraylist
				tempChapter = "";						//Sets the chapter to be empty again
			}
		}
		return chaptersArrayList;
	}
}
