package services.logging;

public interface ILogging {
    void error (String text);
    void warn (String text);
    void info (String text);
    void debug (String text);
}
