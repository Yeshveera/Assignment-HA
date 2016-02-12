package com.HomeAway.PageObjects;

import com.HomeAway.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by yeshkumar on 2/11/16.
 */
public class ProfilePage {

    public void updateProfileAndLogOut(WebDriver driver) {

        WebUtil.click(driver, By.name("submit"));
        logOut(driver);
    }

    public void logOut(WebDriver driver) {
        WebUtil.click(driver, By.linkText("Log out"));
    }

    public void fillDetails(WebDriver driver, String keys) {
        //Record value entered here so we can assert it later with same values
        WebUtil.clearAndSendKeys(driver, By.id("wpsc_checkout_form_2"), keys);
    }

    public void verifyDetails(WebDriver driver, String keys) {
        WebUtil.waitForElementVisible(driver, By.id("wpsc_checkout_form_2"));
        WebUtil.assertFieldValue(driver,By.id("wpsc_checkout_form_2"), keys);
    }
}
