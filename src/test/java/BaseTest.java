import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import resources.TestConstants;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static RequestSpecification requestSpecification;
    private static ResponseSpecification successResponseSpecification;
    private static ResponseSpecification notFoundResponseSpecification;

    public static ResponseSpecification getNotFoundResponseSpecification() {
        return notFoundResponseSpecification;
    }

    public static void setNotFoundResponseSpecification(ResponseSpecification notFoundResponseSpecification) {
        BaseTest.notFoundResponseSpecification = notFoundResponseSpecification;
    }

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public static void setRequestSpecification(RequestSpecification requestSpec) {
        BaseTest.requestSpecification = requestSpec;
    }

    public static ResponseSpecification getSuccessResponseSpecification() {
        return successResponseSpecification;
    }

    public static void setSuccessResponseSpecification(ResponseSpecification responseSpecification) {
        BaseTest.successResponseSpecification = responseSpecification;
    }

    @BeforeClass
    public static void createRequestSpecification() {
        setRequestSpecification(new RequestSpecBuilder().
                setBaseUri(TestConstants.getBaseURL()).
                build());
    }

    @BeforeClass
    public static void createSuccessResponseSpecification() {
        setSuccessResponseSpecification(new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build());
    }

    @BeforeClass
    public static void createNotFoundResponseSpecification() {
        setNotFoundResponseSpecification(new ResponseSpecBuilder().
                expectStatusCode(404).
                build());
    }

}
