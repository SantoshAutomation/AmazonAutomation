package in.amazon.pages;

import in.amazon.base.ITBaseClass;
import in.amazon.locators.AmazonPageLocator;
import in.amazon.utils.Log;
import in.amazon.utils.SeleniumHelperClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Santosh R: Webelements declaration, intitialization using PageFactory
 * and actions on them represented in AmazonHomePage
 */

public class AmazonHomePage extends ITBaseClass {

    SeleniumHelperClass seleniumHelperClass = new SeleniumHelperClass();

    @FindBy(id = AmazonPageLocator.AmazonHomeClass.hamburgerMenu_ById)
    private WebElement hamburgerMenu;

    @FindBy(xpath = AmazonPageLocator.AmazonHomeClass.category_ByXpath)
    private List<WebElement> categories;

    @FindBy(xpath = AmazonPageLocator.AmazonHomeClass.product_ByXpath)
    private List<WebElement> products;

    public AmazonHomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void clickOnHamburgerMenu() {
        seleniumHelperClass.click(hamburgerMenu);
        Log.info("Successfully clicked on Hamburger Menu");
    }

    public void selectCategory(String categoryName) {
        seleniumHelperClass.selectItem(categories, categoryName);
        Log.info("Selected category is : " + categoryName);
    }

    public TelevisionProductPage selectProduct(String productName) {
        seleniumHelperClass.selectItem(products, productName);
        Log.info("Selected product is : " + productName);
        return new TelevisionProductPage();
    }

}
