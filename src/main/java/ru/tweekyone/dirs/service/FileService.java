package ru.tweekyone.dirs.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tweekyone.dirs.dto.FileDTO;
import ru.tweekyone.dirs.entity.CustomFile;
import ru.tweekyone.dirs.repository.CustomFileRepo;
import ru.tweekyone.dirs.util.FileSizeUtil;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class FileService {
    private final CustomFileRepo customFileRepo;

    public List<FileDTO> getFilesList(Long id) {
        List<CustomFile> customFiles = customFileRepo.getCustomFilesByDirectory_Id(id);
        return getFileDTOList(customFiles);
    }

    private List<FileDTO> getFileDTOList (List<CustomFile> customFiles) {
        List<FileDTO> fileDTOList = new LinkedList<>();
        for (CustomFile customFile : customFiles) {
            fileDTOList.add(new FileDTO(
                    customFile.getName(),
                    customFile.getIsFile(),
                    customFile.getIsFile() ? FileSizeUtil.getReadableFileSize(customFile.getSize()) : "<DIR>")
            );
        }

        List<FileDTO> result = fileDTOList.stream()
                                        .sorted(Comparator.comparing(FileDTO::getName, String.CASE_INSENSITIVE_ORDER))
                                        .sorted(Comparator.comparing(FileDTO::getIsFile))
                                        .collect(Collectors.toList());

        return result;
    }
}
