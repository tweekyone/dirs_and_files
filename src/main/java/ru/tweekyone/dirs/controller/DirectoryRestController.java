package ru.tweekyone.dirs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tweekyone.dirs.dto.DirectoryDTO;
import ru.tweekyone.dirs.dto.FileDTO;
import ru.tweekyone.dirs.service.DirectoryService;
import ru.tweekyone.dirs.service.FileService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dirs_and_files")
public class DirectoryRestController {
    private final DirectoryService directoryService;
    private final FileService fileService;

    @PostMapping(value = "/create")
    public ResponseEntity<DirectoryDTO> createDirectory(@RequestBody String path) {
        DirectoryDTO result = directoryService.createDirectory(path);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<List<FileDTO>> getFiles(@PathVariable Long id) {
        return new ResponseEntity<>(fileService.getFilesList(id), HttpStatus.OK);
    }
}
