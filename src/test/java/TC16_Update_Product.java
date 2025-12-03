import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class TC16_Update_Product implements BASE_URI {

    @Test
    public void updateProduct() {

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
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/api/ecommerce/products/1");

        res.then()
                .statusCode(200) ;

        Assert.assertTrue(res.time() < 3000);

        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
