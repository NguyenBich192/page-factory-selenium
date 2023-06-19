package Util;

import Service.AmazonPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class baseWeb {
    static WebDriver webDriver;
    AmazonPage amazonPage = new AmazonPage();

    public baseWeb() {
        PageFactory.initElements(webDriver, amazonPage);
    }

    public void getURL(String url) {
        try {
            System.out.println("--------start get url----------");
            webDriver.get(url);
            webDriver.manage().window().maximize();
            System.out.println("--------get url success----------");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("--------get url failed----------");
        }
    }

    public void navigate(String url) {
        try {
            System.out.println("--------start navigate----------");
            webDriver.navigate().to(url);
            webDriver.manage().window().maximize();
            System.out.println("--------navigate success----------");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("--------navigate failed----------");
        }
    }

    public void open_Browser(String browserName) {
        try {
            if (browserName.equalsIgnoreCase("Firefox")) {
                System.out.println("--------open firefox----------");
                FirefoxDriver driver = new FirefoxDriver();
                System.out.println("--------open firefox success----------");
            } else if (browserName.equalsIgnoreCase("chrome")) {
                System.out.println("--------open chrome----------");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                webDriver = new ChromeDriver(options);
                System.out.println("--------open chrome success----------");
            }
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
            System.out.println("--------open browser failed----------");
        }
    }

    public By locatorValue(String locatorTpye, String value) {
        System.out.println("--------get locator----------");
        By by;
        switch (locatorTpye) {
            case "id":
                by = By.id(value);
                break;
            case "classname":
                by = By.className(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "css":
                by = By.cssSelector(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            case "tagname":
                by = By.tagName(value);
                break;
            default:
                System.out.println("--------get locator failed----------");
                by = null;
                break;
        }
        return by;
    }

    public void send_key(String locatorType, String value, String text) {
        try {
            System.out.println("--------start send key----------");
            By locator;
            locator = locatorValue(locatorType, value);
            WebElement element = webDriver.findElement(locator);
            element.sendKeys(text);
            System.out.println("--------send key success----------");
        } catch (NoSuchElementException e) {
            System.err.format("No Element Found to enter text" + e);
        }
    }

    //method for click action – Keyword is ‘click’
    public void click_btn(String locatorType, String value) {
        try {
            webDriver.findElement(locatorValue(locatorType, value)).click();
            System.out.println("---------click btn success----------");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("---------click btn failed----------");
        }

    }

    public WebElement findElement(String locatorType, String value) {
        WebElement find = webDriver.findElement(locatorValue(locatorType, value));
        return find;
    }

    public List findElements(String locatorType, String value) {
        List<WebElement> listOfElements = webDriver.findElements(locatorValue(locatorType, value));
        return listOfElements;
    }

    public void close_Browser() {
        try {
            System.out.println("----------quit start---------");
            webDriver.quit();
            System.out.println("----------quit success---------");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----------quit failed---------");
        }
    }

    public void waitForElement(String locatorType, String value, long time1, long time2) {
        Wait<WebDriver> wait = new FluentWait<>(webDriver)
                // Chờ 5 giây để một phần tử hiện diện trên trang
                .withTimeout(Duration.ofSeconds(time1))
                // Và sẽ thực hiện lặp lại mỗi 30 giây nếu chưa tìm thấy phần tử đó
                .pollingEvery(Duration.ofMillis(time2))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.elementToBeClickable(locatorValue(locatorType, value)));
    }

    public boolean isElementPresent(String id, String locatorType, String value) {
        if (webDriver.findElements(locatorValue(locatorType, value)).size() > 0) {
            System.out.println("present");
            return true;
        } else {
            System.out.println("absent");
            return false;
        }
    }

    public void reload() {
        webDriver.navigate().refresh();
    }
}
