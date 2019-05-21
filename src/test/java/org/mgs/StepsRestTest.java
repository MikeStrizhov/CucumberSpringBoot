package org.mgs;

import cucumber.api.PendingException;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.То;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class StepsRestTest extends CucumberStepDefinitions {
    @Autowired
    public RestController restController;

    @Дано("^выполняем шаг R1$")
    public void doStep1(){
        System.out.println("step1");
    }

    @То("^выполняем шаг R2$")
    public void doStep2(){
        System.out.println("step2");
    }

    @Дано("^выполняем GET запрос по url \"([^\"]*)\"$")
    public void executeGetByUrl(String url) throws Throwable {
        //  applicationContext = Context.getInstance();
        restController.executeGetByUrl(url);;
    }

    @То("^код ответа 200-Ok$")
    public void responceCode200Ok() throws Throwable {
        boolean result = restController.isResponceCode200Ok();
        assertTrue("Ошибка. Получен другой код ответа", result);
    }

    @Дано("^выполняем POST запрос по url \"([^\"]*)\" с телом запроса в формате XML:$")
    public void executePopstXMLByUrl(String url, String requestBody) throws Throwable {
        restController.executePopstXMLByUrl(url, requestBody);
    }

}