package org.mgs;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;

import static com.jayway.restassured.RestAssured.get;

public class RestAssuredSteps {

    Response response;

    /**
     *Simple step for debug
     */
    @Дано("^выполняем шаг RestAssured1$")
    public void doStep1(){
        System.out.println("step1 RA");
    }

    @Дано("^задаем адрес хоста для RA \"([^\"]*)\"$")
    public void задаемАдресХостаДляRA(String baseURI) throws Throwable {
        RestAssured.baseURI = baseURI;

    }

    @И("^задаем путь к сервису для RA \"([^\"]*)\"$")
    public void задаемПутьКСервисуДляRA(String basePath) throws Throwable {
        RestAssured.basePath = basePath ;
    }

    @Дано("^выполняем запрос RA$")
    public void выполняемЗапросRA() throws Throwable {
        response = get("/pet");
    }

    @То("^код ответа RA 200-Ok$")
    public void кодОтветаRAOk() throws Throwable {
        response.then().statusCode(200);
    }

    @То("^код ответа RA (\\d+)$")
    public void кодОтветаRA(int code) throws Throwable {
        response.then().statusCode(code);
    }
}
