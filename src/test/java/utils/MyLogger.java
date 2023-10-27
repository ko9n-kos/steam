package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {
    public static Logger logger = LogManager.getLogger();

    public static void info(String message) {
        logger.info(message);
    }
}