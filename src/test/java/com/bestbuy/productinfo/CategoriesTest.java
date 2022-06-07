package com.bestbuy.productinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(SerenityRunner.class)
public class CategoriesTest extends TestBase {

    static String name = "Monson Weeding" + TestUtils.getRandomValue();
    static String id = "0096" + TestUtils. getRandomValue();
    static int categoriesId;

    @Steps
    CategoriesSteps categoriesSteps;

    @Title("This will get all categories")
    @Test
    public void test001(){
        ValidatableResponse response = categoriesSteps.getCategories().log().all().statusCode(200);
    }
    @Title("Create new categories")
    @Test
    public void test002(){
        ValidatableResponse response = categoriesSteps.createCategories(name,id);
        response.log().all().statusCode(201);
       // categoriesId=response.log().all().extract().path("id");
        System.out.println(categoriesId);
    }
    @Title("Verify if the categories was added to store")
    @Test
    public void test003(){
        categoriesSteps.getCategories().log().all().statusCode(200);
    }
    @Title("Update the categories")
    @Test
    public void test004(){
        HashMap<Object,Object> subCategories = new HashMap<>();
        name = name + "London School";
        id = id + "VM";
        categoriesSteps.updateCategories(name,id).log().all();
      // HashMap<String,Object> productMap = categoriesSteps.getCategoriesById(categoriesId);

    }
    @Title("Delete store")
    @Test
    public void test005(){
        categoriesSteps.deleteCategoriesById(id).statusCode(200);
        categoriesSteps.getCategoriesById(id).statusCode(404);

    }


}
