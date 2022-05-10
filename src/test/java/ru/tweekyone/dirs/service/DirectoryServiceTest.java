package ru.tweekyone.dirs.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tweekyone.dirs.dto.DirectoryDTO;
import ru.tweekyone.dirs.entity.CustomFile;
import ru.tweekyone.dirs.entity.Directory;
import ru.tweekyone.dirs.exceptions.NoSuchDirectoryException;
import ru.tweekyone.dirs.repository.CustomFileRepo;
import ru.tweekyone.dirs.repository.DirectoryRepo;
import ru.tweekyone.dirs.util.FileSizeUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class DirectoryServiceTest {
    @Mock
    private DirectoryRepo directoryRepoMock;

    @Mock
    private CustomFileRepo customFileRepoMock;

    @InjectMocks
    private DirectoryService directoryServiceMock;

    private final static String CORRECT_TEST_PATH = "src/test/java/ru/tweekyone/dirs/testDir";
    private Directory testDirectory;

    @BeforeEach
    public void init() {
        testDirectory = createDirectory(CORRECT_TEST_PATH);
        testDirectory.setCustomFiles(createCustomFiles(CORRECT_TEST_PATH, testDirectory));
    }

    @Test
    void shouldCreateDirectoryIfCorrectPath() {
        List<CustomFile> testCustomFiles = createCustomFiles(CORRECT_TEST_PATH, testDirectory);
        DirectoryDTO testDirectoryDTO = getDirectoryDTO(testDirectory, testCustomFiles);

        Mockito.when(directoryRepoMock.saveAndFlush(Mockito.any(Directory.class))).thenReturn(testDirectory);
        Mockito.when(customFileRepoMock.saveAllAndFlush(Mockito.anyCollection())).thenReturn(testCustomFiles);

        DirectoryDTO result = directoryServiceMock.createDirectory(CORRECT_TEST_PATH);

        Assertions.assertThat(result.getDateTime()).isEqualTo(testDirectoryDTO.getDateTime());
        Assertions.assertThat(result.getPath()).isEqualTo(testDirectoryDTO.getPath());
        Assertions.assertThat(result.getDirCount()).isEqualTo(testDirectoryDTO.getDirCount());
        Assertions.assertThat(result.getFileCount()).isEqualTo(testDirectoryDTO.getFileCount());
    }

    @Test
    void shouldThrowExceptionIfIncorrectPath() {
        String incorrectPath = "src/test/java/ru/dirs/testDir";

        Assertions.assertThatThrownBy(() -> directoryServiceMock.createDirectory(incorrectPath))
                .isExactlyInstanceOf(NoSuchDirectoryException.class);
    }

    @Test
    void getAllDirectories() {
        List<Directory> testDirectories = List.of(testDirectory);
        List<DirectoryDTO> testDirectoriesDTO = List.of(getDirectoryDTO(testDirectory, testDirectory.getCustomFiles()));
        Mockito.when(directoryRepoMock.findAll()).thenReturn(testDirectories);

        List<DirectoryDTO> result = directoryServiceMock.getAllDirectories();

        Assertions.assertThat(result.get(0).getPath()).isEqualTo(testDirectoriesDTO.get(0).getPath());
        Assertions.assertThat(result.get(0).getDateTime()).isEqualTo(testDirectoriesDTO.get(0).getDateTime());
    }

    @Test
    void getDirectoryById() {
        DirectoryDTO testDirectoryDTO = getDirectoryDTO(testDirectory, testDirectory.getCustomFiles());

        Mockito.when(directoryRepoMock.getById(Mockito.anyLong())).thenReturn(testDirectory);

        DirectoryDTO result = directoryServiceMock.getDirectoryById(1l);

        Assertions.assertThat(result.getPath()).isEqualTo(testDirectoryDTO.getPath());
        Assertions.assertThat(result.getDateTime()).isEqualTo(testDirectoryDTO.getDateTime());
        Assertions.assertThat(result.getFileCount()).isEqualTo(testDirectoryDTO.getFileCount());
        Assertions.assertThat(result.getDirCount()).isEqualTo(testDirectoryDTO.getDirCount());
    }

    private static Directory createDirectory(String path) {
        return Directory.builder().id(1l).dateTime(LocalDateTime.now()).path(path).build();
    }

    private static List<CustomFile> createCustomFiles(String path, Directory testDirectory) {
        List<CustomFile> customFiles = new LinkedList<>();
        try (Stream<Path> stream = Files.walk(Path.of(path.trim()), 1)){
            customFiles = stream
                    .map(p -> new File(p.toString()))
                    .skip(1l)
                    .map(f -> CustomFile.builder().name(f.getName()).isFile(f.isFile()).size(f.length()).directory(testDirectory).build())
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return customFiles;
    }

    private static DirectoryDTO getDirectoryDTO(Directory directory, List<CustomFile> files) {
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