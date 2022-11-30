package in.amazon.pages;


import in.amazon.base.ITBaseClass;
import in.amazon.locators.AmazonPageLocator;
import in.amazon.utils.Log;
import in.amazon.utils.SeleniumHelperClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Santosh R: Webelements declaration, intitialization using PageFactory
 * and actions on them represented in TelevisionProductPage
 *
 */

public class TelevisionProductPage extends ITBaseClass {

    SeleniumHelperClass seleniumHelperClass = new SeleniumHelperClass();

    @FindBy(xpath = AmazonPageLocator.TelevisionProductClass.brand_ByXpath)
    private List<WebElement> brands;

    @FindBy(xpath = AmazonPageLocator.TelevisionProductClass.featured_ByXpath)
    private WebElement featured;

    @FindBy(xpath = AmazonPageLocator.TelevisionProductClass.sorting_ByXpath)
    private List<WebElement> sort;

    @FindBy(xpath = AmazonPageLocator.TelevisionProductClass.price_ByXpath)
    private List<WebElement> price;

    public TelevisionProductPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void selectBrand(String brandName) {
        seleniumHelperClass.selectItem(brands, brandName);
    }

    public void sortingByFeatured(String sortFeatured) {
        seleniumHelperClass.JSClick(featured);
        seleniumHelperClass.selectItem(sort, sortFeatured);
        Log.info("Sorted by featured : " +sortFeatured);
    }

    public ProductDetailsPage selectSecondHighestPriceItem() {
        List<String> itemPriceList;
        itemPriceList = seleniumHelperClass.getItemValue(price);
        Map<Integer, String> priceMap = new HashMap<>();

        for (String priceValue : itemPriceList) {
            priceMap.put(Integer.parseInt(priceValue.replaceAll(",", "").trim()), priceValue);
        }
        int highestPrice = 0;
        int secondHighestPrice = 0;
        for (Integer price : priceMap.keySet()) {
            if (price > highestPrice) {
                secondHighestPrice = highestPrice;
                highestPrice = price;
            } else if (price > secondHighestPrice) {
                secondHighestPrice = price;
            }
        }
        seleniumHelperClass.selectItem(price, priceMap.get(secondHighestPrice));
        return new ProductDetailsPage();
    }
}
