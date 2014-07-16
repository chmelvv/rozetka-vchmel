package ui_tests;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.MainPage;
import pages.VendorPage;
import utils.Log4Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class TestPage extends TestBase {

    MainPage mainPage = new MainPage();
    CategoryPage categoryPage = new CategoryPage();
    VendorPage vendorPage = new VendorPage();

    @Test
    public void setUpPreconditions() {
        mainPage.open("http://rozetka.com.ua/");
        assertTrue(mainPage.isOpened());
    }

    @Test(dependsOnMethods = {"setUpPreconditions"})
    public void checkNotebookPage(){
        String category = "Ноутбуки";
        categoryPage.open(mainPage.getCategoryPageLink(category));

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
//
//        <h4 class="m-cat-l-i-title"> Производители </h4>
//        <ul class="m-cat-subl">
//            <li class="m-cat-subl-i">
//                <a class="m-cat-subl-i-link" href="http://rozetka.com.ua/notebooks/acer/c80004/v017/">Acer</a>
//            </li>
//            <li class="m-cat-subl-i">
//                <a class="m-cat-subl-i-link" href="http://rozetka.com.ua/notebooks/apple/c80004/v069/">Apple</a>
//            </li>
//            <li class="m-cat-subl-i">
//                 <a class="m-cat-subl-i-link" href="http://rozetka.com.ua/notebooks/asus/c80004/v004/">Asus</a>
//            </li>
//            <li class="m-cat-subl-i">
//                 <a class="m-cat-subl-i-link" href="http://rozetka.com.ua/notebooks/dell/c80004/v007/">Dell</a>
//            </li>
//            <li class="m-cat-subl-i">
//                 <a class="m-cat-subl-i-link" href="http://rozetka.com.ua/notebooks/fujitsu/c80004/v030/">Fujitsu</a>
//            </li>
//            <li class="m-cat-subl-i">
//                  <a class="m-cat-subl-i-link" href="http://rozetka.com.ua/notebooks/hp-hewlett-packard/c80004/v005/">HP (Hewlett Packard)</a>
//            </li>
//            <li class="m-cat-subl-i">
//                 <a class="m-cat-subl-i-link" href="http://rozetka.com.ua/notebooks/lenovo/c80004/v120/">Lenovo</a>
//            </li>
//            <li class="m-cat-subl-i">
//                 <a class="m-cat-subl-i-link" href="http://rozetka.com.ua/notebooks/sony/c80004/v039/">Sony</a>
//            </li>
//        </ul>
        //Find h4 with text Производители/than next sibling ul element/choose all its children
        List<WebElement> listOfManufactures = webDriver.findElements(
                By.xpath("//h4[contains(text(), '" + filterHeader + "')]/following-sibling::ul/li/a"));
       //Check is not null and its size
        Assert.assertNotNull(listOfManufactures);
        Log4Test.info("List of manufactures is empty? " + listOfManufactures.isEmpty());
        Assert.assertEquals(listOfManufactures.size(), filtersAmount);
        Log4Test.info("Manufactures list size: " + listOfManufactures.size());
        Log4Test.info("--------------------------");

        //Find Apple name and click on it
        for (WebElement vendorPageLink : listOfManufactures){
           if (vendorPageLink.getText().equals(manufactureApple)) {
               vendorPage.open(vendorPageLink.getAttribute("href"));
                break;
           }
       }
       Log4Test.info("--------------------------");
    }

    @Test (dependsOnMethods = {"check8Manufactures"})
    public void checkVendorPage(){
        String pageTitle = "Ноутбуки Apple";
        Assert.assertEquals(vendorPage.getHeader(), pageTitle);
    }

    @Test (dependsOnMethods = {"checkVendorPage"})
    public void checkAndAddForCompare() {
      //sort page as needed
      String sortBy = "от дорогих к дешевым";
      vendorPage.sortBy(sortBy);


        String model2 = "Apple MacBook Pro Retina 15\" (ME294UA/A)";
        Assert.assertTrue(vendorPage.isContain(model2));
        //add needed model to comparison
        vendorPage.addToCompare(model2);
        //check is it added
        Assert.assertTrue(vendorPage.isInComparison(model2));

      //check if needed model exists on page
      String model1="Apple MacBook Pro Retina 15\" (Z0PU002JE)";
      Assert.assertTrue(vendorPage.isContain(model1));
      //add needed model to comparison
      vendorPage.addToCompare(model1);
      //check is it added
      Assert.assertTrue(vendorPage.isInComparison(model1));


        String model3 = "Apple MacBook Air 13\" (MD761UA/A)";
        Assert.assertTrue(vendorPage.isContain(model3));
        //add needed model to comparison
        vendorPage.addToCompare(model3);
        //check is it added
        Assert.assertTrue(vendorPage.isInComparison(model3));
    }



}
