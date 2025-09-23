// 代码生成时间: 2025-09-24 05:08:11
// ImageResizer.java

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ImageResizer is a batch image resizing utility using Java and Quarkus.
 * It resizes images in a specified directory to a specified size.
 */
@QuarkusMain
public class ImageResizer implements QuarkusApplication {

    private int targetWidth = 800;
    private int targetHeight = 600;

    @Override
    public int run(String... args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: ImageResizer <source-directory> <destination-directory> [width] [height]");
            return 1;
        }

        Path sourceDir = Paths.get(args[0]);
        Path destinationDir = Paths.get(args[1]);

        if (args.length >= 3) {
            targetWidth = Integer.parseInt(args[2]);
        }

        if (args.length >= 4) {
            targetHeight = Integer.parseInt(args[3]);
        }

        resizeImages(sourceDir, destinationDir);
        return 0;
    }

    /**
     * Processes the image resizing for all images in the source directory.
     * @param sourceDir The directory containing the original images.
     * @param destinationDir The directory where the resized images will be saved.
     * @throws IOException If an I/O error occurs.
     */
    private void resizeImages(Path sourceDir, Path destinationDir) throws IOException {
        if (!Files.isDirectory(sourceDir)) {
            throw new IllegalArgumentException("The source directory does not exist or is not a directory.");
        }

        if (!Files.exists(destinationDir)) {
            Files.createDirectories(destinationDir);
        }

        try (Stream<Path> stream = Files.walk(sourceDir)) {
            List<Path> imageFiles = stream
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".jpg") || path.toString().endsWith(".png"))
                .collect(Collectors.toList());

            for (Path imageFilePath : imageFiles) {
                resizeImage(imageFilePath, destinationDir.resolve(sourceDir.relativize(imageFilePath).toString()));
            }
        }
    }

    /**
     * Resizes a single image to the target size.
     * @param sourcePath The path to the original image.
     * @param destinationPath The path to save the resized image.
     * @throws IOException If an I/O error occurs.
     */
    private void resizeImage(Path sourcePath, Path destinationPath) throws IOException {
        BufferedImage originalImage = ImageIO.read(sourcePath.toFile());
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());
        resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(targetWidth, targetHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(resizedImage, "png", destinationPath.toFile());
    }

    /**
     * Main entry point for the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        ImageResizer.resizer = new ImageResizer();
        resizer.run(args);
    }

    private static ImageResizer resizer;
}
