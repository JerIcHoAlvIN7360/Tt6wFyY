// 代码生成时间: 2025-08-16 09:43:03
package com.example.backupandrestore;

import io.quarkus.logging.Log;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

/**
 * Service class for data backup and restore operations.
 */
@ApplicationScoped
public class DataBackupAndRestoreService {

    private static final String DATA_FOLDER = "./data";
    private static final String BACKUP_FOLDER = "./backup";
    private static final String FILE_EXTENSION = ".dat";

    /**
     * Backups the data located in the data folder to the backup folder.
     *
     * @return The path of the backup file if backup is successful, otherwise Optional.empty()
     */
    public Optional<Path> backupData() {
        try {
            Path dataPath = Paths.get(DATA_FOLDER);
            if (!Files.exists(dataPath)) {
                Files.createDirectories(dataPath);
            }

            Path backupPath = Paths.get(BACKUP_FOLDER);
            if (!Files.exists(backupPath)) {
                Files.createDirectories(backupPath);
            }

            String backupFileName = "backup_" + System.currentTimeMillis() + FILE_EXTENSION;
            Path backupFilePath = backupPath.resolve(backupFileName);

            // Copy data from data folder to backup folder
            Files.walk(dataPath).forEach(sourcePath -> {
                try {
                    Path targetPath = backupFilePath.resolveSibling(DATA_FOLDER.relativize(sourcePath));
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    Log.error("Error backing up file: " + sourcePath, e);
                }
            });

            return Optional.of(backupFilePath);
        } catch (IOException e) {
            Log.error("Error during backup operation", e);
            return Optional.empty();
        }
    }

    /**
     * Restores the data from the provided backup file to the data folder.
     *
     * @param backupFilePath The path of the backup file to restore from.
     * @return true if restore is successful, otherwise false
     */
    public boolean restoreData(Path backupFilePath) {
        try {
            if (!Files.exists(backupFilePath)) {
                Log.error("Backup file does not exist: " + backupFilePath);
                return false;
            }

            Path dataPath = Paths.get(DATA_FOLDER);
            if (!Files.exists(dataPath)) {
                Files.createDirectories(dataPath);
            }

            // Copy data from backup folder to data folder
            Files.walk(backupFilePath).forEach(sourcePath -> {
                try {
                    Path targetPath = dataPath.resolve(DATA_FOLDER.relativize(sourcePath));
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    Log.error("Error restoring file: " + sourcePath, e);
                }
            });

            return true;
        } catch (IOException e) {
            Log.error("Error during restore operation", e);
            return false;
        }
    }
}
