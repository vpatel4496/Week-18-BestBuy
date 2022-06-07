package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UtilitiesSteps {

    @Step
    public ValidatableResponse getVersion(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_VERSION)
                .then()
                .statusCode(200);

    }
    @Step
    public ValidatableResponse getHealthcheck(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_HEALTHCHECK)
                .then()
                .statusCode(200);
    }
}
