package Service;

import Model.Amazon;
import Model.Lazada;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LazadaPage{

    @FindBy(xpath = "//input[@id='q']")
    WebElement search;
    @FindBy(xpath = "//button[normalize-space()='SEARCH']")
    WebElement btn_search;
    @FindBy(className = "RfADt")
    List<WebElement> nameValue;
    @FindBy(className = "ooOxS")
    List<WebElement> priceValue;
    @FindBy(xpath = "//div[@class='Bm3ON']/div/div/div[2]/div[2]/a")
    List<WebElement> hefValue;
    @FindBy(className = "Bm3ON")
    List<WebElement> aphone;
    static Lazada lazada;
    static List<Lazada> listLazada = new ArrayList<>();

    static List<Amazon> listAmazon = new ArrayList<>();
    AmazonPage amazonPage;

    public void searchIphone() {
        search.sendKeys("Iphone 11");
        btn_search.click();
    }

    public void getAllProduct() {
        for (int i = 0; i < aphone.size(); i++) {
            lazada = new Lazada();
            lazada.setName(nameValue.get(i).getText());
            lazada.setPrice(priceValue.get(i).getText());
            lazada.setLink(hefValue.get(i).getAttribute("href"));
            listLazada.add(lazada);
        }
        System.out.println(listLazada);
    }

    public void combineList() {
        amazonPage = new AmazonPage();
        listAmazon = amazonPage.listPhone();

        System.out.println("listAmazon" + listAmazon + "");

        ArrayList<Amazon> merge = new ArrayList<>();
        List lstLzd = Arrays.asList(listLazada);
        List lstAmz = Arrays.asList(listAmazon);

        // merging two list using core Java
        List merged = new ArrayList(lstLzd);
        merged.addAll(lstAmz);

        System.out.println("Merged List : " + merged);

        Path output = Paths.get("C:\\Users\\Admin\\Downloads/output.txt");
        try {
            Files.write(output, merged.toString().getBytes());
            System.out.println(output.toFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
