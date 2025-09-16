// 代码生成时间: 2025-09-16 19:30:23
import io.quarkus.runtime.Quarkus;
    import io.quarkus.runtime.annotations.QuarkusMain;
    import java.io.*;
    import java.nio.file.*;
    import java.util.zip.*;

    /**
     * FileDecompressor is a utility class for decompressing archives.
     * It uses the Java built-in compression libraries and provides
     * a simple interface for the Quarkus application.
     */
    @QuarkusMain
    public class FileDecompressor {

        /**
         * Main method entry point for the application.
         * @param args command line arguments
         */
        public static void main(String... args) {
            Quarkus.run(FileDecompressor.class, args);
        }

        /**
         * Decompresses a ZIP file to a specified directory.
         * @param zipFilePath The path to the ZIP file to decompress.
         * @param outputDirectory The directory to extract the files to.
         */
        public void decompressZip(String zipFilePath, String outputDirectory) {
            try {
                // Check if the file exists
                Path path = Paths.get(zipFilePath);
                if (!Files.exists(path)) {
                    throw new FileNotFoundException("File not found: " + zipFilePath);
                }

                // Create the output directory if it doesn't exist
                Path outputPath = Paths.get(outputDirectory);
                Files.createDirectories(outputPath);

                // Open the ZIP file
                try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
                    ZipEntry entry = zipIn.getNextEntry();
                    // Iterate over the ZIP entries
                    while (entry != null) {
                        String fileName = entry.getName();
                        Path newFile = outputPath.resolve(fileName).normalize();

                        if (!newFile.toAbsolutePath().startsWith(outputPath.toAbsolutePath())) {
                            // This condition is to prevent zip slip attack
                            throw new IOException("We won't decompress files starging outside of the target folder.");
                        }

                        if (entry.isDirectory()) {
                            Files.createDirectories(newFile);
                        } else {
                            // Create the file
                            try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(newFile))) {
                                byte[] buffer = new byte[1024];
                                int len;
                                while ((len = zipIn.read(buffer)) > 0) {
                                    bos.write(buffer, 0, len);
                                }
                            }
                        }
                        zipIn.closeEntry();
                        entry = zipIn.getNextEntry();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }