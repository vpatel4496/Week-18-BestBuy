package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps {

    @Step
    public ValidatableResponse getStore(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then()
                .statusCode(200);
    }
    @Step("Creating stores with name: {0}, type: {1}, address: {2}, address2: {3}, city: {4},state: {5},zip: {6}, lat: {7}, lng: {8}, hours: {9}, services: {10}")
    public ValidatableResponse createStores( String name,String type, String address, String address2,String city, String state, String zip, Integer lat, Integer lng,
                                            String hours, HashMap<Object,Object> services){
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);
        return  SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post(EndPoints.GET_ALL_STORES)
                .then();

    }
    @Step("Getting product information with id : {0}")
    public HashMap<String,Object>getProductsById(int storesId){
        return SerenityRest.given().log().all()
                .pathParam("storesId",storesId)
                .when()
                .get(EndPoints.GET_SINGLE_STORES_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");

    }
    @Step("Updating stores with name: {0}, type: {1}, address: {2}, address2: {3}, city: {4},state: " + "{5},zip: {6}, lat: {7}, lng: {8}, hours: {9}, services: {10}")
    public ValidatableResponse updateStores(int storesId, String name, String type, String address, String address2, String city, String state, String zip, Integer lat, Integer lng,
                                            String hours, HashMap<Object, Object> services) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(name);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);
        return SerenityRest.given().log().all()
                .contentType("application/json")
                .pathParam("storesId",storesId)
                .body(storePojo)
                .when()
                .put(EndPoints.UPDATE_STORES_BY_ID)
                .then();
    }
    @Step("getting product information from Id: {0}")
    public ValidatableResponse getStoreById(int storesId){
        return SerenityRest.given().log().all()
                .pathParam("storesId",storesId)
                .when()
                .get(EndPoints.GET_SINGLE_STORES_BY_ID).then();
    }
    @Step("Delete stores by ID")
    public ValidatableResponse deleteStoreByID(int storesId){
        return SerenityRest.given().log().all()
                .pathParam("storesId",storesId)
                .when()
                .delete(EndPoints.DELETE_STORES_BY_ID)
                .then();
    }



}
