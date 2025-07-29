package OmniSupport.testcases;

import OmniSupport.base.BaseSetup;
import OmniSupport.base.ExcelHelpers;
import OmniSupport.pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        excel.setExcelFile("src/main/resources/Account.xlsx","Sheet1");

        signInPage = new SignInPage(driver);

        signInPage.signIn(excel.getCellData("username",1), excel.getCellData("password",1));

        Thread.sleep(2000);
    }

    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}
