import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC25_Filter_Posts_By_INVALIDCategory_Validation implements BASE_URI {

    @Test
    public void filterPostsByInvalidCategory() {

        // Invalid category name
        String invalidCategory = "InvalidCategoryname";

        Response res = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .queryParam("category", invalidCategory)
                .when()
                .get("/api/posts");

        res.then()
                .statusCode(200) ;// API might still return 200 with empty list; // Expect no posts found

        Assert.assertTrue(res.time() < 3000);

        // Print & attach to Allure
        res.prettyPrint();
        Allure.addAttachment("Response Body", res.asString());
    }
}
