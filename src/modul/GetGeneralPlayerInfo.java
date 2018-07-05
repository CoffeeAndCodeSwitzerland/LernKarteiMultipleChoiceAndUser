package modul;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/**
 * This class should get the players general info
 * @author GiBr03
 *
 */
public class GetGeneralPlayerInfo {
	/**
	 * Reads information out of the player.xml file
	 * @param TagName the tagname in the xml file ie: name,gender etc
	 * @return returns the text content of the tag
	 */
	public String getPlayerInfo(String TagName) {
		String returnValue = null;
		try {
			File XMLFile = new File("./src/playerdata/player.xml");//File location
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XMLFile);

			doc.getDocumentElement().normalize();//Normalizes the document
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work

			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("player");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("Element: " + nNode.getNodeName());
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					returnValue =  eElement.getElementsByTagName(TagName).item(0).getTextContent();
				}
			}
		} catch (Exception e) {//Prints the errors
			System.out.println(">>>Error: " + e);
		}
		return returnValue;
	}
}
