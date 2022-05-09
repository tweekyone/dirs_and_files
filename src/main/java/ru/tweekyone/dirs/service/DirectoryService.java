package ru.tweekyone.dirs.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tweekyone.dirs.dto.DirectoryDTO;
import ru.tweekyone.dirs.dto.FileDTO;
import ru.tweekyone.dirs.entity.CustomFile;
import ru.tweekyone.dirs.entity.Directory;
import ru.tweekyone.dirs.exceptions.IORuntimeException;
import ru.tweekyone.dirs.exceptions.NoSuchDirectoryException;
import ru.tweekyone.dirs.repository.CustomFileRepo;
import ru.tweekyone.dirs.repository.DirectoryRepo;
import ru.tweekyone.dirs.util.FileSizeUtil;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

            directoryDTO = getDirectoryDTO(savedDirectory, savedFiles);

        } catch (IOException ex) {
            throw new IORuntimeException(ex.getMessage());
        }

        return directoryDTO;
    }

    private DirectoryDTO getDirectoryDTO(Directory savedDirectory, List<CustomFile> savedFiles) {
        int fileCount = (int) savedFiles.stream().filter(sf -> sf.getIsFile()).count();
        int dirCount = savedFiles.size() - fileCount;
        long size = savedFiles.stream().map(f -> f.getSize()).reduce((x, y) -> x + y).get();
        return new DirectoryDTO(
                savedDirectory.getDateTime(),
                savedDirectory.getPath(),
                dirCount,
                fileCount,
                FileSizeUtil.getReadableFileSize(size));
    }
}
