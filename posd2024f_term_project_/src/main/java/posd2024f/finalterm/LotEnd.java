package posd2024f.finalterm;

import java.io.StringReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;

public class LotEnd implements EquipmentObserver {
    @Override
    public void action(String equipmentId, Message message) {
        String messageContent = message.getMessage();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(messageContent)));

            String lot = doc.getElementsByTagName("lot").item(0).getTextContent();
            String startTimeStr = doc.getElementsByTagName("start_time").item(0).getTextContent();
            String endTimeStr = doc.getElementsByTagName("end_time").item(0).getTextContent();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
            LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);

            Duration duration = Duration.between(startTime, endTime);
            long processingTimeMinutes = duration.toMinutes();

            String output = String.format(
                "Equipment %s has finished processing lot\n\tLot: %s\n\tStart Time: %s\n\tEnd Time: %s\n\tProcessing Time: %d minutes",
                equipmentId, lot, startTimeStr, endTimeStr, processingTimeMinutes
            );
            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}