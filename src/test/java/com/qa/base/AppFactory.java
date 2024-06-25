package com.qa.base;

import com.qa.utils.ConfigReader;
import com.qa.utils.Utilities;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppFactory {

    public static AppiumDriver driver;
    public static ConfigReader configReader;

    @BeforeTest
    @Parameters({"platformName", "platformVersion", "deviceName"})
    public void initializer(String platformName, String platformVersion, String deviceName) throws MalformedURLException {
    try {
        configReader = new ConfigReader();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("deviceName", deviceName); //emulator-5554
        capabilities.setCapability("appPackage", configReader.getAppPackage()); //emulator-5554
        capabilities.setCapability("appActivity", configReader.getAppActivity()); //emulator-5554
        capabilities.setCapability("newCommandTimeout", configReader.getCommandTimeoutValue());
        capabilities.setCapability("automationName", configReader.getAutomationName());
        capabilities.setCapability("noReset", configReader.getNoReset());
        capabilities.setCapability("app", System.getProperty("user.dir") + configReader.getApkPath());

        driver = new AppiumDriver(new URL(configReader.getAppiumServerEndpointURL()), capabilities);
        AppDriver.setDriver(driver);

        System.out.println("Opening Appium Server");
    } catch (Exception exception){
        exception.printStackTrace();
        throw exception;    }
    }

    public void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Utilities.WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        waitForVisibility(element);
        element.click();
    }
    public void sendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }
    public String getAttribute(WebElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    @AfterTest
    public static void quitDriver() {
            if(null != driver) {
                driver.quit();
                System.out.println("Driver quit...");
            }
    }

}
