package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.LogHelper;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        LogHelper.getLogger().error("TEST FAILED: " + testName);

        String screenshotPath = ScreenshotUtils.captureScreenshot(testName);
        LogHelper.getLogger().error("Screenshot saved at: " + screenshotPath);
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogHelper.getLogger().info("TEST STARTED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogHelper.getLogger().info("TEST PASSED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        LogHelper.getLogger().info("===== TEST SUITE STARTED: " + context.getName() + " =====");
    }

    @Override
    public void onFinish(ITestContext context) {
        LogHelper.getLogger().info("===== TEST SUITE FINISHED: " + context.getName() + " =====");
    }
}
