package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By quantityInput = By.className("qty-input");
    private By updateCartBtn = By.name("updatecart");
    private By removeCheckbox = By.name("removefromcart");
    private By emptyCartMessage = By.className("order-summary-content");


    private By termsOfService = By.id("termsofservice");
    private By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isProductDisplayed() {
        return driver.findElements(quantityInput).size() > 0;
    }

    public String getQuantity() {
        return driver.findElement(quantityInput).getAttribute("value");
    }

    public void updateQuantity(String newQuantity) {
        WebElement qtyBox = driver.findElement(quantityInput);
        qtyBox.clear();
        qtyBox.sendKeys(newQuantity);
        driver.findElement(updateCartBtn).click();
    }

    public void removeFirstProduct() {
        driver.findElement(removeCheckbox).click();
        driver.findElement(updateCartBtn).click();
    }

    public String getEmptyCartMessage() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                emptyCartMessage, "Your Shopping Cart is empty!"
        ));
        return driver.findElement(emptyCartMessage).getText().trim();
    }


    public void acceptTermsAndCheckout() {
        driver.findElement(termsOfService).click();
        driver.findElement(checkoutBtn).click();
    }
}