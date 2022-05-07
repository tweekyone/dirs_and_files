package ru.tweekyone.dirs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.tweekyone.dirs.entity.CustomFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DirectoryDTO {

    private LocalDateTime dateTime;

    private String path;

    private List<CustomFile> customFiles;
}
