import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC21_CreateProduct_MissingFields_Validation_Check implements BASE_URI {

    @Test
    public void createProductWithMissingFields() {

        String requestBody = """
                {
                  "name": "Incomplete Product",
                  "price": 100
                }
                """; // ناقص category, stock, description, image

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/products");

        res.then()
                .statusCode(400) // مفروض يرجع 400 Bad Request
                .body("error", containsString("Missing required fields"));

        Assert.assertTrue(res.time() < 3000);
        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
