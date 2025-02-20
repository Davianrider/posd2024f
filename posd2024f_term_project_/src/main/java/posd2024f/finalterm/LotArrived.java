package posd2024f.finalterm;

import java.io.StringReader;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;

public class LotArrived implements EquipmentObserver {
    @Override
    public void action(String equipmentId, Message message) {

        String messageContent = message.getMessage();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(messageContent)));

            String lot = doc.getElementsByTagName("lot").item(0).getTextContent();
            
            System.out.println("Equipment " + equipmentId + " has received lot: " + lot);

            System.out.println("Notify equipment " + equipmentId + " start processing lot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}