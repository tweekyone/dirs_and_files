package ru.tweekyone.dirs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileDTO {

    private Boolean isFile;

    private Long size;
}
