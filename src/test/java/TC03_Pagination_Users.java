import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC03_Pagination_Users implements BASE_URI {

    @Test
    public void pagination_Users() {

        Response res = given()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .when()
                .get("/api/users?page=2&limit=4");

        res.then()
                .statusCode(200)
                .body("size()", equalTo(4))   ;

        Assert.assertTrue(res.time() < 3000);
        res.prettyPrint();
    }
}
