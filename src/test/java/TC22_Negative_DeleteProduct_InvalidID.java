import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC22_Negative_DeleteProduct_InvalidID implements BASE_URI {

    @Test
    public void deleteProductInvalidID() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .when()
                .delete("/api/products/99999"); // ID غير موجود

        res.then()
                .statusCode(200)
                .body("error", notNullValue())
                .body("message", equalTo("Product deleted successfully (mock)"));

        Assert.assertTrue(res.time() < 3000);
        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
