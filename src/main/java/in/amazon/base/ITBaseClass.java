package in.amazon.base;


import in.amazon.utils.ExtentManager;
import in.amazon.utils.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Santosh R: BaseClass is used to load the config file and Initialize
 * WebDriver
 */

public class ITBaseClass {

    // Declare ThreadLocal Driver
    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public static Properties prop;

    @BeforeSuite(groups = {"Smoke", "Regression", "Sanity"})
    public void loadConfig() {
        {
            ExtentManager.setExtent();
            DOMConfigurator.configure("log4j.xml");

            try {
                prop = new Properties();
                FileInputStream appConfig = new FileInputStream(
                        System.getProperty("user.dir") + "\\src\\main\\resources\\configuration\\appConfig.properties");
                FileInputStream appString = new FileInputStream(
                        System.getProperty("user.dir") + "\\src\\main\\resources\\localString\\appString.properties");
                prop.load(appConfig);
                prop.load(appString);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void launchApp(String browser, String url) {

        if (browser.contains(getProperty("chromeBrowser"))) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
            Log.info("Execution started on " + browser + " browser.......");

        } else if (browser.contains(getProperty("firefoxBrowser"))) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
            Log.info("Execution started on " + browser + " browser.......");

        } else if (browser.contains(getProperty("InternetExploreBrowser"))) {
            WebDriverManager.iedriver().setup();
            driver.set(new InternetExplorerDriver());
            Log.info("---Execution started on " + browser + " browser.......");

        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            try {
                Log.info("Execution started on remote webdriver.......");
                driver.set(new RemoteWebDriver(new URL(getProperty("remoteUrl")), capabilities));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();

        getDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(getProperty("pageLoadTimeout")), TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(Long.parseLong(getProperty("implicitWait")), TimeUnit.SECONDS);
        getDriver().get(url);

    }

    public static String getProperty(String property) {
        return prop.getProperty(property);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static String getCurrentTime() {
        String currentDate = new SimpleDateFormat("yyyy-mm-dd-hhmmss").format(new Date());
        return currentDate;
    }

    @AfterSuite(groups = {"Smoke", "Regression", "Sanity"})
    public void afterSuite() {
        ExtentManager.endReport();
    }
}
