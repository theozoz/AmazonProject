package base;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Base {

    public static WebDriver driver;
    String baseUrl = "https://www.amazon.com.tr/";

    @BeforeScenario
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "env/chrome/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        System.out.println("Drives started ");
    }

    @AfterScenario
    public void afterTestMethod() {
        driver.quit();
        System.out.println("Diver  stop:)");
    }
}
