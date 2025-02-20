package posd2024f.finalterm;

import java.nio.file.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;

public class MessageStrategyFile implements MessageStrategy {
    @Override
    public Message receiveMessage(String equipmentId) {
        String relativePath = "data/receive/" + equipmentId;
        Path directoryPath = Paths.get(relativePath);
        
        try {
            // check directory existence
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            Optional<Path> oldestFilePath = Files.list(directoryPath)
                .filter(Files::isRegularFile)
                .min(Comparator.comparingLong(p -> p.toFile().lastModified()));

            if (oldestFilePath.isPresent()) {
                Path filePath = oldestFilePath.get();
                String title = filePath.getFileName().toString();
                String message = new String(Files.readAllBytes(filePath));
                Files.delete(filePath);
                return new Message(title, message);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
