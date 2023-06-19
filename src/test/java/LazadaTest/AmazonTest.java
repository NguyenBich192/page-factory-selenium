package LazadaTest;

import Service.AmazonPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

public class AmazonTest {

    static WebDriver driver;
    AmazonPage amazonPage = new AmazonPage();


    @Before
    public void beforeTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://www.amazon.com/");
    }

    @Test
    public void test() {
        PageFactory.initElements(driver, amazonPage);
        amazonPage.searchIphone();
        amazonPage.listPhone();
    }

    @After
    public void AfterTest() {
        driver.close();
    }
}
