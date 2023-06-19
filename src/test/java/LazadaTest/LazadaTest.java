package LazadaTest;

import Service.AmazonPage;
import Service.LazadaPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

public class LazadaTest {

    WebDriver driver;
    LazadaPage lazadaPage = new LazadaPage();
    AmazonPage amazonPage = new AmazonPage();

    @Before
    public void beforeTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    public void lazadaTest() {
        driver.navigate().to("https://www.lazada.vn/");
        driver.manage().window().maximize();
        PageFactory.initElements(driver, lazadaPage);
        lazadaPage.searchIphone();
        lazadaPage.getAllProduct();
        lazadaPage.combineList();
    }

    @Test
    public void amazonTest() {
        driver.navigate().to("https://www.amazon.com/");
        driver.manage().window().maximize();
        PageFactory.initElements(driver, amazonPage);
        amazonPage.searchIphone();
        amazonPage.listPhone();
    }

    @After
    public void AfterTest() {
        driver.close();
    }
}
