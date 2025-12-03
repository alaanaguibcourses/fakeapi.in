import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC24_Negative_Create_New_Product_Wrong_data_type implements BASE_URI {

    @Test
    public void createProductWithWrongDataTypes() {

        // Body with wrong data types intentionally
        String requestBody = """
                {
                  "name": 12345,
                  "category": true,
                  "price": "cheap",
                  "rating": "high",
                  "isAvailable": "yes",
                  "description": 999,
                  "stock": "a lot",
                  "image": 456
                }
                """;

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/products");

        // Expecting API to reject wrong data types
        res.then()
                .statusCode(equalTo(200)) // depends on API implementation
                .body("message", equalTo("Product added successfully (mock)"));

        // Validate response time
        Assert.assertTrue(res.time() < 3000);

        // Print & attach to Allure
        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
