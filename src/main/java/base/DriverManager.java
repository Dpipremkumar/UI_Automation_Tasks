package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.TestDataProvider;

import java.time.Duration;

public class DriverManager {

    // === Singleton instance ===
    private static final DriverManager INSTANCE = new DriverManager();

    // Private constructor -> no one can create object from outside
    private DriverManager() {}

    // Global access point
    public static DriverManager getInstance() {
        return INSTANCE;
    }

    // Thread-safe WebDriver (good for parallel execution)
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Get current thread's driver
    public WebDriver getDriver() {
        return driver.get();
    }

    // Initialize driver if not already created
    public void initDriver() {
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
        }
    }

    public void openBaseUrl() {
        getDriver().get(TestDataProvider.getValue("baseUrl"));
    }

    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
