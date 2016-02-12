package com.HomeAway.PageObjects;

import com.HomeAway.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by yeshkumar on 2/11/16.
 */
public class AccountPage {


    public static ProfilePage clickSignIn(WebDriver driver) {

        WebUtil.click(driver, By.id("login"));
        WebUtil.clickWhenVisible(driver, By.linkText("Your Details"));
        return PageFactory.initElements(driver, ProfilePage.class);
    }


    public static void enterCredentials(WebDriver driver, String usrName, String password) {

        WebUtil.waitForElementVisible(driver,By.id("log"));
        WebUtil.clearAndSendKeys(driver, By.id("log"), usrName);
        WebUtil.clearAndSendKeys(driver, By.id("pwd"), password);

        //WebElement passwd = driver.findElement(By.id("pwd"));
        //passwd.clear();
        //passwd.sendKeys("Test1234");
    }
}
