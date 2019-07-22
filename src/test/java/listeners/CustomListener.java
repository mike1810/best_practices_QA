package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener{

    int a = 1;
    public void onTestStart(ITestResult var1){
        System.out.println("Method "+ a +")'" + var1.getName() + "' started");
        var1.getTestClass();
    }

    public void onTestSuccess(ITestResult var1){
        System.out.println("Method "+ a +")'" + var1.getName() + "' successful");
        a++;
    }

    public void onTestFailure(ITestResult var1){
        System.out.println("Method "+ a +")'" + var1.getName() + "' failed");
        a++;
    }

    public void onTestSkipped(ITestResult var1){
        System.out.println("Method "+ a +")'" + var1.getName() + "' skipped");
        a++;
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult var1){
        System.out.println("onTestFailedButWithinSuccessPercentage");
    }

    public void onStart(ITestContext var1){
        System.out.println("Test '" + var1.getName() + "' started");
    }

    public void onFinish(ITestContext var1){
        System.out.println("Test '" + var1.getName() + "' finished");
    }
}
