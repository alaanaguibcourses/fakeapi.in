import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC06_Create_New_Product implements BASE_URI {

    @Test
    public void createNewProduct() {

        String requestBody = """
                {
                  "name": "New Test Product",
                  "category": "Electronics",
                  "price": 199.99,
                  "rating": 4,
                  "isAvailable": true,
                  "description": "High quality product for testing",
                  "stock": 50,
                  "image": "https://example.com/test-product.jpg"
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

        res.then()
                .statusCode(200)
                //this api for creation test case sstatus code should be 200 eventhough 201 is for sucessfull creation on other apis;
                .body("name", equalTo("New Test Product"))
                .body("message", equalTo("Product added successfully (mock)"));

        Assert.assertTrue(res.time() < 3000);

        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
