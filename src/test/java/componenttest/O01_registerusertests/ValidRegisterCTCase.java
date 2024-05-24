package componenttest.O01_registerusertests;

import componenttest.baseclasses.BaseApiTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ValidRegisterCTCase extends BaseApiTest {

    @Test
    public void itShouldBePossibleToRegisterAnAccountWithValidData() {

        // postUserRequestData is created and filled with valid data in the BaseApiTest class

        given()
                .spec(rsPostUser)
                .and().body(postUserRequestData)
                .when()
                .post()
                .then()
                .log().everything()
                .and().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void itShouldBePossibleToRegisterAnAccountWithMinimalData() {
        // postUserRequestData is created and filled with valid data in the BaseApiTest class
        postUserRequestData.setPhoneNumber(""); // remove phone number
        given()
                .spec(rsPostUser)
                .and().body(postUserRequestData)
                .when()
                .post()
                .then()
                .log().everything()
                .and().statusCode(HttpStatus.SC_OK);
    }

}
