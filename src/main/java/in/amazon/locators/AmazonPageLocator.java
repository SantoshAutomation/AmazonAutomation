package in.amazon.locators;

/**
 * @author Santosh R: Page elements with locators
 *
 */

public class AmazonPageLocator {

    public static class AmazonHomeClass {
        public static final String hamburgerMenu_ById = "nav-hamburger-menu";
        public static final String category_ByXpath = "//div[contains(text(), 'shop by category')]//following::div";
        public static final String product_ByXpath = "//div[contains(text(), 'tv, audio & cameras')]//following::a";

    }

    public static class TelevisionProductClass {
        public static final String brand_ByXpath = "(//span[contains(text(), 'Brands')])[3]//parent::div//following-sibling::ul//descendant::span[@class='a-size-base a-color-base']";
        public static final String featured_ByXpath = "//span[@class='a-button-text a-declarative']";
        public static final String sorting_ByXpath = "//ul[@role='listbox']//descendant::a";
        public static final String price_ByXpath = "//span[@class='a-price-whole']";
    }

    public static class ProductDetailsClass {
        public static final String aboutPrice_ByXpath = "//h1[contains(text(), ' About this item ')]";
    }
}
