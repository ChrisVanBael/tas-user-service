package componenttest.baseclasses;

import componenttest.helpers.ApiHelper;
import componenttest.models.PostUserRequestData;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

public class BaseApiTest {

    private final String BASE_URL = "http://localhost:8081";

    protected RequestSpecification rsUserService;
    protected RequestSpecification rsPostUser;

    protected PostUserRequestData postUserRequestData;

    @BeforeEach
    public void beforeEachTest() {
        // create a request specification for the BFF without headers
        Map<String, String> headers = new HashMap<>();
        rsUserService = ApiHelper.createRequestSpecification(BASE_URL, headers);

        rsPostUser = ApiHelper.createRequestSpecification(BASE_URL, headers);
        rsPostUser.basePath("/user").contentType(ContentType.JSON);

        postUserRequestData = ApiHelper.createValidPostUserRequestData();
    }

}
