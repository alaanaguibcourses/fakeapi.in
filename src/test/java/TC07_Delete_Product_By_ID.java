import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC07_Delete_Product_By_ID implements BASE_URI {

    @Test
    public void deleteProductById() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .when()
                .delete("/api/products/1");

        // Validations
        res.then()
                .statusCode(200)
                .body("message", equalTo("Product deleted successfully (mock)"));

        // response time < 3 seconds
        Assert.assertTrue(res.time() < 3000);

        // Print & Attach to Allure
        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
