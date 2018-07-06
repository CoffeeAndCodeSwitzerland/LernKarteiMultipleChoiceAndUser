package modul;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import org.w3c.dom.Document;

/**
 * Should write into the player.xml file
 * 
 * @author GiBr03
 *
 */
public class WritePlayerData {
	/**
	 * 
	 * @param tagName
	 *            name of the tag in the xml file
	 * @param value
	 *            value you want to have the tag
	 */
	public void writePlayerData(String tagName, String value) {
		try {
			File XMLFile = new File("./src/playerdata/player.xml");// File location
			String filePath = XMLFile.getAbsolutePath();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XMLFile);

			doc.getDocumentElement().normalize();// Normalizes the document
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work

			Node player = doc.getElementsByTagName("player").item(0);

			NodeList list = player.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);

				if (tagName.equals(node.getNodeName())) {
					node.setTextContent(value);
				}
			}

			// Write content to file
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);

			System.out.println("Done writing players data");
		} catch (Exception e) {
			System.out.println(">>>>Error: " + e);
		}
	}
}
