package ru.tweekyone.dirs.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tweekyone.dirs.dto.DirectoryDTO;
import ru.tweekyone.dirs.dto.FileDTO;
import ru.tweekyone.dirs.entity.CustomFile;
import ru.tweekyone.dirs.entity.Directory;
import ru.tweekyone.dirs.exceptions.NoSuchDirectoryException;
import ru.tweekyone.dirs.repository.CustomFileRepo;
import ru.tweekyone.dirs.repository.DirectoryRepo;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@AllArgsConstructor
public class DirectoryService {
    private final DirectoryRepo directoryRepo;
    private final CustomFileRepo customFileRepo;

    public DirectoryDTO createDirectory(String path) {
        if (!Files.isDirectory(Path.of(path))) {
            throw new NoSuchDirectoryException(path);
        }

        DirectoryDTO directoryDTO = null;
        try (Stream<Path> stream = Files.walk(Path.of(path), 1)) {
            Directory directory = Directory.builder().dateTime(LocalDateTime.now()).path(path).build();
            List<CustomFile> files = stream
                    .map(p -> new File(p.toString()))
                    .skip(1l)
                    .map(f -> CustomFile.builder().name(f.getName()).isFile(f.isFile()).size(f.length()).directory(directory).build())
                    .collect(Collectors.toList());

            Directory savedDirectory = directoryRepo.save(directory);
            List<CustomFile> savedFiles = customFileRepo.saveAll(files);

            List<FileDTO> fileDTOList = getFileDTOList(savedFiles);
            directoryDTO = getDirectoryDTO(savedDirectory, fileDTOList);

        } catch (IOException ex) {

        } catch (InvalidPathException ex) {

        }

        return directoryDTO;
    }

    private List<FileDTO> getFileDTOList (List<CustomFile> savedFiles) {
        List<FileDTO> fileDTOList = new LinkedList<>();
        for (CustomFile customFile : savedFiles) {
            fileDTOList.add(new FileDTO(
                    customFile.getName(),
                    customFile.getIsFile(),
                    customFile.getSize())
            );
        }
        return fileDTOList;
    }

    private DirectoryDTO getDirectoryDTO(Directory savedDirectory, List<FileDTO> fileDTOList) {
        return new DirectoryDTO(
                savedDirectory.getDateTime(),
                savedDirectory.getPath(),
                fileDTOList);
    }
}
