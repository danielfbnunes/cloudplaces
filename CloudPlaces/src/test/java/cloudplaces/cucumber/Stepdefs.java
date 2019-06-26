package cloudplaces.cucumber;

import cloudplaces.webapp.CloudPlacesApplication;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource (value={"classpath:application.properties"})
@SpringBootTest(classes = CloudPlacesApplication.class ,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Stepdefs {
    
    //Example
    @Given("John as already logged in")
    public void test1(){
        Map<String, String> environment = new HashMap<>();
        WebDriver driver;
        // check if a GUI is available
        if(System.getenv("DISPLAY") == null || System.getenv("DISPLAY").equals(":99")){
           System.out.println("Setting Up a Display");
           environment.put("DISPLAY", ":99");
        }
        else System.out.println("Using computer’s defaul GUI");

        GeckoDriverService service = new GeckoDriverService.Builder()
        .usingAnyFreePort()
        .withEnvironment(environment)
        .build();;

        driver = new FirefoxDriver(service);
        
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/getIndex");
        driver.quit();
        System.out.println("Entrou");
    }
    
    @When("He presses the home button")
    public void test2(){
        System.out.println("Entrou");
    }
    
    @Then("He should see the list of properties")
    public void test3(){
        System.out.println("Entrou");
    }//*/
}
