package pages;


import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utils.Log4Test;

public class MainPage extends TestBase{



    protected By searchString = By.className("header-search-input-text");

    public boolean isOpened()
    {
        return webDriver.getCurrentUrl().equals(this.getURL());
    }

    public void searchProduct(String productName)
    {
        Log4Test.info("Search product " + productName);
        webDriver.findElement(searchString).clear();
        webDriver.findElement(searchString).sendKeys(productName);
        webDriver.findElement(searchString).sendKeys(Keys.RETURN);
    }

    public String getCategoryPageLink(String category) {
        String link = webDriver.findElement(By.xpath("//a[.='" + category + "']")).getAttribute("href");
        Log4Test.info("Found URL for category '" + category + " :" + link);
        return link;
    }

}
