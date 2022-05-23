package ru.tweekyone.dirs.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileName implements Comparable<FileName> {
    private NameChunk[] nameChunks;

    @Override
    public int compareTo(FileName fileName) {
        int minSize = Math.min(nameChunks.length, fileName.getNameChunks().length);

        for (int i = 0; i < minSize; i++) {
            int comparisonResult = this.nameChunks[i].compareTo(fileName.getNameChunks()[i]);
            if (comparisonResult != 0) {
                return comparisonResult;
            }
        }

        return Integer.compare(this.nameChunks.length, fileName.getNameChunks().length);
    }
}
