// 代码生成时间: 2025-08-19 09:06:02
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.QuarkusApplicationLifecycle;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import java.util.logging.Logger;

// XSS Protection Service
@ApplicationScoped
public class XssProtectionService {

    @Inject
    QuarkusApplicationLifecycle lifecycle;

    // Logger for logging
    private static final Logger LOGGER = Logger.getLogger(XssProtectionService.class.getName());

    /**
     * Escapes XSS by sanitizing input text
     * @param input The input text to sanitize
*
     * @return Sanitized text with XSS attacks removed
*/
    public String sanitizeInput(String input) {

        if (input == null) {
            // Handle the case where input is null
            LOGGER.warning("Input is null. Returning empty string to prevent XSS.");
            return "";
        }

        try {
            // Use Jsoup to sanitize the input
            // Whitelist allows only safe HTML tags to be present in the input
            return Jsoup.clean(input, Whitelist.basic());
        } catch (Exception e) {
            // Log and handle any unexpected errors during sanitization
            LOGGER.severe("Error occurred during input sanitization: " + e.getMessage());
            lifecycle.exit(1);
            return "";
        }
    }
}
