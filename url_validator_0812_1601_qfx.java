// 代码生成时间: 2025-08-12 16:01:00
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * URL Validator application using QUARKUS framework.
 */
@QuarkusMain
public class UrlValidator implements QuarkusApplication {

    @Override
    public int run(String... args) throws IOException {
        if (args.length < 1) {
            System.out.println("Please provide a URL to validate.");
            return 1;
        }

        String urlToCheck = args[0];
        URL url = new URL(urlToCheck);
        try {
            URLConnection connection = url.openConnection();

            if (url.getProtocol().equals("https")) {
                HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
                httpsConnection.setRequestMethod("GET");
                int responseCode = httpsConnection.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    System.out.println("The URL is valid and reachable.");
                } else {
                    System.out.println("The URL is invalid or not reachable. Response code: " + responseCode);
                return 1;
                }
            } else {
                System.out.println("Only HTTP and HTTPS protocols are supported.");
                return 1;
            }
        } catch (IOException e) {
            System.out.println("Error while validating the URL: " + e.getMessage());
            return 1;
        }

        return 0;
    }

    /**
     * Entry point for standalone application.
     */
    public static void main(String... args) {
        UrlValidator urlValidator = new UrlValidator();
        int exitCode = urlValidator.run(args);
        System.exit(exitCode);
    }
}