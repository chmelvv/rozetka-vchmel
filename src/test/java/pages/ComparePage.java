package pages;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Log4Test;

import java.util.List;

/**
 * Created by Viktor_Chmel on 18.07.2014.
 */
public class ComparePage extends TestBase {
    public boolean isContain(String product){
        Boolean isContain = false;
        List<WebElement> allProducts =  webDriver.findElements(By.cssSelector(".title>a"));
        for (WebElement prod: allProducts) {
            if (prod.getText().contains(product)) {
                Log4Test.info("Comparison page contains product: " + product);
                isContain = true;
            }
        }
        return isContain;
    }
}
