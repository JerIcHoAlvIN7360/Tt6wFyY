// 代码生成时间: 2025-09-02 00:12:30
import io.quarkus.logging.Log;
import javax.enterprise.context.ApplicationScoped;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ErrorLogCollector is responsible for collecting and logging errors in the application.
 */
@ApplicationScoped
public class ErrorLogCollector {

    private static final Logger LOGGER = Logger.getLogger(ErrorLogCollector.class.getName());
    private static final String LOG_FILE = "error_log.txt"; // Default log file name

    /**
     * Logs an error with a message and stack trace.
     *
     * @param errorMessage the error message to log
     * @param throwable the exception to log
     */
    public void logError(String errorMessage, Throwable throwable) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());

            pw.println("---------------------");
            pw.println("Error Time: " + currentDateTime);
            pw.println("Error Message: " + errorMessage);
            pw.println("Exception Stack Trace: ");

            throwable.printStackTrace(pw);

            pw.close();

            // Also log to the console
            LOGGER.log(Level.SEVERE, errorMessage, throwable);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to write to log file", e);
        }
    }

    /**
     * Logs an error without an exception.
     *
     * @param errorMessage the error message to log
     */
    public void logError(String errorMessage) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());

            pw.println("---------------------");
            pw.println("Error Time: " + currentDateTime);
            pw.println("Error Message: " + errorMessage);

            pw.close();

            // Also log to the console
            LOGGER.log(Level.SEVERE, errorMessage);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to write to log file", e);
        }
    }
}
