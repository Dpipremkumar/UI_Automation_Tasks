package pages;

import enums.LoginEnums;
import org.openqa.selenium.WebDriver;
import utils.LogHelper;
import utils.SeleniumUtil;
import utils.TestDataProvider;

import java.time.Duration;

public class LoginPage extends SeleniumUtil {

    public LoginPage(WebDriver driver) {
        super(driver);  // pass driver to SeleniumUtil
    }

    public boolean login(String username, String password) {
        int timeout = TestDataProvider.getInt("wait");
        LogHelper.getLogger().info("Entering login details...");
        driver.manage().timeouts().implicitlyWait (Duration.ofSeconds(10));
        safeSendKeys(LoginEnums.USERNAME_FIELD.getLocator(), username, timeout);
        safeSendKeys(LoginEnums.PASSWORD_FILED.getLocator(), password, timeout);
        safeClick(LoginEnums.SUBMIT.getLocator(), timeout);
        driver.manage().timeouts().implicitlyWait (Duration.ofSeconds(10));
        boolean isDispaly = isDisplayed(LoginEnums.HOMEPAGEICON.getLocator());
        LogHelper.getLogger().info("The User Successfully Login");
        return isDispaly;
    }
}
