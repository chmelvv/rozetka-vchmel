package selenium_cookbook.controlling_test_flow.popup_windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Walker
 * Date: 8/12/13
 * Time: 12:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class PopupByContext {
    WebDriver driver = new FirefoxDriver();

    @BeforeMethod
    public void startUp() {

        driver.get("http://www.quackit.com/html/codes/html_popup_window_code.cfm");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }

    @Test
    public void testWindowPopupUsingTitle() {

        String parentWindowId = driver.getWindowHandle();

        WebElement popupUrl = driver.findElement(By.linkText("Open a popup window"));

        popupUrl.click();

        Set<String> allWindowsId = driver.getWindowHandles();//get all opentabs/windows

        if (!allWindowsId.isEmpty()) {

            for (String WindowId : allWindowsId) {
                driver.switchTo().window(WindowId);

                if (driver.getPageSource().contains("Heading 1")) {
                    driver.close();
                }
            }

            assertTrue(driver.switchTo().window(parentWindowId).getTitle().equals("HTML Popup Window Code"));


        }

    }
}


