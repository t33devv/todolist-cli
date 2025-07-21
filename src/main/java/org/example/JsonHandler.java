package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JsonHandler {

    public void writeJson(String taskName, boolean completed) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("mydata.json");

        ObjectNode jsonNode;
        if (file.exists()) {
            jsonNode = (ObjectNode) objectMapper.readTree(file);
        } else {
            jsonNode = objectMapper.createObjectNode(); // Create a new node if the file doesn't exist
        }

        String done = completed ? "Completed" : "Incomplete";

        jsonNode.put(taskName, completed);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonNode);
    }

    public void readJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File("mydata.json"));
//        String name = jsonNode.get("name").asText();
//        int age = jsonNode.get("age").asInt();
//        String city = jsonNode.get("city").asText();
//        String state = jsonNode.get("state").asText();
//        String country = jsonNode.get("country").asText();
//        System.out.println("Name: " + name);
//        System.out.println("Age: " + age);
//        System.out.println("City: " + city);
//        System.out.println("State: " + state);
//        System.out.println("Country: " + country);
        int count = 1;
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String fieldName = field.getKey();
            JsonNode fieldValue = field.getValue();

            String completed = field.getValue().asBoolean() ? "Completed" : "Incomplete";

            System.out.println(count + ". " + fieldName + ": " + completed);
            count++;
        }
        System.out.println("");
    }

    public int getMax() throws IOException {
        JsonNode jsonNode = new ObjectMapper().readTree(new File("mydata.json"));
        return jsonNode.size();
    }

    public String getKey(int target) throws IOException {
        JsonNode jsonNode = new ObjectMapper().readTree(new File("mydata.json"));
        Iterator<String> fields = jsonNode.fieldNames();
        String keyAtIndex = null;
        int currentIndex = 0;

        while (fields.hasNext()) {
            String fieldName = fields.next();
            if (currentIndex == target) {
                keyAtIndex = fieldName;
                return keyAtIndex;
            }
            currentIndex++;
        }

        return keyAtIndex;
    }

    public void deleteKey(int target) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("mydata.json");

        ObjectNode jsonNode;
        if (file.exists()) {
            jsonNode = (ObjectNode) objectMapper.readTree(file);
        } else {
            jsonNode = objectMapper.createObjectNode(); // Create a new node if the file doesn't exist
        }

        jsonNode.remove(this.getKey(target));
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonNode);
    }
}
