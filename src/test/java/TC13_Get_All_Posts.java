import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC13_Get_All_Posts implements BASE_URI {

    @Test
    public void getAllPosts() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .when()
                .get("/api/posts");

        res.then()
                .statusCode(200)
                .body("size()", greaterThan(0)) ;      // تأكد إن فيه بوستات على الأقل
        Assert.assertTrue(res.time() < 3000);
        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
