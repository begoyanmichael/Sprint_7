package steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Setter;
import model.Order;
import org.junit.Before;

import static io.restassured.RestAssured.given;

@Setter
public class OrderApiSteps {

    private Order order;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.basePath = "/api/v1/orders";
    }

    @Step("Создание заказа")
    public Response createOrder() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post();
    }

    @Step("Получение заказа")
    public Response getOrder() {
        return given().log().all().get();
    }
}