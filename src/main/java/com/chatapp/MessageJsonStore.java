package com.chatapp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/** Writes stored messages to a JSON array in stored_messages.json. */
public class MessageJsonStore {
    private final Path filePath;

    public MessageJsonStore() {
        this(Paths.get("stored_messages.json"));
    }

    public MessageJsonStore(Path filePath) {
        this.filePath = filePath;
    }

    public void storeMessages(List<Message> messages) throws IOException {
        List<Message> stored = messages.stream()
                .filter(message -> "STORED".equals(message.getStatus()))
                .collect(Collectors.toList());
        String json = stored.stream().map(Message::toJsonObject)
                .collect(Collectors.joining(",\n", "[\n", "\n]\n"));
        Path parent = filePath.toAbsolutePath().getParent();
        if (parent != null) Files.createDirectories(parent);
        Files.writeString(filePath, json, StandardCharsets.UTF_8);
    }

    public Path getFilePath() {
        return filePath;
    }
}
