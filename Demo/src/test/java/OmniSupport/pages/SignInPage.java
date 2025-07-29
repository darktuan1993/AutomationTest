package OmniSupport.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class SignInPage {
    private WebDriver driver;

    private WebDriverWait wait;

    public String expectAppName = "Của Linh tất";

    @FindBy(id = "username") private WebElement emailInput;

    @FindBy(id = "password") private WebElement passwordInput;

    @FindBy(id = "kc-login-button") private WebElement signInbutton;

    @FindBy(xpath = "//p[contains(text(),'Của Linh tất')]") private WebElement appName;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, this);
    }

    public void signIn(String username, String password) throws Exception {
        emailInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInbutton.click();

        wait.until(ExpectedConditions.visibilityOf(appName));
        String actualAppName = appName.getText();
        assertEquals(actualAppName, expectAppName);

        appName.click();
        Thread.sleep(2000);
    }
}
