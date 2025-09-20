// 代码生成时间: 2025-09-20 21:59:39
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class FileBackupSyncTool implements QuarkusApplication {

    // The source directory path
    private static final String SOURCE_DIR = "source_directory_path";
    // The backup directory path
    private static final String BACKUP_DIR = "backup_directory_path";

    @Override
    public int run(String... args) throws Exception {
        backupAndSync();
        return 0;
    }

    /**
     * Performs the backup and synchronization of files from source to backup directory.
     */
    private void backupAndSync() {
        try {
            // Check if source directory exists
            File sourceDir = new File(SOURCE_DIR);
            if (!sourceDir.exists()) {
                System.err.println("Source directory does not exist.");
                return;
            }

            // Create backup directory if not exists
            File backupDir = new File(BACKUP_DIR);
            if (!backupDir.exists()) {
                backupDir.mkdir();
            }

            // Synchronize files from source to backup directory
            synchronizeDirectories(sourceDir, backupDir);
        } catch (IOException e) {
            System.err.println("An error occurred during backup and synchronization: " + e.getMessage());
        }
    }

    /**
     * Synchronizes files from the source directory to the backup directory.
     *
     * @param sourceDir The source directory to synchronize from.
     * @param backupDir The backup directory to synchronize to.
     * @throws IOException If an I/O error occurs.
     */
    private void synchronizeDirectories(File sourceDir, File backupDir) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(sourceDir.getAbsolutePath()))) {
            paths.forEach(path -> {
                File sourceFile = path.toFile();
                File backupFile = new File(backupDir, path.toString().substring(sourceDir.getAbsolutePath().length() + 1));

                // Check if the backup file already exists and is the same as the source file
                if (!backupFile.exists() || !Files.isSameFile(sourceFile.toPath(), backupFile.toPath())) {
                    // Copy the file from source to backup directory
                    Files.copy(sourceFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            });
        }
    }

    /**
     * Main method to start the Quarkus application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Quarkus.run(FileBackupSyncTool.class, args);
    }
}
