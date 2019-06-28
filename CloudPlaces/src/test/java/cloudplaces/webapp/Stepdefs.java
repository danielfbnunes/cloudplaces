package cloudplaces.webapp;

import cloudplaces.webapp.CloudPlacesApplication;
import cloudplaces.webapp.database_queries.GeneralQueries;
import cloudplaces.webapp.database_queries.UserQueries;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource (value={"classpath:application.properties"})
@SpringBootTest(classes = CloudPlacesApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Stepdefs {
    private WebDriver driver;
    private final String baseUrl = "http://localhost:8080/";
    private boolean acceptNextAlert = true;
    private final StringBuffer verificationErrors = new StringBuffer();
    private final long waitingTimer = 5000;
    
    @Autowired
    UserQueries uq;
    
    @Autowired
    private GeneralQueries gq;
    
    //Login Test Success
    @Given("John as an account")
    public void creatJohnsAccount(){
        gq.reloadDatabase();
    }
    
    @Given("he is on the login page")
    public void getLoginPage() throws InterruptedException{
        gq.reloadDatabase();
        Map<String, String> environment = new HashMap<>();
        // check if a GUI is available
        if(System.getenv("DISPLAY") == null || System.getenv("DISPLAY").equals(":99")){
           System.out.println("Setting Up a Display");
           environment.put("DISPLAY", ":99");
        }
        else System.out.println("Using computers defaul GUI");

        GeckoDriverService service = new GeckoDriverService.Builder()
                .usingAnyFreePort()
                .withEnvironment(environment)
                .build();;

        driver = new FirefoxDriver(service);
        
        driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
        System.out.println("A pedir Login");
        driver.get(baseUrl+"login");
        System.out.println("Recebeu Login");
        Thread.sleep(waitingTimer);
    }
    
    @When("he fills the email with {string}")
    public void fillUsername(String email) {
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
    }
    
    @When("the password with {string}")
    public void fillPassword(String password) {
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
    }
    
    @Then("he should be redirected to his homepage")
    public void homepageRedirect() throws InterruptedException{
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
        Thread.sleep(waitingTimer);
        driver.findElement(By.xpath("//div/div/div")).click();
        try {
            assertEquals("Cloud Places", driver.findElement(By.linkText("Cloud Places")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.close();
    }
    
    //Login Test Failure 
    @Then("he should see a negative feedback message informing about the failure of the login")
    public void loginFailure() throws InterruptedException {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
        Thread.sleep(waitingTimer);
        driver.findElement(By.id("error")).click();
        try {
          assertEquals("Password or Email Incorrect", driver.findElement(By.id("error")).getText());
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
        driver.close();
    }
    
    //Create an Account With Success
    
    @When("he presses the sign up button")
    public void signupAccess() throws InterruptedException{
        driver.findElement(By.linkText("Sign Up")).click();
        Thread.sleep(waitingTimer);
    }
    

    @When("fills the username with {string}")
    public void fillsUsername(String username) {
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(username);
    }
    
    @When("fills the password with {string}")
    public void fillsPassword(String password) {
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
    }
 
    @When("fills the email with {string}")
    public void fillsEmail(String email) {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);

    }
    @When("fills the cellphone with {string}")
    public void fillsCellphone(String phone) {
        driver.findElement(By.id("cellphone")).click();
        driver.findElement(By.id("cellphone")).clear();
        driver.findElement(By.id("cellphone")).sendKeys("99999999");
    }
    
    @Then("he should see a success message")
    public void checkSuccessMessage() throws InterruptedException {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Photo'])[1]/following::span[1]")).click();
        Thread.sleep(waitingTimer);
    }
    
    @Then("be able to login with email {string}")
    public void fillLoginEmail(String email) {
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
    }
    
    @Then("password {string}")
    public void fillLoginPassword(String password) {
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
    }
    
    @Then("enter")
    public void executeLogin() throws InterruptedException {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
        Thread.sleep(waitingTimer);
        driver.findElement(By.xpath("//div/div/div")).click();
        try {
            assertEquals("Cloud Places", driver.findElement(By.linkText("Cloud Places")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.close();
    }
    
    //Create Account Failure
    @Then("he should see an unsuccessful message")
    public void checkUnsuccessfulMessage(){
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Photo'])[1]/following::span[1]")).click();
        assertEquals("[Error] User was not added! A user with the same email already exists!", closeAlertAndGetItsText());
    }
    
    @Then("can't be able to login using email {string}")
    public void can_t_be_able_to_login_using_username(String email) throws InterruptedException {
        driver.findElement(By.linkText("Cloud Places")).click();
        Thread.sleep(waitingTimer);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
    }
    
    @Then("using password {string}")
    public void passwrodFillAndFailureCheck(String password) {
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
    }
    
    @Then("log in")
    public void errorOnLogIn() throws InterruptedException {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
        Thread.sleep(waitingTimer);
        try {
          assertEquals("Password or Email Incorrect", driver.findElement(By.id("error")).getText());
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
        driver.close();
    }

    
      private String closeAlertAndGetItsText() {
        try {
          Alert alert = driver.switchTo().alert();
          String alertText = alert.getText();
          if (acceptNextAlert) {
            alert.accept();
          } else {
            alert.dismiss();
          }
          return alertText;
        } finally {
          acceptNextAlert = true;
        }
    }
}
