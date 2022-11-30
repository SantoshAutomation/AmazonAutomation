package in.amazon.ui.productDetails;

import in.amazon.base.ITBaseClass;
import in.amazon.pages.AmazonHomePage;
import in.amazon.pages.ProductDetailsPage;
import in.amazon.pages.TelevisionProductPage;
import in.amazon.utils.Log;
import in.amazon.utils.Strings;
import in.amazon.utils.StringsReader;
import in.amazon.utils.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;

public class GetProductDetailsWithShopByCategory extends ITBaseClass {
    private AmazonHomePage amazonHomePage;
    private TelevisionProductPage televisionProductPage;
    private ProductDetailsPage productDetailsPage;

    @DataProvider
    public Iterator<Object[]> getTestData(){
        ArrayList<Object[]> testData = TestDataUtils.getDataFromExcel();
        return testData.iterator();
    }

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void setup(String browser) {
        Log.info("Application launching" );
        launchApp(browser, getProperty("url"));
    }

    @Test(groups = {"Sanity"}, dataProvider = "getTestData")
    public void shouldGetSecondHighestPriceTVDetails(String category, String product, String brand, String featured) {
        Log.startTestCase("shouldGetTvAppliancesAndElectronicsDetails");
        amazonHomePage = new AmazonHomePage();
        amazonHomePage.clickOnHamburgerMenu();
        amazonHomePage.selectCategory(category);
        televisionProductPage = amazonHomePage.selectProduct(product);

        televisionProductPage.selectBrand(brand);
        televisionProductPage.sortingByFeatured(featured);

        productDetailsPage = televisionProductPage.selectSecondHighestPriceItem();
        Assert.assertEquals(productDetailsPage.aboutProduct(), StringsReader.getMessage(Strings.PRODUCT_DESCRIPTION_TITLE));
        Log.endTestCase("shouldGetTvAppliancesAndElectronicsDetails");

    }

    @AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void tearDown() {
        getDriver().quit();
    }
}
