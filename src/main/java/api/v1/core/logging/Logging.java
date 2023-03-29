package api.v1.core.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging implements ILogging {

    private Logger _logger;

    public Logging() {
        _logger = LoggerFactory.getLogger("");
        _logger.info("Logging initialised");
    }

    public void error (String text) {
        _logger.error(finalString(text));
    }

    public void warn (String text) {
        _logger.warn(text);
    }

    public void info (String text) {
        _logger.info(finalString(finalString(text)));
    }

    public void debug (String text) {
        _logger.debug(finalString(text));
    }

    private String finalString(String text) {
        return Thread.currentThread().getStackTrace()[2].getFileName() + ":" + text;
    }
}
