package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ServicesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ServicesSteps {

    @Step
    public ValidatableResponse getServices(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then()
                .statusCode(200);
    }
    @Step
    public ValidatableResponse createServices(String name){
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(servicesPojo)
                .when()
                .post(EndPoints.GET_ALL_SERVICES)
                .then();
    }
    @Step
    public ValidatableResponse getSingleServicesById(int riya){
        return SerenityRest.given().log().all()
                .pathParam("riya",riya)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICES_BY_ID)
                .then()
                .statusCode(200);
    }

    @Step("Delete services by Id")
    public ValidatableResponse deleteServicesId(int riya){
        return SerenityRest.given().log().all()
                .pathParam("riya",riya)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then();
    }
    @Step("Update services by Id")
    public ValidatableResponse patchServicesById(int riya){
        return SerenityRest.given().log().all()
                .pathParam("riya", riya)
                .when()
                .patch(EndPoints.UPDATE_SERVICES_BY_ID)
                .then();
    }


}
