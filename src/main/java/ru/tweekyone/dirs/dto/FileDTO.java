package ru.tweekyone.dirs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.tweekyone.dirs.util.FileName;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FileDTO {

    private String name;

    private FileName fileName;

    private Boolean isFile;

    private String size;
}
