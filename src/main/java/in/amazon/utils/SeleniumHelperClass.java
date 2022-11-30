package in.amazon.utils;

import in.amazon.base.ITBaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Santosh R: SeleniumHelperClass class provides different types of methods and utilities
 */

public class SeleniumHelperClass extends ITBaseClass {

    public void click(WebElement element) {
        if (isEnabled(element)) {
            element.click();
            Log.info("Clicked on : " + element);
        }
    }

    public void JSClick(WebElement element) {
        if (isEnabled(element)) {
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", element);
            Log.info("Clicked by javascript on : " + element);
        }
    }

    public void scrollByVisibilityOfElement(WebElement element) {
        if (isDisplayed(element)) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].scrollIntoView();", element);
            Log.info("Scolling page to find : " + element);
        }

    }

    public List<WebElement> findElements(By byLocator) {
        return getDriver().findElements(byLocator);
    }

    public void click(WebDriver driver, WebElement ele) {

        Actions act = new Actions(driver);
        act.moveToElement(ele).click().build().perform();

    }

    public boolean findElement(WebElement element) {
        boolean flag = false;
        try {
            element.isDisplayed();
            flag = true;
        } catch (Exception e) {
            flag = false;
        } finally {
            if (flag) {
                Log.info("Successfully Found element at :" + element);

            } else {
                Log.info("Unable to locate element at :" + element);
            }
        }
        return flag;
    }

    public boolean isDisplayed(WebElement element) {
        boolean flag=false;
        flag = findElement(element);
        if (flag) {
            flag = element.isDisplayed();
            if (flag) {
                Log.info("The element is Displayed");
            } else {
                Log.info("The element is not Displayed");
            }
        } else {
            Log.info("Not displayed ");
        }
        return flag;
    }

    public boolean isSelected(WebElement element) {
        boolean flag = false;
        flag = findElement(element);
        if (flag) {
            flag = element.isSelected();
            if (flag) {
                Log.info("The element is Selected");
            } else {
                Log.info("The element is not Selected");
            }
        } else {
            Log.info("Not selected ");
        }
        return flag;
    }

    public boolean isEnabled(WebElement element) {
        boolean flag = false;
        flag = findElement(element);
        if (flag) {
            flag = element.isEnabled();
            if (flag) {
                Log.info("The element is Enabled");
            } else {
                Log.info("The element is not Enabled");
            }
        } else {
            Log.info("Not Enabled ");
        }
        return flag;
    }


    public String selectItem(List<WebElement> elements, String item) {
        String value = "";
        for (WebElement element : elements) {
            value = getText(element);
            if (value.contains(item)) {
                scrollByVisibilityOfElement(element);
                click(element);
                break;
            }
        }
        Log.info("Selected item is : " + item);
        return value;
    }

    public List<String> getItemValue(List<WebElement> elements) {
        List<String> priceList = new ArrayList<>();
        for (WebElement element : elements) {
            priceList.add(getText(element));
        }
        return priceList;
    }

    public void switchWindow() {
        Log.info("Switching window...");
        String parentWindow = getDriver().getWindowHandle();
        Set<String> allWindows = getDriver().getWindowHandles();
        Iterator<String> iterator = allWindows.iterator();

        while (iterator.hasNext()) {
            String childWindow = iterator.next();
            if (!parentWindow.equalsIgnoreCase(childWindow)) {
                getDriver().switchTo().window(childWindow);
            }
        }
    }

    public String getText(WebElement element) {
        String elementValue = "";
        if (isDisplayed(element)) {
            elementValue = element.getText();
        }
        return elementValue;

    }

    public String screenShot(String filename) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + getCurrentTime() + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        // This new path for jenkins
        String newImageString = "http://localhost:8082/job/AmazonAutomation/ws/AmazonAutomation/ScreenShots/" + filename + "_"
                + getCurrentTime() + ".png";
        return newImageString;
    }


}
