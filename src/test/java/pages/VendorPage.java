package pages;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Log4Test;

import java.util.List;

/**
 * Created by Viktor_Chmel on 16.07.2014.
 */
public class VendorPage extends TestBase {

     public void sortBy(String criterion){
        //open drop-down list
        webDriver.findElement(By.cssSelector(".xhr.lightblue.sprite.dropdown>i")).click();
        //click on needed list element
        webDriver.findElement(By.xpath("//a[contains(text(),'" + criterion + "')]")).click();
        Log4Test.info("Product list sorted by " + criterion);
    }

    public boolean isContain(String product) {
        Boolean isContain = false;
        if (webDriver.getPageSource().contains(product)) {
            Log4Test.info("Page contains product: " + product);
            isContain = true;
        }
        return isContain;
    }

    public void addToCompare(String product){
        webDriver.findElement(
                By.xpath("//a[contains(text(),'" + product + "')]/../following-sibling::div/div/label/input")).click();
        Log4Test.info("Product " + product + " added to comparison");
    }

    public boolean isInComparison(String product) {
       Boolean isInComparison = false;
       List<WebElement> productLinks = webDriver.findElements(By.cssSelector(".block>ul>li>a"));

         Log4Test.info("Elements in list: " + Integer.toString(productLinks.size()));

       for (WebElement link: productLinks){
        if (link.getText().contains(product)) {
            Log4Test.info("Product " + product + "is in comparison list");
            isInComparison = true;
            break;
        }
       }

       return isInComparison;
    }
}
