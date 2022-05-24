package ru.tweekyone.dirs.testData;

import lombok.Getter;
import ru.tweekyone.dirs.dto.FileDTO;
import ru.tweekyone.dirs.entity.CustomFile;
import ru.tweekyone.dirs.util.FileName;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomFileTestData {
    private final List<CustomFile> customFilesTestData;
    private final List<FileDTO> expectedSortedFileDTOList;

    {
        customFilesTestData = new ArrayList<>();
        customFilesTestData.add(CustomFile.builder().id(1l).name("1_gxXLMIuJDHCH7fwIgEP1cg.png").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(2l).name(".DS_Store").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(3l).name("search?q=list").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(4l).name("of+strings&rlz=1C1GGRV_enRU780RU780q").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(5l).name("~$dтекст на русском").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(6l).name("of+strings&rlz=1C1GGRV_enRU780RU781").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(7l).name("текст на русском").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(8l).name("of+strings&rlz=1C1GGRV_enRU780RU779").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(9l).name("екст на русском").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(10l).name("of+strings&rlz=1C1GGRV_enRU780RU780").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(11l).name("~$екст на русском").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(12l).name("of+strings&rlz=1C1GGRV_enRU780RU78a").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(13l).name("~$текст на русском").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(14l).name("only letters text").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(15l).name("text 564 without46 speci654al symbols").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(16l).name("~$starts with symbols").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(17l).name(".starts with dot").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(18l).name(".начинается с точки").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(19l).name("of+strings&rlz=1C1GGRV_enRU750RU780").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(20l).name("of+strings&rlz=1C1GGRV_enRU750RU778").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(21l).name("of+strings&rlz=1C1GGRV_enRU75RU778").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(22l).name("of+strings&rlz=1C1GGRV_enRU780RU78").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(23l).name(";not dot").isFile(true).size(1l).build());
        customFilesTestData.add(CustomFile.builder().id(24l).name("текст на русском").isFile(false).build());
        customFilesTestData.add(CustomFile.builder().id(25l).name("~$текст на русском").isFile(false).build());
        customFilesTestData.add(CustomFile.builder().id(26l).name("~$starts with symbols").isFile(false).build());
        customFilesTestData.add(CustomFile.builder().id(27l).name(".starts with dot").isFile(false).build());
        customFilesTestData.add(CustomFile.builder().id(28l).name("of+strings&rlz=1C1GGRV_enRU75RU778").isFile(false).build());
        customFilesTestData.add(CustomFile.builder().id(29l).name("of+strings&rlz=1C1GGRV_enRU780RU78").isFile(false).build());

        expectedSortedFileDTOList = new ArrayList<>();
        expectedSortedFileDTOList.add(FileDTO.builder().name(".starts with dot").fileName(new FileName(".starts with dot")).isFile(false).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("of+strings&rlz=1C1GGRV_enRU75RU778").fileName(new FileName("of+strings&rlz=1C1GGRV_enRU75RU778")).isFile(false).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("of+strings&rlz=1C1GGRV_enRU780RU78").fileName(new FileName("of+strings&rlz=1C1GGRV_enRU780RU78")).isFile(false).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("~$starts with symbols").fileName(new FileName("~$starts with symbols")).isFile(false).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("~$текст на русском").fileName(new FileName("~$текст на русском")).isFile(false).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("текст на русском").fileName(new FileName("текст на русском")).isFile(false).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name(".DS_Store").fileName(new FileName(".DS_Store")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name(".starts with dot").fileName(new FileName(".starts with dot")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name(".начинается с точки").fileName(new FileName(".начинается с точки")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("1_gxXLMIuJDHCH7fwIgEP1cg.png").fileName(new FileName("1_gxXLMIuJDHCH7fwIgEP1cg.png")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name(";not dot").fileName(new FileName(";not dot")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("of+strings&rlz=1C1GGRV_enRU75RU778").fileName(new FileName("of+strings&rlz=1C1GGRV_enRU75RU778")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("of+strings&rlz=1C1GGRV_enRU750RU778").fileName(new FileName("of+strings&rlz=1C1GGRV_enRU750RU778")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("of+strings&rlz=1C1GGRV_enRU750RU780").fileName(new FileName("of+strings&rlz=1C1GGRV_enRU750RU780")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("of+strings&rlz=1C1GGRV_enRU780RU78").fileName(new FileName("of+strings&rlz=1C1GGRV_enRU780RU78")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("of+strings&rlz=1C1GGRV_enRU780RU78a").fileName(new FileName("of+strings&rlz=1C1GGRV_enRU780RU78a")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("of+strings&rlz=1C1GGRV_enRU780RU779").fileName(new FileName("of+strings&rlz=1C1GGRV_enRU780RU779")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("of+strings&rlz=1C1GGRV_enRU780RU780").fileName(new FileName("of+strings&rlz=1C1GGRV_enRU780RU780")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("of+strings&rlz=1C1GGRV_enRU780RU780q").fileName(new FileName("of+strings&rlz=1C1GGRV_enRU780RU780q")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("of+strings&rlz=1C1GGRV_enRU780RU781").fileName(new FileName("of+strings&rlz=1C1GGRV_enRU780RU781")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("only letters text").fileName(new FileName("only letters text")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("search?q=list").fileName(new FileName("search?q=list")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("text 564 without46 speci654al symbols").fileName(new FileName("text 564 without46 speci654al symbols")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("~$dтекст на русском").fileName(new FileName("~$dтекст на русском")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("~$starts with symbols").fileName(new FileName("~$starts with symbols")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("~$екст на русском").fileName(new FileName("~$екст на русском")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("~$текст на русском").fileName(new FileName("~$текст на русском")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("екст на русском").fileName(new FileName("екст на русском")).isFile(true).build());
        expectedSortedFileDTOList.add(FileDTO.builder().name("текст на русском").fileName(new FileName("текст на русском")).isFile(true).build());
    }
}
