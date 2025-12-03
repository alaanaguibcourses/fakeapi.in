import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC19_Filter_Posts_By_Category implements BASE_URI {

    @Test
    public void filterPostsByCategory() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .queryParam("category", "Tech")
                .when()
                .get("/api/posts");

        res.then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));

        Assert.assertTrue(res.time() < 3000);

        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
