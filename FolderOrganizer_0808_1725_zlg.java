// 代码生成时间: 2025-08-08 17:25:40
package com.yourcompany.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FolderOrganizer {

    private static final String DIRECTORY_NOT_FOUND_MSG = "Directory not found: %%s";
    private static final String UNEXPECTED_ERROR_MSG = "An unexpected error occurred: %%s";
    private static final String CANNOT_CREATE_DIR_MSG = "Cannot create directory: %%s";
    private static final String ORGANIZATION_COMPLETE_MSG = "Folder organization is complete.";

    public static void main(String[] args) {
        String sourceFolderPath = "/path/to/your/folder";
        try {
            organizeFolder(new File(sourceFolderPath));
            System.out.println(ORGANIZATION_COMPLETE_MSG);
        } catch (IOException e) {
            System.err.println(String.format(UNEXPECTED_ERROR_MSG, e.getMessage()));
        }
    }

    /**
     * Organize the specified folder by sorting its contents.
     *
     * @param folder The folder to be organized.
     * @throws IOException If an I/O error occurs.
     */
    public static void organizeFolder(File folder) throws IOException {
        if (!folder.exists()) {
            throw new IOException(String.format(DIRECTORY_NOT_FOUND_MSG, folder.getAbsolutePath()));
        }

        if (!folder.isDirectory()) {
            throw new IOException("This is not a directory: " + folder.getAbsolutePath());
        }

        File[] files = folder.listFiles();
        if (files == null) {
            throw new IOException(String.format(CANNOT_CREATE_DIR_MSG, folder.getAbsolutePath()));
        }

        Arrays.sort(files, Comparator.comparing(File::getName));

        for (File file : files) {
            if (file.isDirectory()) {
                // Recursively organize subdirectories
                organizeFolder(file);
            } else {
                // Files are already sorted by name in this implementation.
            }
        }
    }
}
