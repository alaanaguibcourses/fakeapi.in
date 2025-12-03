import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC09_Filter_Empty_Carts implements BASE_URI {

    @Test
    public void filterEmptyCarts() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .queryParam("hasItems", false)
                .when()
                .get("/api/ecommerce/carts");

        res.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("data.carts.size()", equalTo(0))
                .body("data.statistics.totalCarts", equalTo(0))
                .body("data.statistics.activeCarts", equalTo(0))
                .body("data.statistics.totalItemsInCarts", equalTo(0))
                .body("data.statistics.averageCartValue", nullValue())
                .body("data.statistics.totalCartValue", equalTo(0))
                .body("pagination.page", equalTo(1))
                .body("pagination.limit", equalTo(10))
                .body("pagination.total", equalTo(0))
                .body("pagination.totalPages", equalTo(0));

        Assert.assertTrue(res.time() < 3000);

        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
