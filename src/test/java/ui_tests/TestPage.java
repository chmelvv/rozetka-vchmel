package ui_tests;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.MainPage;
import utils.Log4Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class TestPage extends TestBase {

    MainPage mainPage = new MainPage();
    CategoryPage categoryPage = new CategoryPage();

    @Test
    public void setUpPreconditions() {
        mainPage.open();
        assertTrue(mainPage.isOpened());
    }

    @Test(dependsOnMethods = {"setUpPreconditions"})
    public void checkNotebookPage(){
        String category = "Ноутбуки";
        categoryPage.setURL(mainPage.getCategoryPageLink(category));
        //check that page called "Notebooks"
        assertTrue(categoryPage.getHeader().equals(category));

        String activeDivTabClassName = "m-main-i active";
        String activeTabName = "Ноутбуки, планшеты и компьютеры";
        //check that Notebook tab is active
//        <div class="m-main-i active">
//           <a class="m-main-title" onclick="false" name="active-elem" href="http://rozetka.com.ua/computers-notebooks/c80253/">
//               <span class="m-main-title-text">Ноутбуки, планшеты и компьютеры</span>
        // We find span element by text, that find its parent of parent by "/../.."
        WebElement divTab = webDriver.findElement(By.xpath("//span[.='" + activeTabName + "']/../.."));
        Log4Test.info("Active Tab text: " + divTab.getText());
        Assert.assertEquals(divTab.getAttribute("class"), activeDivTabClassName);
    }

    @Test (dependsOnMethods = {"checkNotebookPage"})
    public void check8Manufactures(){
        String filterHeader = "Производители";
        int filtersAmount = 8;
        String manufactureApple = "Apple";

//        <h4 class="m-cat-l-i-title"> Производители </h4>
//        <ul class="m-cat-subl">
//            <li class="m-cat-subl-i">
//            <li class="m-cat-subl-i">
//            <li class="m-cat-subl-i">
//            <li class="m-cat-subl-i">
//            <li class="m-cat-subl-i">
//            <li class="m-cat-subl-i">
//            <li class="m-cat-subl-i">
//            <li class="m-cat-subl-i">
//        </ul>
        //Find h4 with text Производители/than next sibling ul element/choose all its children
        List<WebElement> listOfManufactures = webDriver.findElements(
                By.xpath("//h4[contains(text(), '" + filterHeader + "')]/following-sibling::ul/li/a"));
       //Check is not null and its size
        Assert.assertNotNull(listOfManufactures);
        Log4Test.info("List of manufactures is empty? " + listOfManufactures.isEmpty());
        Assert.assertEquals(listOfManufactures.size(), filtersAmount);
        Log4Test.info("Manufactures list size: " + listOfManufactures.size());

        //Find Apple name and click on it
        for (WebElement manufacturePage : listOfManufactures){
           if (manufacturePage.getText().equals(manufactureApple)) {
               manufacturePage.click();
               // Log4Test.info("Click on link: " + manufacture.getAttribute("href"));
                break;
           }
       }



    }

}
