package componenttest.helpers;

import componenttest.models.PostUserRequestData;
import helpers.TestHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class ApiHelper {

    public static RequestSpecification createRequestSpecification(String apiBasePath, Map<String, String> headers) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(apiBasePath);
        for (Map.Entry<String, String> header : headers.entrySet()) {
            requestSpecBuilder.addHeader(header.getKey(), header.getValue());
        }
        return requestSpecBuilder.build();
    }

    public static PostUserRequestData createValidPostUserRequestData() {
        String uuid = TestHelper.getRandomUUIDAsString();
        return new PostUserRequestData(
                "API Test User " + uuid,
                "apitestuser-" + uuid + "@test.com",
                "password",
                "0612345678"
        );
    }

}
