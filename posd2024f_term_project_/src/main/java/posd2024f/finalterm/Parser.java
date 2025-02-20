package posd2024f.finalterm;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {

    // read xml file and return a map of state and observers
    public Map<String, List<String>> readXML(String filePath) {
        Map<String, List<String>> stateObserverMap = new HashMap<>();
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("state");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String state = eElement.getAttribute("name");
                    NodeList observerList = eElement.getElementsByTagName("observer");
                    List<String> observers = new ArrayList<>();
                    for (int i = 0; i < observerList.getLength(); i++) {
                        Node observerNode = observerList.item(i);
                        if (observerNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element observerElement = (Element) observerNode;
                            observers.add(observerElement.getTextContent());
                        }
                    }
                    stateObserverMap.put(state, observers);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stateObserverMap;
    }

}
