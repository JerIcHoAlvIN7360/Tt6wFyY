// 代码生成时间: 2025-09-22 15:22:12
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Callable;

/**
 * A Quarkus based application that serves as a file backup and synchronization tool.
 */
@QuarkusMain
@Command(name = "file-backup-sync", mixinStandardHelpOptions = true, version = "file-backup-sync 1.0")
public class FileBackupSync implements Callable<Integer> {

    @Parameters(index = "0", description = "The source directory to backup.")
    private String sourceDir;

    @Option(names = "-d", description = "The destination directory to sync files.")
    private String destDir;

    /**
     * Runs the backup and synchronization process.
     *
     * @param args Command line arguments.
     * @return Exit code.
     */
    public static void main(String[] args) {
        System.exit(new CommandLine(new FileBackupSync()).execute(args));
    }

    @Override
    public Integer call() throws Exception {
        try {
            Path sourcePath = Paths.get(sourceDir);
            Path destPath = Paths.get(destDir);

            if (!Files.exists(sourcePath) || !Files.isDirectory(sourcePath)) {
                throw new IllegalArgumentException("Source directory does not exist or is not a directory.");
            }

            if (!Files.exists(destPath) && !Files.isDirectory(destPath)) {
                Files.createDirectories(destPath);
            }

            backupAndSync(sourcePath, destPath);
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Performs the backup and synchronization of files from source to destination.
     *
     * @param sourcePath The source directory.
     * @param destPath The destination directory.
     * @throws IOException If an I/O error occurs.
     */
    private void backupAndSync(Path sourcePath, Path destPath) throws IOException {
        Files.walk(sourcePath).forEach(source -> {
            Path target = destPath.resolve(sourcePath.relativize(source));
            try {
                if (Files.isDirectory(source)) {
                    if (!Files.exists(target)) {
                        Files.createDirectories(target);
                    }
                } else {
                    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to backup and sync files.", e);
            }
        });
    }
}
