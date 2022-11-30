package in.amazon.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import in.amazon.base.ITBaseClass;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Santosh R: ExtentManager class is used for Extent Report
 */

public class ExtentManager {

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void setExtent() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/extentReport/" + "AmazonReport_" + ITBaseClass.getCurrentTime() + ".html");
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        try {
            extent.setSystemInfo("HostName", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        extent.setSystemInfo("ProjectName", "AmazonAutomationProject");
        extent.setSystemInfo("Tester", System.getProperty("user.name"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "chrome");

    }

    public static void endReport() {
        extent.flush();
    }
}
