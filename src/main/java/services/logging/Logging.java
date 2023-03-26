package services.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging implements ILogging {

    private Logger _logger;

    public Logging() {
        _logger = LoggerFactory.getLogger("app***");
        _logger.info("Logging initialised");
    }

    public void error (String text) {
        _logger.error(text);
    }

    public void warn (String text) {
        _logger.warn(text);
    }

    public void info (String text) {
        _logger.info(text);
    }

    public void debug (String text) {
        _logger.debug(text);
    }
}
