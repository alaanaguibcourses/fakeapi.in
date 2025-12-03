import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC11_Filter_Products_By_Rating implements BASE_URI {

    @Test
    public void filterProductsByRating() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .queryParam("rating", 5)
                .when()
                .get("/api/products");

        res.then()
                .statusCode(200)
                // تأكيد إن كل Product ال rating بتاعها = 5
                .body("products.findAll { it.rating == 5 }.size()",
                        equalTo(res.jsonPath().getList("products").size()))
                .body("products.size()", greaterThanOrEqualTo(1));  // لازم يرجع داتا

        Assert.assertTrue(res.time() < 3000);

        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
