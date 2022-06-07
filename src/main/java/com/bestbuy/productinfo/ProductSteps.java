package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductSteps {

    @Step
    public ValidatableResponse getProduct() {

        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_PRODUCT)
                .then()
                .statusCode(200);
    }

    @Step("Creating product  with name : {0}, type : {1},price : {2},shipping : {3},upc : {4},description : {5},manufacturer : {6},model : {7},url : {8},image : {9}")
    public ValidatableResponse CreateProductList(String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post()
                .then();
    }
    @Step("Getting product information with id : {0}")
    public HashMap<String, Object> getProductInformationById(int productsId) {
        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .pathParam("productId", productsId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCTS_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }
    @Step("Updating product information with name : {0}, type: {1}, price: {2}, shipping: {3}, upc: {4}, description: {5}, manufacturer: {6}, model: {7}, url: {8} and image: {8}")
    public ValidatableResponse updateProduct(int productId,String name,String type,int price,int shipping,String upc,String description,String manufacturer, String model, String url, String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        return SerenityRest.given().log().all()
                .contentType("application/json")
                .pathParam("productId",productId)
                .body(productPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCTS_BY_ID).then();

    }
    @Step("Deleting product information with product id : {0}")
    public ValidatableResponse deleteProductById(int productId){
        return  SerenityRest.given().log().all()
                .pathParam("productId",productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCTS_BY_ID)
                .then();
    }
    @Step("getting product information with stident id : {0}")
    public ValidatableResponse getProductById(int productId){
        return  SerenityRest.given().log().all()
                .pathParam("productId",productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCTS_BY_ID)
                .then();
    }

}
