package pages;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Log4Test;

/**
 * Created by Viktor_Chmel on 10.07.2014.
 */
public class CategoryPage extends TestBase {

    WebElement headerH1;
    String URL;

    public void setURL(String URL){
        this.URL = URL;
        webDriver.get(URL);
        Log4Test.info("Open WebUrl " + URL);
    }

    public String getURL(){ return this.URL; }

    public String getHeader(){
        headerH1 = webDriver.findElement(By.tagName("h1"));
        return headerH1.getText();
    }

}
