package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {

    private static final Logger logger = LogManager.getLogger("AutomationLogger");

    public static Logger getLogger() {
        return logger;
    }
}
