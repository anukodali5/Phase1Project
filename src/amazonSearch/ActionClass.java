package amazonSearch;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.time.Duration;



public class ActionClass {

    public WebDriver driver = null;

    public ActionClass(WebDriver driver)
    {
        this.driver = driver;
    }

    // This Method Can Type on a Text Box
    public void TypeOnATextBox(By element , String whatToType){
        try{
            driver.findElement(element).sendKeys(whatToType);
            Thread.sleep(1000);
        }
        catch(Exception e){e.printStackTrace();
            System.out.println(element+"..... is not present ...");}
    }

    // This Method Can CLick on A HTML Element
    public void ClickAndWait(By element){
        try{
            driver.findElement(element).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            Thread.sleep(5000);
        }
        catch(Exception e){e.printStackTrace();
            System.out.println(element+"..... is not present ...");}
    }

   }



