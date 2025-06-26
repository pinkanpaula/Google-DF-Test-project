
import com.google.cloud.dialogflow.cx.v3.*;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.*;




public class DFIntent {


    public DFIntent() {}

    public static void detectIntent(String projectId, List<String> texts, String sessionId, String languageCode, String Agent, String location, String env) throws IOException {
        
        Map<String, QueryResult> queryResults = Maps.newHashMap();
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            SessionName session = SessionName.ofProjectLocationAgentEnvironmentSessionName(projectId, location, Agent,env ,sessionId);
            for(String text : texts) {
                TextInput.Builder textInput =
                        TextInput.newBuilder().setText(text);

                // Build the query with the TextInput
                QueryInput queryInput = QueryInput.newBuilder().setText(textInput).setLanguageCode(languageCode).build();
                
                // Performs the detect intent request              
                DetectIntentRequest dIR = DetectIntentRequest.newBuilder().setSession(session.toString()).setQueryInput(queryInput).build();
                DetectIntentResponse response = sessionsClient.detectIntent(dIR);

                // Display the query result
                QueryResult queryResult = response.getQueryResult();
                System.out.println("====================");
                System.out.format("QueryResult: %s\n", queryResult.getText());
                System.out.format(
                        "Detected Intent: %s (confidence: %f)\n",
                        queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());

                System.out.format(
                        "Fulfillment Text: '%s'\n",
                        queryResult.getResponseMessagesCount() > 0
                                ? queryResult.getResponseMessages(0).getText()
                                : "Triggered Default Fallback Intent");

                queryResults.put(text, queryResult);
            }
        }
    }
}
