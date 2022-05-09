package ru.tweekyone.dirs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileDTO {

    private String name;

    private Boolean isFile;

    private String size;
}
