package selenium_cookbook.controlling_test_flow.javascript_alert;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Walker
 * Date: 8/12/13
 * Time: 12:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class AlertBox {
    WebDriver driver = new FirefoxDriver();

    @BeforeMethod
    public void startUp() {

        driver.get("http://www.quackit.com/javascript/javascript_alert_box.cfm");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//
//    }

    @Test
    public void testWindowPopupUsingTitle() {

        WebElement alertBoxButton = driver.findElement(By.cssSelector(".result>input"));

        alertBoxButton.click();

        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();
        // how to click on JS ALert window
        alert.accept();

        assertTrue(alertText.equals("Thanks... I feel much better now!"));
    }
}
