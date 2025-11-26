package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;

public class CartTest extends BaseTest {

    @Test(priority = 1)
    public void test01_EndToEndCheckout() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);


        driver.get("https://demowebshop.tricentis.com/141-inch-laptop");
        productPage.clickAddToCart();
        productPage.openShoppingCart();


        cartPage.acceptTermsAndCheckout();


        checkoutPage.login("mahmoudibrahim@gamail.com", "m123456789");


        checkoutPage.fillBillingAddress("Egypt", "Giza", "home", "12345", "01112539956");
        checkoutPage.clickContinueShippingAddress();
        checkoutPage.clickContinueShippingMethod();
        checkoutPage.clickContinuePaymentMethod();
        checkoutPage.clickContinuePaymentInfo();
        checkoutPage.clickConfirm();


        String actualMsg = checkoutPage.getSuccessMessage();
        Assert.assertTrue(actualMsg.contains("Your order has been successfully processed"), "Order execution failed!");
    }

    @Test(priority = 2)
    public void test02_AddProductToCart() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);


        driver.get("https://demowebshop.tricentis.com/141-inch-laptop");

        productPage.clickAddToCart();

        productPage.openShoppingCart();

        Assert.assertTrue(cartPage.isProductDisplayed(), "Cart is empty! Product was not added.");
    }


    @Test(priority = 3, dependsOnMethods = "test02_AddProductToCart")
    public void test03_ModifyQuantity() {

        driver.get("https://demowebshop.tricentis.com/cart");

        CartPage cartPage = new CartPage(driver);


        cartPage.updateQuantity("3");


        Assert.assertEquals(cartPage.getQuantity(), "3", "Quantity was not updated correctly!");
    }


    @Test(priority = 4, dependsOnMethods = "test03_ModifyQuantity")
    public void test04_RemoveProduct() {

        driver.get("https://demowebshop.tricentis.com/cart");

        CartPage cartPage = new CartPage(driver);

        cartPage.removeFirstProduct();

        String message = cartPage.getEmptyCartMessage();
        Assert.assertTrue(message.contains("Your Shopping Cart is empty"), "Cart is not empty after removing item!");
    }
}