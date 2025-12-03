import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class TC01_getAllUsers implements BASE_URI {

    @Test
    public void getAllUsers() {

        Response res = given()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .when()
                .get("/api/users");

        // Validations
        res.then()
                .statusCode(200)
                .body("size()", greaterThan(0));

        Assert.assertTrue(res.time() < 3000);

        // Print response and attach to Allure report
        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
