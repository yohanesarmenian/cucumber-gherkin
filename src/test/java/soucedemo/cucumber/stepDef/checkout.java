package soucedemo.cucumber.stepDef;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class checkout {
    WebDriver driver;
    String baseUrl = "http://www.saucedemo.com/";

    @Given("User Add items")
    public void userAddItems(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
        //Assertion
        String LoginPageAssert = driver.findElement(By.xpath("//div[@class='login_logo']")).getText();
        Assert.assertEquals(LoginPageAssert, "Swag Labs");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        //Assertion
        String dashboardLogo = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        Assert.assertEquals(dashboardLogo, "Swag Labs");
    }

    @When("User on Cart Page")
    public void userOnCartPage() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        //Assertion
        String removeItemBag1 = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).getText();
        Assert.assertEquals(removeItemBag1,"Remove");

        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).click();
        //Assertion
        String removeItemBag2 = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-bike-light']")).getText();
        Assert.assertEquals(removeItemBag2,"Remove");

        String counter =  driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).getText();
        Assert.assertEquals(counter, "2");

        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        String titleCart = driver.findElement(By.xpath("//span[@class='title']")).getText();
        Assert.assertEquals(titleCart, "Your Cart");

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertFalse(cartItems.isEmpty());
    }

    @And("Click Check Out button")
    public void clickCheckOutButton() {
        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        String titleInformation = driver.findElement(By.xpath("//span[@class='title']")).getText();
        Assert.assertEquals(titleInformation, "Checkout: Your Information");
    }

    @And("Fill the Information page")
    public void fillTheInformationPage() {
        driver.findElement(By.id("first-name")).sendKeys("Standard-User");
        driver.findElement(By.id("last-name")).sendKeys("User");
        driver.findElement(By.id("postal-code")).sendKeys("1234567");
        driver.findElement(By.xpath("//input[@id='continue']")).click();

        String titleOverview = driver.findElement(By.xpath("//span[@class='title']")).getText();
        Assert.assertEquals(titleOverview, "Checkout: Overview");
    }

    @And("Click finish button")
    public void clickFinishButton() {
        driver.findElement(By.xpath("//button[@id='finish']")).click();

        String titleComplete = driver.findElement(By.xpath("//span[@class='title']")).getText();
        Assert.assertEquals(titleComplete, "Checkout: Complete!");
    }

    @Then("User success order")
    public void userSuccessOrder() {
        String buttonBackHome = driver.findElement(By.xpath("//button[@id='back-to-products']")).getText();
        Assert.assertEquals(buttonBackHome, "Back Home");

        driver.close();
    }

    @But("Didn't Fill the first Name Information page")
    public void didnTFillTheFirstNameInformationPage() {
        driver.findElement(By.id("last-name")).sendKeys("User");
        driver.findElement(By.id("postal-code")).sendKeys("1234567");
        driver.findElement(By.xpath("//input[@id='continue']")).click();
    }

    @Then("User get error notification")
    public void userGetErrorNotification() {
        String errorNotification = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertEquals(errorNotification, "Error: First Name is required");

        driver.close();
    }
}
