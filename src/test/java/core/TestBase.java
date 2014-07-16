package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.Log4Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected static WebDriver webDriver;
    protected static WebDriverWait wait;
    WebElement headerH1;
    private String URL;

    public String getURL(){
        return this.URL;
    }
    public void open(String URL)
    {
        webDriver.get(URL);
        this.URL = URL;
        Log4Test.info("Open WebUrl " + URL);
    }

    @BeforeSuite
    public static void setUp() throws IOException

    {
           // cvv TODO
        // webDriver = WebDriverFactory.getWebDriver(TestData.BROWSER_NAME);
        webDriver = new FirefoxDriver();
        wait = new WebDriverWait(webDriver, 30);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
    }

    public String getHeader(){
        headerH1 = webDriver.findElement(By.tagName("h1"));
        return headerH1.getText();
    }

    @AfterSuite
    public void tearDown()
    {
        webDriver.quit();
    }

}


