// 代码生成时间: 2025-09-11 20:45:31
import org.jboss.logging.Logger;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * A simple zip utility class to unzip files.
 */
class UnzipTool {

    // Logger instance for logging
    private static final Logger log = Logger.getLogger(UnzipTool.class.getName());

    /**
     * Unzips the contents of a zip file.
     *
     * @param zipFilePath The path to the zip file.
     * @param destDirectory The destination directory to extract the zip contents.
     * @throws IOException If an I/O error occurs during unzipping.
     */
    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
        // Check if the zip file exists
        File zipFile = new File(zipFilePath);
        if (!zipFile.exists()) {
            throw new IOException("Zip file does not exist: " + zipFilePath);
        }

        // Create the destination directory if it does not exist
        Path destPath = Paths.get(destDirectory);
        if (!Files.exists(destPath)) {
            Files.createDirectories(destPath);
        }

        // Create a new ZipInputStream and read the zip file
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                // Extract the zip entry to the destination directory
                String fileName = zipEntry.getName();
                Path filePath = destPath.resolve(fileName);

                if (zipEntry.isDirectory()) {
                    // Create directory if it is a zip directory entry
                    Files.createDirectories(filePath);
                } else {
                    // Write file if it is a zip file entry
                    try (OutputStream out = new FileOutputStream(filePath.toFile())) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            out.write(buffer, 0, len);
                        }
                    }
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
        }
    }

    /**
     * Main method to run the unzip utility.
     *
     * @param args The command line arguments.
     */
    @QuarkusMain
    public static void main(String... args) {
        if (args.length < 2) {
            log.error("Usage: UnzipTool <zipFilePath> <destDirectory>");
            Quarkus.exit(1);
            return;
        }

        try {
            UnzipTool.unzip(args[0], args[1]);
            log.info("Unzipped successfully");
        } catch (IOException e) {
            log.error("Failed to unzip file", e);
            Quarkus.exit(1);
        }
    }
}
