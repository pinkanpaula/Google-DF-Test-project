import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



class TestDFIntent {
    String projectId;
    String sessionId;
    String languageCode;
    String Agent;
    String location;
    String environment;
    List<String> texts;
    UUID uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        projectId = "ppdialog";
        sessionId = uuid.toString();
        languageCode = "en";
        Agent = "307eb9af-2b28-4cc5-b887-eb236944bc72";
        location = "global";
        environment = "draft";
        texts = new ArrayList<>();
        texts.add("Sawat de krap");

    }

    @AfterEach
    void tearDown() {
    }

   
    @Test
    void detectIntent() throws IOException {
        TestIntent.detectIntent(projectId, texts, sessionId, languageCode, Agent, location, environment);

    }
}
