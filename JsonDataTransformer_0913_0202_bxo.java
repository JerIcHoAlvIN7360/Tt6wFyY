// 代码生成时间: 2025-09-13 02:02:56
package com.example.converter;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.Scanner;

/**
 * Main class for JSON data format converter using Quarkus framework.
 */
@QuarkusMain
public class JsonDataTransformer implements QuarkusApplication {

    // Method to convert JSON data format
    public String convertJson(String jsonData) {
        try {
            // Parse JSON data
            JsonReader jsonReader = Json.createReader(new StringReader(jsonData));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            // Convert JSON object to string
            return jsonObject.toString();

        } catch (Exception e) {
            // Handle JSON parsing errors
            return "Error parsing JSON: " + e.getMessage();
        }
    }

    // Main method for Quarkus application
    @Override
    public int run(String... args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for JSON data to convert
        System.out.println("Enter JSON data to convert: ");
        String jsonData = scanner.nextLine();

        // Convert JSON data format
        String convertedJson = convertJson(jsonData);

        // Print converted JSON data
        System.out.println("Converted JSON data: ");
        System.out.println(convertedJson);

        scanner.close();
        return 0;
    }

    // Method to start Quarkus application
    public static void main(String[] args) {
        new JsonDataTransformer().run(args);
    }
}
