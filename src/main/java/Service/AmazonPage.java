package Service;

import Model.Amazon;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

    public class AmazonPage {
    static Amazon amazon;
    static List<Amazon> listAmazon = new ArrayList<>();
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    WebElement search;
    @FindBy(xpath = "//input[@id='nav-search-submit-button'][1]")
    WebElement btn_search;
    @FindBy(xpath = "//div[@Class=\"a-column a-span6 a-span-last a-text-right\"]")
    WebElement passWeb;
    @FindBy(xpath = "//div[@class='a-section a-spacing-none puis-padding-right-small s-title-instructions-style']")
    List<WebElement> nameValue;
    @FindBy(xpath = "//div[@class='a-section a-spacing-none a-spacing-top-micro puis-price-instructions-style']/div/a/span/span[2]/span[2]")
    List<WebElement> priceValue;
    @FindBy(xpath = "//div[@class='a-section a-spacing-none puis-padding-right-small s-title-instructions-style']/h2/a")
    List<WebElement> hrefValue;
    @FindBy(xpath = "//div[@data-component-type=\"s-search-result\"]")
    List<WebElement> listPhone;
    @FindBy(xpath = "//div[@class='a-row a-text-center']/img")
    WebElement file;

    public void searchIphone() {
        passWeb.click();
        search.sendKeys("Iphone 11");
        btn_search.click();
    }

    public List<Amazon> listPhone() {
        for (int i = 0; i < listPhone.size(); i++) {
            amazon = new Amazon();
            amazon.setName(nameValue.get(i).getText());
            amazon.setPrice(priceValue.get(i).getText());
            amazon.setLink(hrefValue.get(i).getAttribute("href"));
            listAmazon.add(amazon);
        }
        System.out.println(listAmazon);
        return listAmazon;
    }

}
