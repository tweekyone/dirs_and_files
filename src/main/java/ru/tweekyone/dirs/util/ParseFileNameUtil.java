package ru.tweekyone.dirs.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ParseFileNameUtil {

    public static List<NameChunk> parseFileName(String stringFileName) {
        List<NameChunk> nameChunks = new ArrayList<>();
        String[] splited = stringFileName.toLowerCase(Locale.ROOT).split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        for (int i = 0; i < splited.length; i++) {
            splited[i] = splited[i].replaceAll("[^A-Za-z0-9]", "");
            if (!splited[i].isBlank() && !isNumeric(splited[i])) {
                List<NameChunk> list = Arrays.stream(splited[i].split(""))
                        .map(s -> new NameChunk(s.charAt(0)))
                        .collect(Collectors.toList());
                nameChunks.addAll(list);
            } else if (!splited[i].isBlank() && isNumeric(splited[i])) {
                nameChunks.add(new NameChunk(Integer.parseInt(splited[i])));
            } else {
                continue;
            }
        }

        return nameChunks;
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
