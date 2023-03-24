package org.selenium.api.actions;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class SignUpApi {

    private Cookies cookies;
    public Cookies getCookies() {
        return cookies;
    }

    public String fetchRegisterNonceValueUsingGroovy(){
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll { it.@name == 'woocommerce-register-nonce' }.@value");
    }

    private Response getAccount(){
        cookies = new Cookies();
        Response response = given().
                baseUri("https://askomdch.com").
                cookies(getCookies()).
                log().all().
                when().
                get("/account").
                then().
                log().all().
                extract().response();

        if(response.getStatusCode() != 200){
            throw new RuntimeException("Failed to fetch the account, HTTP Status Code: " + response.getStatusCode());
        }
        return response;
    }
}
