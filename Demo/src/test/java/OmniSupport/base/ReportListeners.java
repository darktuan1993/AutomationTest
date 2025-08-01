package OmniSupport.base;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportListeners implements ITestListener {
    private WebDriver driver;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null
                ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null
                ? result.getMethod().getDescription()
                : getTestName(result);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onStart(ITestContext context) {
        // Lấy WebDriver nếu cần dùng sau
//        BaseSetup base = new BaseSetup();
//        driver = base.getDriver();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshotPNG(driver); // Đính kèm screenshot vào Allure
        saveTextLog(getTestName(result) + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        saveTextLog(getTestName(result) + " was skipped.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        saveTextLog(getTestName(result) + " passed successfully.");
    }

    @Override
    public void onTestStart(ITestResult result) {}
    @Override
    public void onFinish(ITestContext context) {}
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}