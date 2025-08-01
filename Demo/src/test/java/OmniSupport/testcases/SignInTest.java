package OmniSupport.testcases;

import OmniSupport.base.BaseSetup;
import OmniSupport.base.ExcelHelpers;
import OmniSupport.pages.SignInPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;

//@Listeners(ReportListeners.class)
@Epic("OmniSupport")
@Feature("Sign In")
public class SignInTest extends BaseSetup {
    private WebDriver driver;
    private SignInPage signInPage;
    private ExcelHelpers excel;

    @BeforeClass
    public void Setup() {
        driver = getDriver();
        excel = new ExcelHelpers();
    }

    @Test
    public void SignIn() throws Exception {
        excel.setExcelFile("src/main/resources/helpers/Account.xlsx","Sheet1");

        signInPage = new SignInPage(driver);

        signInPage.signIn(excel.getCellData("username",1), excel.getCellData("password",1));

        Thread.sleep(2000);
    }

    @AfterMethod
    // passed = SUCCESS và failed = FAILURE
    public void takeScreenshot(ITestResult result) {
        if (ITestResult.SUCCESS == result.getStatus()) {
            try {
                // Tạo tham chiếu của TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                // Gọi hàm capture screenshot - getScreenshotAs
                File source = ts.getScreenshotAs(OutputType.FILE);
                //Kiểm tra folder tồn tại. Nêu không thì tạo mới folder
                File theDir = new File("./Screenshots/");
                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                // result.getName() lấy tên của test case xong gán cho tên File chụp màn hình
                FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
                System.out.println("Đã chụp màn hình: " + result.getName());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}
