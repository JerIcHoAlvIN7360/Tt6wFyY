// 代码生成时间: 2025-09-17 14:59:57
 * IntegrationTestTool.java
 *
 * A simple integration test tool using Quarkus framework.
 * This tool is designed to run integration tests for a Quarkus application.
 *
 * @author Your Name
 * @version 1.0
 */

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class IntegrationTestTool {

    // Setup RestAssured before class
    static {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;
    }

    // Test GET request to the root endpoint
    @Test
    void testGetRoot() {
        RestAssured.given()
            .when().get("/")
            .then()
                .statusCode(200)
                .body("greeting", equalTo("hello"));
    }

    // Test POST request to a specific endpoint
    @Test
    void testPostData() {
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body("
    {
        "name": "John Doe"
    }
    ")
            .when().post("/persons")
            .then()
                .statusCode(201)
                .body("name", equalTo("John Doe"));
    }

    // Additional tests can be added here...
}
