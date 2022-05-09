package ru.tweekyone.dirs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DirectoryDTO {

    private long id;

    private String dateTime;

    private String path;

    private int dirCount;

    private int fileCount;

    private String fileSize;
}
