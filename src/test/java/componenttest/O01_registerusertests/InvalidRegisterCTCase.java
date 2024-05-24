package componenttest.O01_registerusertests;

import componenttest.baseclasses.BaseApiTest;
import componenttest.models.ErrorResponseData;
import componenttest.models.PostUserRequestData;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class InvalidRegisterCTCase extends BaseApiTest {

    @Test
    public void itShouldNotBePossibleToRegisterAnAccountWithNoData() {
        ValidatableResponse response =
                given()
                    .spec(rsPostUser)
                    .and().body(new PostUserRequestData())
                .when()
                    .post()
                .then()
                    .log().everything()
                    .and().statusCode(HttpStatus.SC_BAD_REQUEST);

        String expectedNameMessage = "Name is required";
        String expectedEmailMessage = "Email is required";
        String expectedEmailValidMessage = "Email is not valid";
        String expectedPasswordMessage = "Password is required";

        assertAll("",
                () -> assertEquals(
                        HttpStatus.SC_BAD_REQUEST,
                        response.extract().statusCode(),
                        "Status code should be 400")
                , () -> assertTrue(
                        response.extract().body().as(ErrorResponseData.class).getMessage().contains(expectedNameMessage),
                        "Message should contain '" + expectedNameMessage + "'")
                , () -> assertTrue(
                        response.extract().body().as(ErrorResponseData.class).getMessage().contains(expectedEmailMessage),
                        "Message should contain '" + expectedEmailMessage + "'")
                , () -> assertTrue(
                        response.extract().body().as(ErrorResponseData.class).getMessage().contains(expectedEmailValidMessage),
                        "Message should contain '" + expectedEmailValidMessage + "'")
                , () -> assertTrue(
                        response.extract().body().as(ErrorResponseData.class).getMessage().contains(expectedPasswordMessage),
                        "Message should contain '" + expectedPasswordMessage + "'")
        );
    }

    //
    // Do we need more tests for invalid registration?
    //

}
