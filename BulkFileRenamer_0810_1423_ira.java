// 代码生成时间: 2025-08-10 14:23:22
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Quarkus application that acts as a Batch File Renamer Tool.
 */
@QuarkusMain
@Command(name = "bulk-file-renamer", mixinStandardHelpOptions = true, version = "Bulk File Renamer 1.0")
public class BulkFileRenamer implements QuarkusApplication {

    @Parameters(index = "0", description = "The directory containing the files to rename.")
    private String directoryPath;

    @Option(names = { "-p", "--prefix" }, description = "The prefix to add to the filename.")
    private String prefix = "renamed_";

    @Option(names = { "-s", "--suffix" }, description = "The suffix to add to the filename.")
    private String suffix = ".txt";

    @Override
    public int run(String... args) throws Exception {
        // Parse command line options
        CommandLine cmd = new CommandLine(this);
        cmd.parseArgs(args);

        try {
            // Validate directory path
            File directory = new File(directoryPath);
            if (!directory.exists() || !directory.isDirectory()) {
                throw new IllegalArgumentException("Invalid directory path: " + directoryPath);
            }

            // Rename files in the directory
            renameFilesInDirectory(directory);
        } catch (IOException e) {
            System.err.println("Error during file renaming: " + e.getMessage());
            return CommandLine.ExitCode.SOFTWARE;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return CommandLine.ExitCode.SOFTWARE;
        }

        return CommandLine.ExitCode.OK;
    }

    private void renameFilesInDirectory(File directory) throws IOException {
        // Get all files in the directory
        try (Stream<Path> files = Files.walk(directory.toPath())) {
            List<File> fileList = files
                .filter(File::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

            for (File file : fileList) {
                renameFile(file);
            }
        }
    }

    private void renameFile(File file) throws IOException {
        // Construct new file name
        Path newFilePath = file.toPath().resolveSibling(prefix + file.getName() + suffix);
        File newFile = newFile.toFile();

        // Rename file
        Files.move(file.toPath(), newFilePath);
    }

    public static void main(String... args) {
        // Run Quarkus application
        new CommandLine(new BulkFileRenamer()).execute(args);
    }
}
