package pages;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Log4Test;

/**
 * Created by Viktor_Chmel on 10.07.2014.
 */
public class CategoryPage extends TestBase {
    public String getActiveTabName(){
         //check that Notebook tab is active
    //        <div class="m-main-i active">
    //           <a class="m-main-title" onclick="false" name="active-elem" href="http://rozetka.com.ua/computers-notebooks/c80253/">
    //               <span class="m-main-title-text">Ноутбуки, планшеты и компьютеры</span>
        // We find span element by text, that find its parent of parent by "/../.."
        WebElement divTab = webDriver.findElement(By.cssSelector(".m-main-i.active>a>span"));
        Log4Test.info("Active Tab text: " + divTab.getText());
        return divTab.getText();
    }
}
