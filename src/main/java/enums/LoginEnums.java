package enums;

import lombok.Getter;
import org.openqa.selenium.By;
@Getter
public enum Login {



    private final String xpath;

    Login(String xpath) {
        this.xpath = xpath;
    }


    // Method to format xpath with dynamic values
    public String getXpath(String... values) {
        return String.format(xpath, values);
    }

    // Method to get By locator
    public By getLocator() {
        return By.xpath(xpath);
    }

    // Method to get By locator with dynamic values
    public By getLocator(String... values) {
        return By.xpath(String.format(xpath, values));
    }
}
