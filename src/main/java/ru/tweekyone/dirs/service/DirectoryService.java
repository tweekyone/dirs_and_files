package ru.tweekyone.dirs.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tweekyone.dirs.dto.DirectoryDTO;
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
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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
        if (!Files.isDirectory(Path.of(path.trim()))) {
            throw new NoSuchDirectoryException(path);
        }

        DirectoryDTO directoryDTO;
        try (Stream<Path> stream = Files.walk(Path.of(path.trim()), 1)) {
            Directory directory = Directory.builder().dateTime(LocalDateTime.now()).path(path.trim()).build();
            List<CustomFile> files = stream
                    .map(p -> new File(p.toString()))
                    .skip(1l)
                    .map(f -> CustomFile.builder().name(f.getName()).isFile(f.isFile()).size(f.length()).directory(directory).build())
                    .collect(Collectors.toList());

            Directory savedDirectory = directoryRepo.saveAndFlush(directory);
            List<CustomFile> savedFiles = customFileRepo.saveAllAndFlush(files);

            directoryDTO = getDirectoryDTO(savedDirectory, savedFiles);

        } catch (IOException ex) {
            throw new IORuntimeException(ex.getMessage());
        }

        return directoryDTO;
    }

    public List<DirectoryDTO> getAllDirectories() {
        List<Directory> directories = directoryRepo.findAll();
        List<DirectoryDTO> result = directories.stream()
                .map(d -> getDirectoryDTO(d, d.getCustomFiles()))
                .collect(Collectors.toList());
        return result;
    }

    public List<DirectoryDTO> getRefreshedDirectoryList() {
        List<Directory> directories = directoryRepo.findAll().stream()
                .sorted(Comparator.comparing(Directory::getId))
                .collect(Collectors.toList());
        Directory lastAddedDirectory = directories.get(directories.size() - 1);
        lastAddedDirectory.setCustomFiles(customFileRepo.getCustomFilesByDirectory_Id(lastAddedDirectory.getId()));
        List<DirectoryDTO> result = directories.stream()
                .map(d -> getDirectoryDTO(d, d.getCustomFiles()))
                .collect(Collectors.toList());
        return result;
    }

    public DirectoryDTO getDirectoryById(Long directoryId) {
        Directory directory = directoryRepo.getById(directoryId);
        return getDirectoryDTO(directory, directory.getCustomFiles());
    }

    private DirectoryDTO getDirectoryDTO(Directory directory, List<CustomFile> files) {
        int fileCount = (int) files.stream().filter(sf -> sf.getIsFile()).count();
        int dirCount = files.size() - fileCount;
        long size = files.stream().map(f -> f.getSize()).reduce((x, y) -> x + y).get();
        return new DirectoryDTO(
                directory.getId(),
                directory.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
                directory.getPath(),
                dirCount,
                fileCount,
                FileSizeUtil.getReadableFileSize(size));
    }
}
