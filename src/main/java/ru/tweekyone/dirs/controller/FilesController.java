package ru.tweekyone.dirs.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tweekyone.dirs.dto.DirectoryDTO;
import ru.tweekyone.dirs.dto.FileDTO;
import ru.tweekyone.dirs.service.DirectoryService;
import ru.tweekyone.dirs.service.FileService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping
public class FilesController {
    private final DirectoryService directoryService;
    private final FileService fileService;

    @GetMapping(value = "/get/{directoryId}")
    public String getFiles(@PathVariable Long directoryId, Model model) {
        List<FileDTO> result = fileService.getFilesList(directoryId);
        DirectoryDTO directoryDTO = directoryService.getDirectoryById(directoryId);
        model.addAttribute("dir", directoryDTO);
        model.addAttribute("files", result);
        return "files_page :: table-fragment";
    }
}
