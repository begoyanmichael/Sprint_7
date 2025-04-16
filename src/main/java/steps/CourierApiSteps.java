package steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Setter;
import model.Courier;
import org.junit.Before;

import static io.restassured.RestAssured.given;

@Setter
public class CourierApiSteps {
    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.basePath = "/api/v1/courier";
    }

    private Response sendRequest(String endpoint, String method) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .request(method, endpoint);
    }

    @Step("Создание курьера")
    public Response createCourier() {
        return sendRequest("", "POST");
    }

    @Step("Авторизация курьера")
    public Response loginCourier() {
        return sendRequest("/login", "POST");
    }

    @Step("Удаление курьера")
    public void deleteCourier() {
        Integer courierId = loginCourier()
                .then().log().all()
                .extract().path("id");
        if (courierId != null) {
            sendRequest("/" + courierId, "DELETE");
        }
    }
}
