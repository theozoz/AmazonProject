package methods;


import base.Base;
import com.thoughtworks.gauge.Logger;
import io.restassured.RestAssured;
import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import utils.Utils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Methods {

    FluentWait<WebDriver> wait;
    long waitElementTimeout=10;
    long pollingEveryValue=10;
    WebDriver driver;
    Utils utils;
    Log log=new Log();
    public Methods()
    {
        this.driver=Base.driver;
        wait = setFluentWait(waitElementTimeout);
    }

    public FluentWait<WebDriver> setFluentWait(long timeout){

        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEveryValue))
                .ignoring(NoSuchElementException.class);
        return fluentWait;
    }
    public WebElement findElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }
    public void clickElement(By locator)
    {
        findElement(locator).click();
    }
    public void hoverElement(By locator) {
        System.out.println(driver);
        Actions builder= new Actions(driver);
        builder.moveToElement(findElement(locator));
    }
    public boolean isElementVisible(By locator){
        return findElement(locator).isEnabled();
    }
    public void sendKey(By locator, String key)
    {
        findElement(locator).sendKeys(key);
    }
    public void waitBySeconds(long seconds) {
        utils=new Utils();
        utils.waitBySeconds(seconds);
    }
    public int generateRandom() {
        return utils.generateRandom();
    }

    public void isElementSame(By locator,String element){
        String as=findElement(locator).getText();
        if (as.equals(element))
        {
            log.info("LIST PAGE ELEMENT AND DETAIL PAGE ELEMENT ARE SAME");
        }
        else  {
            log.error("LIST PAGE ELEMENT AND DETAIL PAGE ELEMENT ARE DIFFERENT");
            log.error("list element:-> "+element +" And Detail element:-> "+as);
            Assertions.assertEquals(as,element);
        }
    }
}
