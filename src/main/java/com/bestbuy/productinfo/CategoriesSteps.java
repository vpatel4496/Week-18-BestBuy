package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.CategoriesPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class CategoriesSteps {


    @Step
    public ValidatableResponse getCategories(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then()
                .statusCode(200);
    }
    @Step("Create categories with name: {0}, id {1}")
    public ValidatableResponse createCategories(String name,String id){
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);
        return SerenityRest.given().log().all()
                .contentType("application/json")
                .body(categoriesPojo)
                .when()
                .post(EndPoints.GET_ALL_CATEGORIES)
                .then();

    }
    @Step("Getting catagories information with id: {0}")
    public HashMap<String,Object>getCategoriesById(int id){
        return SerenityRest.given().log().all()
                .pathParam("id",id)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORIES_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }
    @Step("Updating store with name: {0}, id {1}")
    public ValidatableResponse updateCategories(String name, String id){
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);
        return SerenityRest.given().log().all()
                .contentType("application/json")
                .pathParam("id",id)
                .body(categoriesPojo)
                .when()
                .put(EndPoints.UPDATE_CATEGORIES_BY_ID)
                .then();
    }
    @Step("getting categories information from Id: {0}")
    public ValidatableResponse getCategoriesById(String id){
        return  SerenityRest.given().log().all()
                .pathParam("id",id)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORIES_BY_ID)
                .then();
    }@Step("Delete categories by Id")
    public ValidatableResponse deleteCategoriesById(String id){
        return SerenityRest.given().log().all()
                .pathParam("id",id)
                .when()
                .delete(EndPoints.DELETE_CATEGORIES_BY_ID)
                .then();
    }
}
