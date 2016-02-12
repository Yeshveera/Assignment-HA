package com.HomeAway.util;

import com.HomeAway.PageObjects.AccountPage;
import com.HomeAway.PageObjects.HomePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by yeshkumar on 2/10/16.
 */
public class WebUtil {
    final static int WAIT_TIME_OUT = 30;

    public static void click(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    public static void selectDragDownValue(WebDriver driver, By by, String location) {

        WebElement selectCountry = driver.findElement(by);
        selectCountry.click();
        Select clickThis= new Select(selectCountry);
        clickThis.selectByVisibleText(location);
    }

    public static void click(WebDriver driver, WebElement element) {
        element.click();
    }

    public static void clickWhenVisible(WebDriver driver, By by) {
        waitForElementVisible(driver, by);
        click(driver, by);
    }

    public static void waitForElementVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static boolean isElementExist(WebDriver driver, By by)
    {
        return driver.findElements(by).size() > 0;
    }

    public static void clearAndSendKeys(WebDriver driver, By by, String s) {
        waitForElementVisible(driver, by);
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(s);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public static void assertFieldValue(WebDriver driver, By by, String expected) {

        WebElement element = driver.findElement(by);
        String actual = element.getAttribute("value");
        Assert.assertEquals("Strings does not match", expected, actual);
    }

    public static String getElementText(WebDriver driver, By by) {
        waitForElementVisible(driver, by);
        WebElement subjectArea = driver.findElement(by);
        return subjectArea.getText();
    }

    public static boolean isElementDisplayed(WebDriver driver, By by)
    {
        return driver.findElement(by).isDisplayed();
    }

    public static HomePage gotoHomePage(WebDriver driver) {
        driver.get("http://store.demoqa.com");
        return PageFactory.initElements(driver, HomePage.class);
    }

    public static AccountPage gotoAccountPage(WebDriver driver) {
        driver.get("http://store.demoqa.com");
        driver.manage().window().maximize();
        click(driver, By.id("account"));
        return PageFactory.initElements(driver, AccountPage.class);
    }

}
