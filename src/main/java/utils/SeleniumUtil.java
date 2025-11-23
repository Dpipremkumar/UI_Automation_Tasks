package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.NoSuchElementException;

public class SeleniumUtil {

    protected WebDriver driver;

    // constructor to inject WebDriver from BaseTest
    public SeleniumUtil(WebDriver driver) {
        this.driver = driver;
    }

    // ========== WAIT METHODS ==========

    public WebElement waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementPresence(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean waitForElementToDisappear(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForPageToLoad(int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public WebElement fluentWait(By locator, int timeoutInSeconds, int pollingInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(pollingInSeconds))
                .ignoring(NoSuchElementException.class);

        return wait.until(drv -> drv.findElement(locator));
    }

    // ========== CLICK METHODS ==========

    public void safeClick(By locator, int timeoutInSeconds) {
        try {
            WebElement element = waitForElementToBeClickable(locator, timeoutInSeconds);
            element.click();
        } catch (StaleElementReferenceException e) {
            WebElement element = waitForElementToBeClickable(locator, timeoutInSeconds);
            element.click();
        }
    }

    public void jsClick(By locator, int timeoutInSeconds) {
        WebElement element = waitForElementToBeVisible(locator, timeoutInSeconds);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void actionsClick(By locator, int timeoutInSeconds) {
        WebElement element = waitForElementToBeVisible(locator, timeoutInSeconds);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void doubleClick(By locator, int timeoutInSeconds) {
        WebElement element = waitForElementToBeVisible(locator, timeoutInSeconds);
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    // ========== SEND KEYS METHODS ==========

    public void safeSendKeys(By locator, String text, int timeoutInSeconds) {
        WebElement element = waitForElementToBeVisible(locator, timeoutInSeconds);
        element.clear();
        element.sendKeys(text);
    }

    public void actionsSendKeys(By locator, String text, int timeoutInSeconds) {
        WebElement element = waitForElementToBeVisible(locator, timeoutInSeconds);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().sendKeys(text).perform();
    }

    public void jsSendKeys(By locator, String text, int timeoutInSeconds) {
        WebElement element = waitForElementToBeVisible(locator, timeoutInSeconds);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + text + "';", element);
    }
    public boolean isDisplayed(By locator){
        WebElement element = driver.findElement(locator);
        return element.isDisplayed();
    }
    public void enterText(By locator, String value){
        WebElement element = driver.findElement(locator);
        element.sendKeys(value);
    }
    public void clickElement(By locator, String value){
        WebElement element = driver.findElement(locator);
        element.click();
    }
    // ========== DROPDOWN, ALERT, FRAME, WINDOW, SCROLL, VERIFY, TABLE, FILE, KEYS, BROWSER ==========
    // (keep your existing implementations here, only change the driver field & constructor as above)
    // I’m not re-pasting everything to save space, but logic is same – just using the new `driver`.
}
