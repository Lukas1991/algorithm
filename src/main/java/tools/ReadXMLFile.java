package tools;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLFile {

    public static void main(String[] args) {

        try {
            File fXmlFile = new File("/Users/chuyu/Desktop/Chuyu/algorithm/src/main/resources/ipadmin.xlf");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("trans-unit");

            System.out.println("{\n" + "    \"translation\": {");

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String id = eElement.getAttribute("id");
                    String target = eElement.getElementsByTagName("target").item(0).getTextContent();

                    String line = String.format("\t\t\"%s\": \"%s\"", id, target);
                    if (i != nList.getLength() - 1) {
                        line = line + ",";
                    }
                    System.out.println(line);
                }
            }

            System.out.println("    }\n" + "}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
