import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC10_Filter_Products_By_Availability implements BASE_URI {

    @Test
    public void filterAvailableProducts() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .queryParam("isAvailable", true)
                .when()
                .get("/api/products");

        res.then()
                .statusCode(200)
                // تأكيد ان كل Product راجع isAvailable = true
                .body("products.findAll { it.isAvailable == true }.size()",
                        equalTo(res.jsonPath().getList("products").size()))
                .body("products.size()", greaterThanOrEqualTo(1));  // لازم يرجع Products

        Assert.assertTrue(res.time() < 3000);

        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
