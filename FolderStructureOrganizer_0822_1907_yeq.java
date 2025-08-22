// 代码生成时间: 2025-08-22 19:07:07
 * A program to organize the folder structure by moving files into subdirectories based on file extensions.
 *
 * @author Your Name
 * @version 1.0
 */
package com.yourcompany.quarkus.folderorganizer;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.EnumSet;
import java.util.stream.Stream;

@QuarkusMain
public class FolderStructureOrganizer implements QuarkusApplication {
    @Override
    public int run(String... args) throws IOException {
        // Check for directory path argument
        if (args.length < 1) {
            System.out.println("Please provide a directory path as an argument.");
            return 1;
        }

        Path directoryPath = Paths.get(args[0]);
        if (!Files.isDirectory(directoryPath)) {
            System.out.println("The provided path is not a directory: " + args[0]);
            return 1;
        }

        // Process each file in the directory
        try (Stream<Path> paths = Files.walk(directoryPath, 1)) {
            paths.filter(Files::isRegularFile)
                .forEach(file -> moveFileToSubdirectory(file, directoryPath));
        } catch (IOException e) {
            System.out.println("An error occurred while processing the directory: " + e.getMessage());
            return 1;
        }

        return 0;
    }

    /**
     * Moves a file to a subdirectory based on its extension.
     *
     * @param file The file to move.
     * @param directoryPath The directory to move the file from.
     * @throws IOException If an I/O error occurs during the move operation.
     */
    private void moveFileToSubdirectory(Path file, Path directoryPath) throws IOException {
        String fileName = file.getFileName().toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        Path subdirectoryPath = directoryPath.resolve(fileExtension);

        // Create the subdirectory if it doesn't exist
        Files.createDirectories(subdirectoryPath);

        // Move the file to the subdirectory
        Path targetPath = subdirectoryPath.resolve(file.getFileName());
        Files.move(file, targetPath, EnumSet.of(StandardCopyOption.REPLACE_EXISTING));
    }

    /**
     * Main method for running the application directly.
     *
     * @param args The command line arguments.
     */
    public static void main(String... args) {
        FolderStructureOrganizer organizer = new FolderStructureOrganizer();
        organizer.run(args);
    }
}
