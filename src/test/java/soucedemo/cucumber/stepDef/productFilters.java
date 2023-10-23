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
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class productFilters {
    WebDriver driver;
    String baseUrl = "http://www.saucedemo.com/";

    @Given("User on Dashboard")
    public void userOnDashboard() {
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

    @When("User clicks the Filter menu")
    public void userClicksTheMenu(){
        String activeOptionElement = driver.findElement(By.xpath("//span[@class='active_option']")).getText();
        Assert.assertEquals(activeOptionElement, "Name (A to Z)");

    }

    @And("User uses the Prize Low to High filter")
    public void userUsesTheFilter() {
        WebElement filterDropdown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select = new Select(filterDropdown);
        select.selectByValue("hilo");
    }

    @Then("Products are sorted from the highest to lowest price")
    public void productsAreSortedFromTheHighestToLowestPrice() {
        String activeOptionElement = driver.findElement(By.xpath("//span[@class='active_option']")).getText();
        Assert.assertEquals(activeOptionElement, "Price (high to low)");

        driver.close();
    }

    @Given("Problem User on Dashboard")
    public void problemUserOnDashboard() {
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

    @Then("Products are not sorted")
    public void productsAreNotSorted() {
        String activeOptionElement = driver.findElement(By.xpath("//span[@class='active_option']")).getText();
        Assert.assertEquals(activeOptionElement, "Name (A to Z)");

        driver.close();
    }
}
