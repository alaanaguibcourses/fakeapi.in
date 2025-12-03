import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC20_Negative_GetSingleUser_InvalidID implements BASE_URI {

    @Test
    public void getUserByInvalidID() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .when()
                .get("/api/users/99999"); // ID غير موجود

        res.then()
                .statusCode(404) ;  // مفروض يرجع 404;

        Assert.assertTrue(res.time() < 3000);
        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
