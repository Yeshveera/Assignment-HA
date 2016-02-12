package com.HomeAway.PageObjects;

import com.HomeAway.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

/**
 * Created by yeshkumar on 2/11/16.
 */
public class CheckoutPage {

    public void calculateShipping(WebDriver driver, String country) {

        //selectByValue("US");
        WebUtil.waitForElementVisible(driver, By.id("uniform-current_country"));
        WebUtil.selectDragDownValue(driver,By.id("current_country"), "USA");
        WebUtil.click(driver, By.name("wpsc_submit_zipcode"));

        //Wait till the time it calculates shipping and recalculates amount
        WebUtil.waitForElementVisible(driver, By.name("wpsc_submit_zipcode"));

    }

    public void VerifyTotalAmount(WebDriver driver, String itemPrice) {

        //Assert price on product page matches price before shipping
        String itemCost = WebUtil.getElementText(driver, By.xpath("//td[contains(text(),\"Item Cost:\")]/following-sibling::td//span[@class=\"pricedisplay\"]"));
        assertEquals("Product price doesnt match as per listed price", itemPrice, itemCost);

        //Assert price after adding shipping is greater than item price and shipping of US $12 is added
        String totalCost = WebUtil.getElementText(driver, By.xpath("//span[@id=\"checkout_total\"]/span"));

        //Eliminate dollar sign from price
        itemPrice = itemPrice.substring(1);
        totalCost = totalCost.substring(1);

        //Add shipping cost
        float price = Float.parseFloat(itemPrice);
        float totalPrice = Float.parseFloat(totalCost);

        int expectedTotal = (int)price + 12;
        int t = (int)totalPrice;
        assertEquals(expectedTotal,t);
    }
}
