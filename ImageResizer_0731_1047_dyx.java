// 代码生成时间: 2025-07-31 10:47:51
 * 作者：[你的名字]
 * 日期：[当前日期]
 */

package com.yourdomain.imageresizer;

import io.smallrye.mutiny.Multi;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ImageResizer {
    // 图片目标尺寸
    private static final int TARGET_WIDTH = 800;
    private static final int TARGET_HEIGHT = 600;

    public static void main(String[] args) {
        // 检查参数是否提供
        if (args.length < 1) {
            System.out.println("Please provide a directory path containing images to resize.");
            return;
        }

        // 获取图片文件夹路径
        String directoryPath = args[0];
        Path directory = Paths.get(directoryPath);

        // 检查路径是否有效
        if (!Files.isDirectory(directory)) {
            System.out.println("The provided path is not a valid directory.");
            return;
        }

        // 获取目录下所有图片文件
        Multi<File> imageFiles = Multi.createFrom().items(
            Files.walk(directory)
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .filter(file -> file.getName().endsWith(".jpg") || file.getName().endsWith(".png"))
                .collect(Collectors.toList())
        );

        // 处理每个图片文件
        imageFiles.subscribe().with(file -> resizeImage(file, TARGET_WIDTH, TARGET_HEIGHT));
    }

    /**
     * 调整图片尺寸
     *
     * @param imageFile 图片文件
     * @param targetWidth 目标宽度
     * @param targetHeight 目标高度
     */
    private static void resizeImage(File imageFile, int targetWidth, int targetHeight) {
        try {
            BufferedImage originalImage = ImageIO.read(imageFile);
            // 计算缩放比例
            double scaleX = (double) targetWidth / originalImage.getWidth();
            double scaleY = (double) targetHeight / originalImage.getHeight();
            double scale = Math.min(scaleX, scaleY);

            // 创建新的图片缓冲区
            BufferedImage resizedImage = new BufferedImage(
                (int) (originalImage.getWidth() * scale),
                (int) (originalImage.getHeight() * scale),
                originalImage.getType()
            );

            // 绘制调整后的图片
            resizedImage.getGraphics().drawImage(
                originalImage.getScaledInstance(
                    (int) (originalImage.getWidth() * scale),
                    (int) (originalImage.getHeight() * scale),
                    java.awt.Image.SCALE_SMOOTH
                ), 0, 0, null
            );

            // 保存调整后的图片
            String resizedFileName = imageFile.getAbsolutePath() + "_resized";
            ImageIO.write(resizedImage, "png", new File(resizedFileName));

            System.out.println("Resized image saved: " + resizedFileName);

        } catch (IOException e) {
            System.err.println("Error processing image file: " + imageFile.getAbsolutePath());
            e.printStackTrace();
        }
    }
}
