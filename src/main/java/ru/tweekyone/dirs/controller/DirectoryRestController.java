package ru.tweekyone.dirs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tweekyone.dirs.dto.DirectoryDTO;
import ru.tweekyone.dirs.service.DirectoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dirs_and_files")
public class DirectoryRestController {
    private final DirectoryService directoryService;

    @PostMapping(value = "/create")
    public ResponseEntity<DirectoryDTO> createDirectory(@RequestBody String path) {
        DirectoryDTO result = directoryService.createDirectory(path);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
