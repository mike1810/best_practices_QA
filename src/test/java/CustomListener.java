import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class CustomListener implements ITestListener{

    static final Logger LOGGER = Logger.getLogger(Page.class);

    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] saveAllureScreenshot(ITestResult var1) {
        Object currentClass = var1.getInstance();
        WebDriver webDriver = ((BaseTest) currentClass).getDriver();
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    int a = 1;
    public void onTestStart(ITestResult var1){
        //takeScreenShot(var1);
        LOGGER.info("Method "+ a +")'" + var1.getName() + "' started");
        var1.getTestClass();
    }

    public void onTestSuccess(ITestResult var1){

        LOGGER.info("Method "+ a +")'" + var1.getName() + "' successful");
        a++;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] saveAllureScreenshot(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    public void onTestFailure(ITestResult var1){

        try {
            takeScreenShot(var1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        a++;

        LOGGER.info("Method "+ a +")'" + var1.getName() + "' failed");
        Object currentClass = var1.getInstance();
        ((BaseTest) currentClass).getDriver().quit();
    }

    public void onTestSkipped(ITestResult var1){
        //takeScreenShot(var1);
        System.out.println("Method "+ a +")'" + var1.getName() + "' skipped");
        a++;

        Object currentClass = var1.getInstance();
        ((BaseTest) currentClass).getDriver().quit();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult var1){
        //takeScreenShot(var1);
        System.out.println("onTestFailedButWithinSuccessPercentage");
    }

    public void onStart(ITestContext var1){
        System.out.println("Test '" + var1.getName() + "' started");
    }

    public void onFinish(ITestContext var1){
        System.out.println("Test '" + var1.getName() + "' finished");
    }


    private void takeScreenShot(ITestResult var1) throws IOException {
        Object currentClass = var1.getInstance();
        WebDriver webDriver = ((BaseTest) currentClass).getDriver();
        File screenshot = ((TakesScreenshot) webDriver)
                .getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("target/allure-results/screenshots/" + var1.getTestClass()+ ".jpg"));
    }

}
