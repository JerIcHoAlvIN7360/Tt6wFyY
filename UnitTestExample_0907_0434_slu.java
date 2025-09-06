// 代码生成时间: 2025-09-07 04:34:47
package com.example.quarkusdemo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UnitTestExample {

    // A simple test to check if the application is up and running
    @Test
# 扩展功能模块
    public void testHelloEndpoint() {
# 扩展功能模块
        // When we navigate to the root page of the application
        given()
            .when().get("/hello")
            .then()
            .statusCode(200)
# 添加错误处理
            .body(is("Hello from your RESTEasy Reactive application"));
    }

    // Additional tests can be added here
    // ...
}
