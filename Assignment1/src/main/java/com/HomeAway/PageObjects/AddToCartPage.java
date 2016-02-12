package com.HomeAway.PageObjects;

import com.HomeAway.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.assertEquals;

/**
 * Created by yeshkumar on 2/11/16.
 */
public class AddToCartPage {
    public String verifyQuantityAndGetItemPrice(WebDriver driver, String i) {

        WebElement getQuantity = driver.findElement(By.name("quantity"));
        String itemPrice = WebUtil.getElementText(driver, By.cssSelector("[class=\"pricedisplay\"]"));
        WebUtil.assertFieldValue(driver, By.name("quantity"), i);
        return itemPrice;
    }

    public void updateQuantity(WebDriver driver, String i) {

        //WebElement getQuantity = driver.findElement(By.name("quantity"));
        WebUtil.clearAndSendKeys(driver, By.name("quantity"), i );
        String itemPrice = WebUtil.getElementText(driver, By.cssSelector("[class=\"pricedisplay\"]"));
        WebUtil.assertFieldValue(driver, By.name("quantity"), i);
        WebUtil.click(driver, By.name("submit"));
    }

    public CheckoutPage confirmAndContinue(WebDriver driver) {
        WebUtil.clickWhenVisible(driver, By.linkText("Continue"));
        return PageFactory.initElements(driver, CheckoutPage.class);
    }

    public void verifyEmptyMessageAlert(WebDriver driver) {
        String cartEmptyMessage = WebUtil.getElementText(driver, By.cssSelector("div[class=\"entry-content\"]"));
        String expectedErrMsg = "Oops, there is nothing in your cart.";
        assertEquals(expectedErrMsg,cartEmptyMessage);
    }
}
