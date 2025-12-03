import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class TC08_Filter_Carts_With_oneItem implements BASE_URI {

    @Test
    public void filterCartsWithOneItem() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .queryParam("hasItems", true)
                .when()
                .get("/api/ecommerce/carts");

        res.then()
                .statusCode(200)
                // التأكد إن كل cart فيه products array > 0
                .body("carts.findAll { it.products.size() == 1 }.size()",
                        greaterThan(0));

        Assert.assertTrue(res.time() < 3000);

        res.prettyPrint();
    }
}
