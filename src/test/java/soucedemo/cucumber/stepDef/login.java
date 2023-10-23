package soucedemo.cucumber.stepDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseUrl = "http://www.saucedemo.com/";

    @Given("Login Page Swag Labs")
    public void loginpageSwagLabs(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //Assertion
        String LoginPageAssert = driver.findElement(By.xpath("//div[@class='login_logo']")).getText();
        Assert.assertEquals(LoginPageAssert, "Swag Labs");
    }
    @When("Input Username")
    public void inputUsername(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("Input Password")
    public void inputPassword() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("Click Login Button")
    public void clickLoginButton() {
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @Then("User Landing on Dashboard")
    public void userLandingOnDashboard() {
        String dashboardLogo = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        Assert.assertEquals(dashboardLogo, "Swag Labs");

        driver.close();
    }

    @When("Input invalid username")
    public void inputInvalidUsername() {
            driver.findElement(By.id("user-name")).sendKeys("standard_userr");
    }

    @Then("User get error message")
    public void userGetErrorMessage() {
        String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");

        driver.close();
    }
}
