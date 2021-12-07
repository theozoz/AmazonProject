package steps;

import base.Base;
import com.thoughtworks.gauge.Step;
import methods.Log;
import methods.Methods;
import org.apache.http.util.Asserts;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StepImplementation {


    Methods methods;
    WebDriver driver;
    int count;
    String dataListName,dataDetailName,productDetailPrice,productBasketPrice;

    private final By homeToLogin = By.id("nav-link-accountList-nav-line-1");
    private final By loginEmail = By.id("ap_email");
    private final By loginPassword = By.id("ap_password");
    private final By loginBtn2 = By.id("signInSubmit");
    private final By loginBtn = By.xpath("//div//span[@class=\"a-button a-button-span12 a-button-primary\"]");
    private final By userNameWithLogin = By.xpath("//div[@class=\"nav-line-1-container\"]//span[text()=\"test\u200B\"]");
    private final By searchPlace = By.id("twotabsearchtextbox");
    private final By searchBtn = By.id("nav-search-submit-button");
    private final By detailTitle = By.xpath("//div[@class=\"a-section a-spacing-none\"]//h1//span");
    private final By  addToCartButton= By.id("add-to-cart-button");
    private final By  productPrice= By.xpath("//div//span[@class=\"a-price a-text-price a-size-medium\"]");
    private final By  goToBasket= By.id("nav-cart");
    private final By  closePopup= By.id("attach-close_sideSheet-link");
    private final By  productPriceBasketPage= By.xpath("//p[@class=\"a-spacing-mini\"]");


    public StepImplementation()
    {
        this.driver=Base.driver;
        methods=new Methods();
    }

    @Step("Go to this url <key>")
    public void  getPageUrl(String key)
    {
        driver.navigate().to(key);
    }

    @Step("Go To login page")
    public void goToLoginPage() {
        methods.hoverElement(homeToLogin);
        methods.clickElement(homeToLogin);
    }

    @Step("Login with email <key> ve password <key>")
    public void userLogin(String userMail,String userPassword)
    {
        methods.sendKey(loginEmail,userMail);
        methods.clickElement(loginBtn);
        methods.sendKey(loginPassword,userPassword);
        methods.clickElement(loginBtn2);
        methods.isElementVisible(userNameWithLogin);
    }
    @Step("Search <Key> on home page")
    public void searchData(String key)
    {
        methods.isElementVisible(searchPlace);
        methods.sendKey(searchPlace,key);
        methods.waitBySeconds(1);
        methods.clickElement(searchBtn);
    }
    @Step("<seconds> second wait")
    public void waitBySeconds(long seconds) {
        methods.waitBySeconds(seconds);
    }

    @Step("Select random element")
    public void selectRandomElement()
    {
      count=methods.generateRandom();
       By dataListIndex=By.xpath("(//span[contains(@class,\"text-normal\")])["+count+"]");
       dataListName=methods.findElement(dataListIndex).getText();
       System.out.println(dataListName);
        System.out.println("---Count"+count+"------------Count");
       methods.clickElement(dataListIndex);
       methods.waitBySeconds(1);
    }
    @Step("Control element Title")
    public void areElementSame(){
        methods.isElementSame(detailTitle,dataListName);
    }
    @Step("Check element Price")
    public void checkElementPrice(){
        methods.waitBySeconds(1);
        productDetailPrice=methods.findElement(productPrice).getText();
    }
      @Step("Add to card")
    public void setAddToCard(){
        methods.clickElement(addToCartButton);
        methods.waitBySeconds(2);
    }
  @Step("Go to basket page")
    public void goToBasketPage(){
        getPageUrl("https://www.amazon.com.tr/gp/cart/view.html?ref_=nav_cart");
    }

    @Step("Control basket price product")
    public void checkBasketProductPrice()
    {
        productBasketPrice=methods.findElement(productPriceBasketPage).getText();
        Assertions.assertEquals(productDetailPrice,productBasketPrice.replaceAll(" ",""),"Expected result and actual result are not same");
        methods.waitBySeconds(4);

    }
    
}
