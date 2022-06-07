package com.bestbuy.productinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ServicesTest extends TestBase {

    static int riya;

    static String name = "Tesco" + TestUtils.getRandomValue();
    static int servicesId;

    @Steps
    ServicesSteps servicesSteps;

    @Title("This will get all the services")
    @Test
    public void test001(){
        ValidatableResponse response = servicesSteps.getServices().log().all().statusCode(200);
    }
    @Title("This will create new services")
    @Test
    public void test002(){
        ValidatableResponse response = servicesSteps.createServices(name).log().all().statusCode(201);
        riya = response.log().all().extract().path("id");
        System.out.println(riya);


    }
    @Title("Get single services")
    @Test
    public void test003(){
        servicesSteps.getSingleServicesById(riya).log().all().statusCode(200);
        System.out.println(riya);
    }

    @Title("Delete services")
    @Test
    public void test004(){
        servicesSteps.deleteServicesId(riya).statusCode(200);
        System.out.println(riya);
    }
    @Title("Update services")
    @Test
    public void test005(){
        servicesSteps.patchServicesById(riya).statusCode(404);
    }

}
