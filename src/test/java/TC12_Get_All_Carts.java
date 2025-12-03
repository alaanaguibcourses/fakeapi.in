import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

public class TC12_Get_All_Carts implements BASE_URI {

    @Test
    public void getAllCarts() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .when()
                .get("/api/ecommerce/carts");

        res.then()
                .statusCode(200)
                .body("size()", greaterThan(0)) // لازم يرجع carts
               ; // لازم يكون فيه منتجات

        Assert.assertTrue(res.time() < 3000); // response time أقل من 3 ثواني

        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
