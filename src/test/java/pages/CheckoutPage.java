package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;


    private By emailInput = By.id("Email");
    private By passwordInput = By.id("Password");
    private By loginBtn = By.className("login-button");


    private By termsOfService = By.id("termsofservice");
    private By checkoutBtn = By.id("checkout");


    private By billingAddressDropdown = By.id("billing-address-select");
    private By countryDropdown = By.id("BillingNewAddress_CountryId");
    private By cityInput = By.id("BillingNewAddress_City");
    private By addressInput = By.id("BillingNewAddress_Address1");
    private By zipInput = By.id("BillingNewAddress_ZipPostalCode");
    private By phoneInput = By.id("BillingNewAddress_PhoneNumber");
    private By billingContinueBtn = By.cssSelector("input[onclick='Billing.save()']");


    private By shippingAddressContinueBtn = By.cssSelector("input[onclick='Shipping.save()']");
    private By shippingMethodContinueBtn = By.cssSelector("input[onclick='ShippingMethod.save()']");
    private By paymentMethodContinueBtn = By.cssSelector("input[onclick='PaymentMethod.save()']");
    private By paymentInfoContinueBtn = By.cssSelector("input[onclick='PaymentInfo.save()']");
    private By confirmOrderBtn = By.cssSelector("input[onclick='ConfirmOrder.save()']");


    private By successMessage = By.xpath("//strong[contains(text(),'Your order has been successfully processed')]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void login(String email, String password) {

        if (driver.findElements(emailInput).size() > 0) {
            driver.findElement(emailInput).sendKeys(email);
            driver.findElement(passwordInput).sendKeys(password);
            driver.findElement(loginBtn).click();


            if (driver.findElements(checkoutBtn).size() > 0) {
                driver.findElement(termsOfService).click();
                driver.findElement(checkoutBtn).click();
            }
        }
    }

    public void fillBillingAddress(String country, String city, String address, String zip, String phone) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(billingContinueBtn));


        if (driver.findElements(billingAddressDropdown).size() > 0) {
            Select select = new Select(driver.findElement(billingAddressDropdown));
            select.selectByVisibleText("New Address");
        }


        wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropdown));


        new Select(driver.findElement(countryDropdown)).selectByVisibleText(country);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(zipInput).sendKeys(zip);
        driver.findElement(phoneInput).sendKeys(phone);

        driver.findElement(billingContinueBtn).click();

        wait.until(ExpectedConditions.elementToBeClickable(shippingAddressContinueBtn));
    }

    public void clickContinueShippingAddress() {
        driver.findElement(shippingAddressContinueBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinueBtn));
    }

    public void clickContinueShippingMethod() {
        driver.findElement(shippingMethodContinueBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinueBtn));
    }

    public void clickContinuePaymentMethod() {
        driver.findElement(paymentMethodContinueBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(paymentInfoContinueBtn));
    }

    public void clickContinuePaymentInfo() {
        driver.findElement(paymentInfoContinueBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderBtn));
    }

    public void clickConfirm() {
        driver.findElement(confirmOrderBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}