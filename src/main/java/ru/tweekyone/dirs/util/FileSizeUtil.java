package ru.tweekyone.dirs.util;

public class FileSizeUtil {
    public static String getReadableFileSize (long size) {
        String[] sizes = { "B", "KB", "MB", "GB", "TB" };
        int order = 0;
        while (size >= 1024 && order < sizes.length - 1) {
            order++;
            size = size/1024;
        }

        return String.format("%d %s", size, sizes[order]);
    }
}
