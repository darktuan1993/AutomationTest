package OmniSupport.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseSetup {
    private WebDriver driver;

    static String driverPath = "resources\\drivers\\";

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(String browserType, String appURL) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver(appURL);
                break;
            case "edge":
                driver = initEdgeDriver(appURL);
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver(appURL);
        }
    }

    private WebDriver initEdgeDriver(String appURL) {
        System.out.println("Launching Edge browser...");
//      System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver.exe");\
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }

    private WebDriver initChromeDriver(String appURL) {
        System.out.println("Launching Chrome browser...");
//      System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }

    @Parameters({ "browserType", "appURL" })
    @BeforeClass
    public void initializeTestBaseSetup(String browserType, String appURL) {
        try {
            setDriver(browserType, appURL);
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
