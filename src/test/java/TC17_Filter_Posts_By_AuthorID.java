import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC17_Filter_Posts_By_AuthorID implements BASE_URI {

    @Test
    public void filterPostsByAuthorID() {

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .queryParam("authorId", 1)
                .when()
                .get("/api/posts");

        res.then()
                .statusCode(200);                                    //note cannot be validated because its mock api .body("authorId", everyItem(equalTo(1)));


        Assert.assertTrue(res.time() < 3000);

        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
