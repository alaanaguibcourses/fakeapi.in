import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class TC15_Search_Products_By_Name implements BASE_URI {

    @Test
    public void searchProductsByName() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .queryParam("search", "Chair")
                .when()
                .get("/api/ecommerce/products");

        res.then()
                .statusCode(200) ;


        Assert.assertTrue(res.time() < 3000);

        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
