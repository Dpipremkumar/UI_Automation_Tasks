package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.TestDataProvider;

import java.time.Duration;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setup() {
        if (driver.get() == null) {
            String browser = TestDataProvider.getValue("browser").toLowerCase();

            WebDriver webDriver;

            switch (browser) {
                case "chrome" -> {
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
                }
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                }
                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                }
                default -> throw new RuntimeException("Unsupported browser: " + browser);
            }

            webDriver.manage().window().maximize();
            webDriver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(TestDataProvider.getInt("wait")));

            driver.set(webDriver);
            getDriver().get(TestDataProvider.getValue("baseUrl"));
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
