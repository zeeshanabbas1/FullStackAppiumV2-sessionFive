package com.qa.tests;

import com.qa.base.AppFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;

import java.lang.reflect.Method;

public class LoginTest extends AppFactory {

    LoginPage loginPage;
    ProductPage productPage;
    @BeforeMethod
    public void setup(Method method) {
        loginPage = new LoginPage();
        System.out.println("\n" + "********** Starting Test " +  method.getName() + " **********");
    }

    @Test
    public void verifyInvalidUserName() {
        System.out.println("This test will verify that the error message is correct for an invalid user name.");
        loginPage.enterUserName("invalid_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Username and password do not match any user in this service.";
        System.out.println("Actual error message is - " + actualErrorMessage + "\n" + "Expected error message is - " + expectedErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }
    @Test
    public void verifyInvalidPassword() {
        System.out.println("This test will verify that the error message is correct for an invalid password");
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Username and password do not match any user in this service.";
        System.out.println("Actual error message is - " + actualErrorMessage + "\n" + "Expected error message is - " + expectedErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }
    @Test
    public void verifyInvalidUserNameAndPassword() {
        System.out.println("This test will verify that the error message is correct for an invalid user name and password");
        loginPage.enterUserName("invalid_user");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Username and password do not match any user in this service.";
        System.out.println("Actual error message is - " + actualErrorMessage + "\n" + "Expected error message is - " + expectedErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }
    @Test
    public void verifyValidLogin() {
        System.out.println("This test will verify valid user login");
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        productPage = loginPage.clickLoginButton();

        String actualProductTitle = productPage.getTitle();
        String expectedProductTitle = "PRODUCTS";
        System.out.println("Actual Product page title is - " + actualProductTitle + "\n" + "Expected Product page title is - " + expectedProductTitle);
        Assert.assertEquals(actualProductTitle, expectedProductTitle);

        System.out.println("Logged in");

    }

}
