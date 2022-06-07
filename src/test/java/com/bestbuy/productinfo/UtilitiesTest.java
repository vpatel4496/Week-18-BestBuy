package com.bestbuy.productinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UtilitiesTest extends TestBase {

    @Steps
    UtilitiesSteps utilitiesSteps;

    @Title("This will get the version")
    @Test
    public void test001(){
        ValidatableResponse response = utilitiesSteps.getVersion().log().all().statusCode(200);
    }
    @Title("This will get the healthcheck")
    @Test
    public void test002(){
        ValidatableResponse response = utilitiesSteps.getHealthcheck().log().all().statusCode(200);
    }


}
