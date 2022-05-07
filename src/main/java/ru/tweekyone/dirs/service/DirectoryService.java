package ru.tweekyone.dirs.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tweekyone.dirs.dto.DirectoryDTO;
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
        Directory directory;
        List<CustomFile> files;
        try (Stream<Path> stream = Files.walk(Path.of(path), 1)) {
            if (Files.isDirectory(Path.of(path))) {
                directory = Directory.builder().dateTime(LocalDateTime.now()).path(path).build();
                files = stream
                        .map(p -> new File(path))
                        .map(f -> CustomFile.builder().isFile(f.isFile()).size(f.length()).directory(directory).build())
                        .collect(Collectors.toList());
            } else {
                throw new NoSuchDirectoryException(path);
            }
        } catch (IOException ex) {

        } catch (InvalidPathException ex) {

        }
        directoryRepo.save(directory);
        List<CustomFile> savedFiles = customFileRepo.saveAll(files);
    }
}
