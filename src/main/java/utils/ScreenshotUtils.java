package utils;

import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) BaseTest.getDriver();
        File src = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String folderPath = "screenshots";
        String filePath = folderPath + File.separator + testName + "_" + timestamp + ".png";

        try {
            FileUtils.createParentDirectories(new File(filePath));
            FileUtils.copyFile(src, new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }

        LogHelper.getLogger().info("Screenshot captured: " + filePath);
        return filePath;
    }
}
