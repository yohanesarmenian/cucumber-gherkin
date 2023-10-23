package soucedemo.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class cartItems {
    WebDriver driver;
    String baseUrl = "http://www.saucedemo.com/";

    @Given("User Choose Items")
    public void userChooseItems(){
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

    @When("User clicks Add Item to Card for Sauce Labs Backpack")
    public void userClicksAddItemToCardForSauceLabsBackpack() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        //Assertion
        String removeItemBag = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).getText();
        Assert.assertEquals(removeItemBag,"Remove");
    }

    @And("User clicks Add Item to Card for Sauce Labs Bike Light")
    public void userClicksAddItemToCardForSauceLabsBikeLight() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).click();
        //Assertion
        String removeItemBag = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-bike-light']")).getText();
        Assert.assertEquals(removeItemBag,"Remove");
    }

    @And("User clicks cart logo")
    public void userClicksCartLogo() {
       String counter =  driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).getText();
       Assert.assertEquals(counter, "2");

       driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

       String titleCart = driver.findElement(By.xpath("//span[@class='title']")).getText();
       Assert.assertEquals(titleCart, "Your Cart");
    }

    @Then("Products are showed on cart page")
    public void productsAreShowedOnCartPage() {
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertFalse(cartItems.isEmpty());

        driver.close();
    }

    @Given("Problem User Choose Item")
    public void problemUserChooseItem() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
        //Assertion
        String LoginPageAssert = driver.findElement(By.xpath("//div[@class='login_logo']")).getText();
        Assert.assertEquals(LoginPageAssert, "Swag Labs");

        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        //Assertion
        String dashboardLogo = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        Assert.assertEquals(dashboardLogo, "Swag Labs");
    }

    @When("User click Add Item to Card for Sauce Labs Backpack")
    public void userClickAddItemToCardForSauceLabsBackpack() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        //Assertion
        String removeItemBag = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).getText();
        Assert.assertEquals(removeItemBag,"Remove");
    }

    @And("User clicks Remove Item to Card for Sauce Labs Backpack")
    public void userClicksRemoveItemToCardForSauceLabsBackpack() {
        driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).click();
    }

    @Then("Problem User cant Remove the item")
    public void problemUserCantRemoveTheItem() {
        //Assertion
        String removeItemBag = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).getText();
        Assert.assertEquals(removeItemBag,"Remove");

        driver.close();
    }

}
