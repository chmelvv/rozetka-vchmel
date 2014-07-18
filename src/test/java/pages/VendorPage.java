package pages;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log4Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

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
        List<WebElement> allProducts =  webDriver.findElements(By.cssSelector(".gtile-i-title>a"));
        for (WebElement prod: allProducts) {
            if (prod.getText().contains(product)) {
                Log4Test.info("Page contains product: " + product);
                isContain = true;
            }
        }
        return isContain;
    }

    public void addToCompare(String product){
        webDriver.findElement(
                By.xpath("//a[contains(text(),'" + product + "')]/../following-sibling::div/div/label/input")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 60);
        //wait until product added to Compare block
        wait.until(presenceOfElementLocated(By.xpath("//div[contains(concat(' ', @class, ' '), ' block ')]/ul/li/a[contains(text(),'" + product + "')]")));
        Log4Test.info("Product " + product + " added to comparison");
    }

    public boolean isInComparison(String product) {
       Boolean isInComparison = false;
       //WebElement comparisonBlock = webDriver.findElement(By.xpath("//div[contains(concat(' ', @class, ' '), ' block ')]"));
       List<WebElement> productsInComparison = webDriver.findElements(By.cssSelector(".block>ul>li>a"));
       for( WebElement p: productsInComparison){
           if (p.getText().contains(product)) {
               isInComparison = true;
               break;
           }
       }
        return isInComparison;
    }
}
