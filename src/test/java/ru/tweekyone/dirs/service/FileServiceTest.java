package ru.tweekyone.dirs.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tweekyone.dirs.dto.FileDTO;
import ru.tweekyone.dirs.repository.CustomFileRepo;
import ru.tweekyone.dirs.testData.CustomFileTestData;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {
    @Mock
    private CustomFileRepo customFileRepoMock;

    @InjectMocks
    private FileService fileServiceMock;

    private CustomFileTestData customFileTestData;

    @BeforeEach
    public void init() {
        customFileTestData = new CustomFileTestData();
    }

    @Test
    void shouldReturnSortedFileDTOsList() {
        Mockito.when(customFileRepoMock.getCustomFilesByDirectory_Id(Mockito.anyLong()))
                .thenReturn(customFileTestData.getCustomFilesTestData());

        List<FileDTO> result = fileServiceMock.getFilesList(Mockito.anyLong());
        List<FileDTO> expected = customFileTestData.getExpectedSortedFileDTOList();

        Assertions.assertThat(result.size()).isEqualTo(expected.size());

        for (int i = 0; i < result.size(); i++) {
            Assertions.assertThat(result.get(i).getName()).isEqualTo(expected.get(i).getName());
        }
    }
}