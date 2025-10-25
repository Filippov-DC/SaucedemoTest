package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTest extends BaseTest {
    
    private WebDriverWait wait;
    
    private void initWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
    }
    
    @Test
    public void checkPositiveLoginTest() {
        initWait();
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        WebElement title = driver.findElement(By.className("title"));
        Assert.assertTrue(title.getText().contains("Products"));
    }
    
    @Test
    public void checkNegativeLoginTest() {
        initWait();
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("wrong_user");
        driver.findElement(By.id("password")).sendKeys("wrong_password");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));
        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMessage.getText().contains("Username and password do not match"));
    }
    
    @Test
    public void testEmptyUsername() {
        initWait();
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));
        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMessage.getText().contains("Username is required"));
    }
    
    @Test
    public void testEmptyPassword() {
        initWait();
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));
        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMessage.getText().contains("Password is required"));
    }
    
    @Test
    public void testLockedUser() {
        initWait();
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));
        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMessage.getText().contains("Sorry, this user has been locked out"));
    }
    
    @Test
    public void testAddToCart() {
        initWait();
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        WebElement title = driver.findElement(By.className("title"));
        Assert.assertTrue(title.getText().contains("Products"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")));
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(cartBadge.getText(), "1");
    }
    
    @Test
    public void testLogout() {
        initWait();
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        driver.findElement(By.id("react-burger-menu-btn")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        driver.findElement(By.id("logout_sidebar_link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement usernameField = driver.findElement(By.id("user-name"));
        Assert.assertTrue(usernameField.isDisplayed());
    }
    
    @Test
    public void testSortProducts() {
        initWait();
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("product_sort_container")));
        driver.findElement(By.className("product_sort_container")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("option[value='hilo']")));
        driver.findElement(By.cssSelector("option[value='hilo']")).click();
        WebElement firstProduct = driver.findElement(By.cssSelector(".inventory_item_name"));
        Assert.assertTrue(firstProduct.getText().contains("Sauce Labs Fleece Jacket"));
    }
}