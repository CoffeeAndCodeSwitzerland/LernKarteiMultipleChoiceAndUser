package modul;

import java.io.File;

public class getStandartPath {
	public static String getStandartPath(String Name) {
		File currentDirFile = new File(".");
		String helper = currentDirFile.getAbsolutePath();
		String currentFilePath = "";

		char[] test = helper.toCharArray();

		for (int i = 0; i < helper.length() - 1; i++) {
			currentFilePath += test[i];
		}
		currentFilePath = currentFilePath + "src\\Tests\\" + Name;
		System.out.println(currentFilePath);

		return currentFilePath;
	}
}
