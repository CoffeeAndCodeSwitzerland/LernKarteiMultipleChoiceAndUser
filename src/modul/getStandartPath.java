package modul;

import java.io.File;

/**
 * This class searches for the path where the file is currently located
 * 
 * @author GiBr03
 *
 */
public class getStandartPath {
	public static String getStandartPath() {
		File currentDirFile = new File(".");// Creates a new file named '.' where the file currently is
		String helper = currentDirFile.getAbsolutePath();// gets the path of '.'
		String currentFilePath = "";

		char[] test = helper.toCharArray();

		for (int i = 0; i < helper.length() - 1; i++) {// Subtracts the current file from the path
			currentFilePath += test[i];
		}
		currentFilePath = currentFilePath + "src\\Tests\\";// Adds the test location to the path

		return currentFilePath;
	}
}
