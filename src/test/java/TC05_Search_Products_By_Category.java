import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC05_Search_Products_By_Category implements BASE_URI {

    @Test
    public void searchProductsByCategory() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .queryParam("category", "Books")
                .when()
                .get("/api/products");

        res.then()
                .statusCode(200)
                .body("products.size()", greaterThan(0))
                .body("products.category", everyItem(equalTo("Books")));

        Assert.assertTrue(res.time() < 3000);

        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
