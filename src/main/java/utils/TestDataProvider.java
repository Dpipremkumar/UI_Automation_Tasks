package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import pojo.Login;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataProvider {

    private static Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/testdata/testdata.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load testdata.properties", e);
        }
    }

    public static String getValue(String key) {
        // 1️⃣ Read from VM options (-Dkey=value)
        String sysValue = System.getProperty(key);

        if (sysValue != null && !sysValue.isEmpty()) {
            return sysValue;
        }

        // 2️⃣ Fallback to properties file
        return properties.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(getValue(key));
    }

    @DataProvider(name = "loginuser")
    public static Object[][] getUser() throws IOException {
        String value = properties.getProperty("logindata");
        ObjectMapper objectMapper = new ObjectMapper();
        Login login = objectMapper.readValue(value, Login.class);
        return new Object[][]{{login}};
    }
}
