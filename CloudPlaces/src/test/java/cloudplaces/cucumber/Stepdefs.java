package cloudplaces.cucumber;

import cloudplaces.webapp.CloudPlacesApplication;
import cloudplaces.webapp.database_queries.UserQueries;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.nio.charset.StandardCharsets;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource (value={"classpath:application.properties"})
@SpringBootTest(classes = CloudPlacesApplication.class ,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Stepdefs {
    private WebDriver driver;
    private final String baseUrl = "http://localhost:8080/";
    private final boolean acceptNextAlert = true;
    private final StringBuffer verificationErrors = new StringBuffer();
    
    @Autowired
    UserQueries uq;
    
    //Login Test Success
    @Given("John as an account")
    public void creatJohnsAccount(){
        String image1b64 = "data:image/jpeg;base64,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";
        byte[] b = image1b64.getBytes(StandardCharsets.UTF_8);
        uq.addUser("user1", "user1@ua.pt", "password", "123123123", b);
    }
    
    @Given("he is on the login page")
    public void getLoginPage() throws InterruptedException{
        driver = new FirefoxDriver();
        driver.get(baseUrl+"login");
        Thread.sleep(1000);
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
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div/div/div")).click();
        try {
            assertEquals("Cloud Places", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Home'])[1]/preceding::div[1]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.quit();
    }
    
    //Login Test Failure 
    @Then("he should see a negative feedback message informing about the failure of the login")
    public void loginFailure() throws InterruptedException {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("error")).click();
        try {
          assertEquals("Password or Email Incorrect", driver.findElement(By.id("error")).getText());
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
        driver.quit();
    }
    /*
    //Create an Account With Success
    
    @When("he presses the sign up button")
    public void signupAccess() throws InterruptedException{
        driver.findElement(By.linkText("Sign Up")).click();
        Thread.sleep(1000);
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
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
    }
    @When("fills the cellphone with {string}")
    public void fillsCellphone(String phone) {
        driver.findElement(By.id("cellphone")).click();
        driver.findElement(By.id("cellphone")).clear();
        driver.findElement(By.id("cellphone")).sendKeys("939939");
    }
    
    @When("adds a picture")
    public void addsPicture() {
        driver.findElement(By.id("photo")).click();
        driver.findElement(By.id("photo")).clear();
        driver.findElement(By.id("photo")).sendKeys("C:\\fakepath\\maxresdefault.jpg");
    }
    
    @Then("he should see a success message")
    public void checkSuccessMessage() throws InterruptedException {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Photo'])[1]/following::span[1]")).click();
        Thread.sleep(1000);
    }
    
    @Then("be able to login with email {string}")
    public void fillLoginEmail(String email) {
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("user2@ua.pt");
    }
    
    @Then("password {string}")
    public void fillLoginPassword(String password) {
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("password");
    }
    
    @Then("enter")
    public void executeLogin() throws InterruptedException {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div/div/div")).click();
        try {
          assertEquals("Cloud Places", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Home'])[1]/preceding::div[1]")).getText());
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
        driver.quit();
    }
    
    //Create Account Failure
    @Then("he should see an unsuccessful message")
    public void checkUnsuccessfulMessage() {
    }
    
    @Then("can't be able to login using email {string}")
    public void can_t_be_able_to_login_using_username(String email) {
    }
    
    @Then("using password {string}")
    public void passwrodFillAndFailureCheck(String password) {
        driver.quit();
    }
    
    @Then("log in")
    public void errorOnLogIn(String password) {
        driver.quit();
    }
*/
}
