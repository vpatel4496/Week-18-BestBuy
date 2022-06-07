package com.bestbuy.productinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreTest extends TestBase {

    static String name = "Robinhood" + TestUtils.getRandomValue();
    static String type = "smallbox";
    static String address = "105 County grove B2";
    static String address2 = "";
    static String city = "Roseville";
    static String state = "NY";
    static String zip = "34523";
    static double lat = 56.87364;
    static double lng = -67.563452;
    static String hours = "Mon: 10-9; Tue 10-9;";
    static HashMap<Object,Object> services;
    static int storesId;

    @Steps
    StoreSteps storeSteps;

    @Title("This will get all stores")
    @Test
    public void  test001(){
        ValidatableResponse response = storeSteps.getStore().log().all().statusCode(200);}

    @Title("This will create new store")
    @Test
    public void test002() {
        HashMap<Object,Object> services = new HashMap<Object,Object>();
        ValidatableResponse response = storeSteps.createStores(name, type, address, address2, city, state, zip, (int) lat, (int) lng, hours, services);
        response.log().all().statusCode(201);
        storesId=response.log().all().extract().path("id");
        System.out.println(storesId);

    }
    @Title("Verify if the product was added to store")
    @Test
    public void test003(){
        storeSteps.getStoreById(storesId).log().all().statusCode(200);
    }

    @Title("Update the product information and verify the updated information")
    @Test
    public void test004(){
        HashMap<Object,Object> services = new HashMap<>();
        name = name + "_updated";
        city = city + "London";
        storeSteps.updateStores(storesId,name,type,address,address2,city,state,zip,(int)lat,(int)lng,hours,services).log().all();
        HashMap<String,Object> productMap = storeSteps.getProductsById(storesId);
        Assert.assertThat(productMap,hasValue(name));
    }
    @Title("Delete store")
    @Test
    public void test005(){
        storeSteps.deleteStoreByID(storesId).statusCode(200);
        storeSteps.getStoreById(storesId).statusCode(404);
    }


}
