package enums;

import org.openqa.selenium.By;

public enum LoginEnums {

    USERNAME_FIELD("//input[@name='username']"),
    PASSWORD_FILED("//input[@name='password']"),
    SUBMIT("//button[@type='submit']"),
    HOMEPAGEICON("//img[@alt='client brand banner']");

    private final String xpath;

    LoginEnums(String xpath) {
        this.xpath = xpath;
    }

    public String getXpath() {
        return xpath;
    }

    public String getXpath(String... values) {
        return String.format(xpath, (Object[]) values);
    }

    public By getLocator() {
        return By.xpath(xpath);
    }

    public By getLocator(String... values) {
        return By.xpath(String.format(xpath, (Object[]) values));
    }
}
