package selenium_cookbook.controlling_test_flow.popup_windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Walker
 * Date: 8/11/13
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Popup {

    WebDriver driver = new FirefoxDriver();

    @BeforeMethod
    public void startUp() {

        driver.get("http://www.quackit.com/javascript/popup_windows.cfm");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterMethod

    public void tearDown() {

        driver.quit();
    }

    @Test
    public void testWindowPopUp() {

        String parentWindowId = driver.getWindowHandle();//save page ID

        WebElement popUpWindow = driver.findElement(By.cssSelector(".result>input"));

        popUpWindow.click();

        driver.switchTo().window("popUpWindow");

        assertTrue(driver.getTitle().contains("Quackit.com"));

        driver.switchTo().window(parentWindowId);
        //Verify the driver context is in Parent Browser Window
        assertTrue(driver.getTitle().equals("JavaScript Popup Windows"));
    }
}
