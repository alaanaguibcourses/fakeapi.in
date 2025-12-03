import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC04_Get_All_Products implements BASE_URI {

    @Test
    public void getAllProducts() {

        Response res = given()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .when()
                .get("/api/products\t");

        res.then()
                .statusCode(200)
                .body("size()", greaterThan(0)) ;

        Assert.assertTrue(res.time() < 3000);
        res.prettyPrint();
    }
}
