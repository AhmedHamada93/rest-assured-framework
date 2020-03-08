import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.RandomDataGenerator;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetLocationNameTest extends BaseTest {

    @DataProvider
    public static Object[][] testData() {
        return new Object[][]{
                {"AU", "0200", "Australian National University", "10000"},
                {"BR", "01000-000", "São Paulo", "100"},
                {"FR", "01000", "Bourg-en-Bresse", "100000"}
        };
    }


    @Test(dataProvider = "testData")
    public void verifyGetRequestWithCountryCodeAndZipCode(String countryCode, String zipCode, String expectedPlaceName, String invalidZipCode) {
        given().
                spec(getRequestSpecification()).
                when().
                get("/{countryCode}/{zipCode}", countryCode, zipCode).
                then().
                spec(getSuccessResponseSpecification()).
                and().
                assertThat().
                body("places[0].'place name'", equalTo(expectedPlaceName));
    }

    @Test(dataProvider = "testData")
    public void getRequestWithoutZipCodeShouldGiveNotFound(String countryCode, String zipCode, String expectedPlaceName, String invalidZipCode) {
        given().
                spec(getRequestSpecification()).
                when().
                get("/{countryCode}", countryCode).
                then().
                spec(getNotFoundResponseSpecification());
    }

    @Test(dataProvider = "testData")
    public void getRequestWithInvalidZipCodeShouldGiveNotFound(String countryCode, String zipCode, String expectedPlaceName, String invalidZipCode) {
        given().
                spec(getRequestSpecification()).
                when().
                get("/{countryCode}/{invalidZipCode}", countryCode, invalidZipCode).
                then().
                spec(getNotFoundResponseSpecification());
    }

    @Test
    public void getRequestWithInvalidCountryCodeShouldGiveNotFound() {
        Integer randomCountryCodeLength = 10;
        String randomCountryCode = RandomDataGenerator.randomString(randomCountryCodeLength);
        given().
                spec(getRequestSpecification()).
                when().
                get("/{countryCode}", randomCountryCode).
                then().
                spec(getNotFoundResponseSpecification());
    }

}