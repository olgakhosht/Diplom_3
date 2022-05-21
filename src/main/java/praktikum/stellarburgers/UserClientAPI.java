package praktikum.stellarburgers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClientAPI extends StellarBurgersRestClientAPI {
    private static final String USER_PATH = "api/auth/";

    @Step("Отправка POST запроса на /api/auth/login")
    public ValidatableResponse loginUser(UserCredentialsForAPI credentials) {
        ValidatableResponse vrem = given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(USER_PATH + "login")
                .then();
        return vrem;
    }

    @Step("Отправка DELETE запроса на /api/auth/user")
    public ValidatableResponse deleteUser(String accessToken) {
        if (accessToken == null) {
            return given()
                    .spec(getBaseSpec())
                    .when()
                    .delete(USER_PATH + "user")
                    .then();
        } else {
            return given()
                    .spec(getBaseSpec())
                    .header("Authorization", accessToken)
                    .when()
                    .delete(USER_PATH + "user")
                    .then();
        }
    }
}
