import com.HomeAway.PageObjects.*;
import com.HomeAway.util.WebUtil;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by yeshkumar on 2/10/16.
 */
public class Assignment1 {
    WebDriver driver = new FirefoxDriver();


    @Test
    public void verifyOrderCheckoutTest1() {

        //Browse to webpage
        HomePage homePage = WebUtil.gotoHomePage(driver);

        //Go to product category and select Iphone
        homePage.browseProductCategory(driver, "iPhones");

        //Add product to cart
        AddToCartPage addProduct = homePage.findProductAndAddToCart(driver, "iPhones");

        //Assert to make sure product has been added
        String itemPrice = addProduct.verifyQuantityAndGetItemPrice(driver, "1");

        //Continue to checkoutpage
        CheckoutPage checkout = addProduct.confirmAndContinue(driver);

        //Calculate shipping by adding location 1
        checkout.calculateShipping(driver, "USA");

        //Verify amount
        checkout.VerifyTotalAmount(driver, itemPrice);
    }


    @Test
    public void verifyMyAccountTest2() {

        //Navigate to account page from homepage
        AccountPage myaccountPage = WebUtil.gotoAccountPage(driver);

        //Enter userName and Password credentials after they have been identified
        myaccountPage.enterCredentials(driver, "yashveera", "Test1234");

        //Sign in to profile page
        ProfilePage profilePage = myaccountPage.clickSignIn(driver);

        //Update first name,last name and address with some value
        String keys = "test";
        profilePage.fillDetails(driver, keys);

        //Save and logout of profile page
        profilePage.updateProfileAndLogOut(driver);

        //Navigate again to MyAccount Page
        AccountPage myaccountPage1 = WebUtil.gotoAccountPage(driver);

        //Enter credentials and sign in to my account that has already been created before
        myaccountPage1.enterCredentials(driver, "yashveera", "Test1234");
        ProfilePage profilePage1 = myaccountPage1.clickSignIn(driver);

        //Verify information that was updated earlier is saved and updated information is retreived
        profilePage1.verifyDetails(driver, keys);

        //Logout
        profilePage1.logOut(driver);

    }

    @Test
    public void verifyEmptyCartAlertTest3() {

        //Browse to webpage
        HomePage homePage = WebUtil.gotoHomePage(driver);

        //Go to product category and select Iphone
        homePage.browseProductCategory(driver, "iPhones");

        //Add product to cart
        AddToCartPage addProduct = homePage.findProductAndAddToCart(driver, "iPhones");

        //Update quantity to 0 and verify message is given to user
        addProduct.updateQuantity(driver, "0");
        addProduct.verifyEmptyMessageAlert(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}