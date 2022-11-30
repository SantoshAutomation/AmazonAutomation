package in.amazon.pages;

import in.amazon.base.ITBaseClass;
import in.amazon.locators.AmazonPageLocator;
import in.amazon.utils.SeleniumHelperClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Santosh R: Webelements declaration, intitialization using PageFactory
 * and actions on them represented in ProductDetailsPage
 */

public class ProductDetailsPage extends ITBaseClass {

    SeleniumHelperClass seleniumHelperClass = new SeleniumHelperClass();

    @FindBy(xpath = AmazonPageLocator.ProductDetailsClass.aboutPrice_ByXpath)
    private WebElement aboutProduct;

    public ProductDetailsPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public String aboutProduct() {
        seleniumHelperClass.switchWindow();
        return seleniumHelperClass.getText(aboutProduct);
    }

}
