package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;


    private By addToCartBtn = By.cssSelector(".add-to-cart-button");
    private By successMessage = By.id("bar-notification");
    private By closeNotification = By.cssSelector(".close");
    private By shoppingCartLink = By.className("ico-cart");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickAddToCart() {
        driver.findElement(addToCartBtn).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }

    public void closeNotificationBar() {

        if (driver.findElements(successMessage).size() > 0) {
            driver.findElement(closeNotification).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(successMessage));
        }
    }

    public void openShoppingCart() {
        closeNotificationBar();
        driver.findElement(shoppingCartLink).click();
    }
}