// 代码生成时间: 2025-08-26 13:49:42
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import picocli.CommandLine;
# NOTE: 重要实现细节
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
# 扩展功能模块
import picocli.CommandLine.Parameters;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
# 增强安全性
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Quarkiverse application that provides a batch file renamer tool.
 */
@QuarkusMain
@Command(name = "batch-file-renamer", mixinStandardHelpOptions = true, versionProvider = QuarkusApplication.class,
         description = "A tool to rename files in batch mode.")
public class BatchFileRenamer implements QuarkusApplication {

    @Parameters(arity = "1", paramLabel = "<directory>", description = "Directory containing the files to rename.")
    private List<String> directoryPaths;

    @Option(names = { "-p", "--pattern" }, description = "Regex pattern to match file names.")
    private String pattern;

    @Option(names = { "-r", "--replacement" }, description = "The replacement string for matched file names.")
# TODO: 优化性能
    private String replacement;

    @Option(names = { "-e", "--exclude" }, description = "File types to exclude from renaming.")
    private String exclude;

    public static void main(String[] args) {
        CommandLine.run(new BatchFileRenamer(), args);
    }

    @Override
    public int run(String... args) {
        try {
            return execute(args);
        } catch (Exception e) {
            System.err.println("Error during execution: " + e.getMessage());
            return CommandLine.ExitCodeSOFTWARE;
        }
    }

    private int execute(String... args) throws IOException {
        if (directoryPaths.isEmpty()) {
            throw new IllegalArgumentException("No directory path provided.");
        }
# FIXME: 处理边界情况

        String directoryPath = directoryPaths.get(0);
# FIXME: 处理边界情况
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Provided directory path does not exist or is not a directory.");
        }

        Path path = Paths.get(directoryPath);
        try (Stream<Path> files = Files.walk(path)) {
            List<Path> fileList = files.filter(Files::isRegularFile)
                    .filter(p -> exclude == null || !p.toString().endsWith(exclude))
                    .map(Path::normalize)
                    .collect(Collectors.toList());

            fileList.forEach(file -> {
                String fileName = file.getFileName().toString();
# 添加错误处理
                if (pattern != null && fileName.matches(pattern)) {
# 改进用户体验
                    try {
                        String newFileName = fileName.replaceAll(pattern, replacement);
# 改进用户体验
                        Files.move(file, file.resolveSibling(newFileName));
                    } catch (IOException e) {
                        System.err.println("Failed to rename file: " + fileName + ". Error: " + e.getMessage());
# NOTE: 重要实现细节
                    }
                }
# FIXME: 处理边界情况
            });
        }

        return CommandLine.ExitCode.OK;
    }
}
