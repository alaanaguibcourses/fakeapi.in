import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC14_Paginate_Products_Page2_Limit5 implements BASE_URI {

    @Test
    public void paginateProducts() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .queryParam("page", 2)
                .queryParam("limit", 5)
                .when()
                .get("/api/products");

        res.then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("limit", equalTo(5))
                .body("products.size()", equalTo(5));  // لازم يرجع 5 items

        Assert.assertTrue(res.time() < 3000);
        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
