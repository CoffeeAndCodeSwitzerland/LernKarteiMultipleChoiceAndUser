package modul;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetTest {
	getStandartPath gtp = new getStandartPath();
	
	public static void main(String [] args) {
		
		String currentFilePath = getStandartPath.getStandartPath();
		String testName = "Test1.txt";
		String fileName = currentFilePath + "src\\Tests\\" + testName;
		
		getFileContent(fileName);
	}
	
	private static String getFileContent(String name) {
		try {
			FileReader fr = new FileReader(name);
			
			BufferedReader br  = new BufferedReader(fr);
			
			String st;
			  while ((st = br.readLine()) != null)
			    System.out.println(st);
			
			
			br.close();
			}
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file: " + name);  
	        }
			catch(IOException ex) {
				System.out.println(
					"Error reading file: "+ name);                  
		    }
		return null;//TODO: add return value
	}
	
}
