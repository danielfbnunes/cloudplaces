package cloudplaces.webapp;

import cloudplaces.webapp.database_queries.GeneralQueries;
import cloudplaces.webapp.database_queries.UserQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.PropertyRepository;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.entities.UserRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
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
    private static final StringBuffer sVerificationErrors = new StringBuffer();
    private static final long sWaitingTimer = 4000;
    
    @Autowired
    private PropertyRepository propertyRepo;
    
    @Autowired
    private UserRepository userRepo;
    
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
        driver.get(baseUrl+"login");
        Thread.sleep(sWaitingTimer);
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
        Thread.sleep(sWaitingTimer);
        driver.findElement(By.xpath("//div/div/div")).click();
        try {
            assertEquals("Cloud Places", driver.findElement(By.linkText("Cloud Places")).getText());
        } catch (Error e) {
            sVerificationErrors.append(e.toString());
        }
        driver.close();
    }
    
    //Login Test Failure 
    @Then("he should see a negative feedback message informing about the failure of the login")
    public void loginFailure() throws InterruptedException {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
        Thread.sleep(sWaitingTimer);
        driver.findElement(By.id("error")).click();
        try {
          assertEquals("Password or Email Incorrect", driver.findElement(By.id("error")).getText());
        } catch (Error e) {
          sVerificationErrors.append(e.toString());
        }
        driver.close();
    }
    
    //Create an Account With Success
    
    @When("he presses the sign up button")
    public void signupAccess() throws InterruptedException{
        driver.findElement(By.linkText("Sign Up")).click();
        Thread.sleep(sWaitingTimer);
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
        Thread.sleep(sWaitingTimer);
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
        Thread.sleep(sWaitingTimer);
        driver.findElement(By.xpath("//div/div/div")).click();
        try {
            assertEquals("Cloud Places", driver.findElement(By.linkText("Cloud Places")).getText());
        } catch (Error e) {
            sVerificationErrors.append(e.toString());
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
        Thread.sleep(sWaitingTimer);
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
        Thread.sleep(sWaitingTimer);
        try {
          assertEquals("Password or Email Incorrect", driver.findElement(By.id("error")).getText());
        } catch (Error e) {
          sVerificationErrors.append(e.toString());
        }
        driver.close();
    }
    
    //Search By Name
    @Given("John is logged in")
    public void doLogin() throws InterruptedException{
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
        driver.get(baseUrl+"login");
        
        Thread.sleep(sWaitingTimer);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("rafael@ua.pt");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(sWaitingTimer);

    }
    
    @Given("in the homepage")
    public void goToHomepage() throws InterruptedException{
        driver.findElement(By.linkText("Cloud Places")).click();
        Thread.sleep(sWaitingTimer);
    }
    
    @Given("there are properties with the name {string}")
    public void addHome(String houseName){
        User u1 = new User("Daniel Nunes",
            "test@ua.pt",
            "password",
            "987654321",
            "",
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        userRepo.save(u1);
        
        House h1 = new House(
                "Rua 1",
                3,
                20,
                150,
                houseName,
                "data",
                u1,
                2,
                1,
                "Situated in Ladywell this room enables you to have both privacy and convince - being only 10 minutes away by train to central London. The short train ride to London Bridge,Waterloo and shortly stopping at Charing Cross which will enable you to roam around Covent Garden and cross the river to Southbank or even venture further by bus or the underground to Oxford Street, Regent Street and all other destinations. Whether you have a short or long stay in London - my place is perfect.",
                "library_garden_test1_test2_test3_test4",
                1,
                new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
        );
        propertyRepo.save(h1);
    }
    
    @When("he searches by the name {string}")
    public void searchHouse(String houseName){
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(houseName);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Logout'])[1]/following::button[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Search'])[1]/following::div[8]")).click();
    }
    
    @Then("the properties with the name {string} should appear")
    public void verifySearch(String houseName){


        try {
          assertEquals(houseName, driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Search'])[1]/following::h5[1]")).getText());
        } catch (Error e) {
          sVerificationErrors.append(e.toString());
        }
        driver.close();
    }
    
    //Search By Location
    @Given("there are properties with the location {string}")
    public void addHomeLocation(String location){
        User u1 = new User("Daniel Nunes",
            "test@ua.pt",
            "password",
            "987654321",
            "",
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        userRepo.save(u1);
        
        House h1 = new House(
                "Rua 1",
                3,
                20,
                150,
                "House 4",
                location,
                u1,
                2,
                1,
                "Situated in Ladywell this room enables you to have both privacy and convince - being only 10 minutes away by train to central London. The short train ride to London Bridge,Waterloo and shortly stopping at Charing Cross which will enable you to roam around Covent Garden and cross the river to Southbank or even venture further by bus or the underground to Oxford Street, Regent Street and all other destinations. Whether you have a short or long stay in London - my place is perfect.",
                "library_garden_test1_test2_test3_test4",
                1,
                new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
        );
        propertyRepo.save(h1);
    }
    
    @When("he searches by the location {string}")
    public void searchHouseLocation(String location){   
        driver.findElement(By.id("location")).click();
        driver.findElement(By.id("location")).clear();
        driver.findElement(By.id("location")).sendKeys(location);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Search'])[1]/i[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Search'])[1]/following::div[8]")).click();
    }
    
    @Then("the properties with the location {string} should appear")
    public void verifySearchLocation(String location){
        try {
          assertEquals(location, driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='House 1'])[1]/following::span[1]")).getText());
        } catch (Error e) {
          sVerificationErrors.append(e.toString());
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
