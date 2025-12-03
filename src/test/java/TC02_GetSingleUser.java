import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class TC02_GetSingleUser implements BASE_URI {


    @Test
    public void getUserById() {
        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .when()
                .get("/api/users/1");


        res.then()
                .statusCode(200)
                .body("id", equalTo(1));


        Assert.assertTrue(res.time() < 3000);


        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}