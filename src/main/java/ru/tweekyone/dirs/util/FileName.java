package ru.tweekyone.dirs.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class FileName implements Comparable<FileName> {
    private List<NameChunk> nameChunks;

    public FileName(String stringFileName) {
        nameChunks = ParseFileNameUtil.parseFileName(stringFileName);
    }

    @Override
    public int compareTo(FileName fileName) {
        int minSize = Math.min(nameChunks.size(), fileName.getNameChunks().size());

        for (int i = 0; i < minSize; i++) {
            int comparisonResult = this.nameChunks.get(i).compareTo(fileName.getNameChunks().get(i));
            if (comparisonResult != 0) {
                return comparisonResult;
            }
        }

        return Integer.compare(nameChunks.size(), fileName.getNameChunks().size());
    }
}
