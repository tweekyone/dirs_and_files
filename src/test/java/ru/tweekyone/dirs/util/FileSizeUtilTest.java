package ru.tweekyone.dirs.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static ru.tweekyone.dirs.util.FileSizeUtil.getReadableFileSize;

class FileSizeUtilTest {

    @Test
    void getByteFileSize() {
        Assertions.assertThat(getReadableFileSize(1l)).isEqualTo("1 B");
    }

    @Test
    void getKByteFileSize() {
        Assertions.assertThat(getReadableFileSize(1024l)).isEqualTo("1 KB");
    }

    @Test
    void getMByteFileSize() {
        Assertions.assertThat(getReadableFileSize(1048600l)).isEqualTo("1 MB");
    }

    @Test
    void getGByteFileSize() {
        Assertions.assertThat(getReadableFileSize(1073741850l)).isEqualTo("1 GB");
    }

    @Test
    void getTByteFileSize() {
        Assertions.assertThat(getReadableFileSize(1099511627776l)).isEqualTo("1 TB");
    }
}