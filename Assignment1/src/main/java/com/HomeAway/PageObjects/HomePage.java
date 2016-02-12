package com.HomeAway.PageObjects;

import com.HomeAway.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by yeshkumar on 2/10/16.
 */
public class HomePage {


    public void browseProductCategory(WebDriver driver, String s) {

        WebElement productNameCategory = driver.findElement(By.linkText("Product Category"));
        Actions actions = new Actions (driver);
        actions.moveToElement(productNameCategory);
        actions.perform();
        WebUtil.waitForElementVisible(driver, By.linkText("Product Category"));
        WebUtil.clickWhenVisible(driver, By.linkText("iPhones"));

    }

    public AddToCartPage findProductAndAddToCart(WebDriver driver, String product) {

        WebUtil.click(driver, By.linkText("Apple iPhone 4S 16GB SIM-Free – Black"));

        //Remove it if not needed
        String productPrice = WebUtil.getElementText(driver, By.cssSelector("span[class='currentprice pricedisplay product_price_96']"));

        //Add to cart
        WebUtil.click(driver,By.cssSelector("input[name=\"Buy\"][value=\"Add To Cart\"]") );

        //Handle pop -up window while adding it to cart

        WebUtil.waitForElementVisible(driver, By.id("fancy_notification_content"));

        WebUtil.clickWhenVisible(driver, By.cssSelector("[class=\"go_to_checkout\"]"));
        return PageFactory.initElements(driver, AddToCartPage.class);

    }

}
