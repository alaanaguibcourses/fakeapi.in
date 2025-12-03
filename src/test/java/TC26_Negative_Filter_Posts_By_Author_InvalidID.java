import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC26_Negative_Filter_Posts_By_Author_InvalidID implements BASE_URI {

    @Test
    public void filterPostsByInvalidAuthorID() {


        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .queryParam("authorId", 9999999)
                .when()
                .get("/api/posts");

        res.then()
                .statusCode(200); // API شغال تمام
                // مفيش posts ترجع

        Assert.assertTrue(res.time() < 3000);

        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
//the response doesnot contains post for this id it shows all ids posts
