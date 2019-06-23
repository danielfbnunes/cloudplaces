/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package selenium;

/**
 *
 * @author rd
 */

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

public class RedmineTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    
    @BeforeEach
    public void setUp() throws Exception {
        
        Map<String, String> environment = new HashMap<>();
        environment.put("DISPLAY", ":99");
        GeckoDriverService service = new GeckoDriverService.Builder()
        .usingAnyFreePort()
        .withEnvironment(environment)
        .build();;
        
        driver = new FirefoxDriver(service);
        baseUrl = "https://www.katalon.com/";
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    
    @Test
    public void testRedmine() throws Exception {
        driver.get("http://demo.redmine.org/");
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("rd");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("rdrd");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.linkText("Sign out")).click();
        assertEquals("Sign in", driver.findElement(By.linkText("Sign in")).getText());
    }
    
    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
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
;