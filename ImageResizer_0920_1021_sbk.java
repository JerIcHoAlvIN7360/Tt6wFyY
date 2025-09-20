// 代码生成时间: 2025-09-20 10:21:32
package com.example.imageresizer;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Main class for the Image Resizer application.
 */
@QuarkusMain
public class ImageResizer implements QuarkusApplication {

    private static final String IMAGES_DIRECTORY = "images"; // The directory containing images to resize
    private static final String RESIZED_IMAGES_DIRECTORY = "resized-images"; // The directory for resized images

    @Override
    public int run(String... args) throws Exception {
        // Check if the images directory exists
        File imagesDir = new File(IMAGES_DIRECTORY);
        if (!imagesDir.exists() || !imagesDir.isDirectory()) {
            System.err.println("The images directory does not exist.");
            return 1;
        }

        // Create the resized images directory if it does not exist
        File resizedDir = new File(RESIZED_IMAGES_DIRECTORY);
        if (!resizedDir.exists()) {
            resizedDir.mkdirs();
        }

        // Get all image files from the directory
        try (Stream<File> imageFiles = Files.walk(Paths.get(IMAGES_DIRECTORY)).filter(f -> f.toString().endsWith(".jpg") || f.toString().endsWith(".png"))) {
            List<File> imageList = imageFiles.collect(Collectors.toList());

            // Resize images
            for (File imageFile : imageList) {
                resizeImage(imageFile);
            }

            System.out.println("All images have been resized successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while reading the images directory: " + e.getMessage());
            return 1;
        }

        return 0;
    }

    /**
     * Resizes an image to a specified width and height.
     *
     * @param imageFile The file of the image to resize.
     */
    private void resizeImage(File imageFile) {
        try {
            BufferedImage originalImage = ImageIO.read(imageFile);
            int targetWidth = 800; // Target width
            int targetHeight = 600; // Target height

            // Create a new image with the target size
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());

            // Draw the original image onto the new image, scaling it to fit
            resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(targetWidth, targetHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

            // Save the resized image to the resized images directory
            String imagePath = RESIZED_IMAGES_DIRECTORY + File.separator + imageFile.getName();
            ImageIO.write(resizedImage, "png", new File(imagePath));

            System.out.println("Resized image saved: " + imagePath);
        } catch (IOException e) {
            System.err.println("An error occurred while resizing the image: " + imageFile.getName() + ". Error: " + e.getMessage());
        }
    }

    public static void main(String... args) {
        new ImageResizer().run(args);
    }
}
