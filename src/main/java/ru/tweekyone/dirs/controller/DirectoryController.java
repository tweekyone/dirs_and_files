package ru.tweekyone.dirs.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tweekyone.dirs.dto.DirectoryDTO;
import ru.tweekyone.dirs.dto.FileDTO;
import ru.tweekyone.dirs.service.DirectoryService;
import ru.tweekyone.dirs.service.FileService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/dirs_and_files")
public class DirectoryController {
    private final DirectoryService directoryService;
    private final FileService fileService;

    @GetMapping
    public String getDirectories(Model model) {
        model.addAttribute("dirs", directoryService.getAllDirectories());
        return "dirs_page";
    }

    @PostMapping(value = "/create")
    public String createDirectory(@RequestParam String path, Model model) {
        DirectoryDTO result = directoryService.createDirectory(path);
        model.addAttribute("dirs", result);
        return "dirs_page";
    }

    @GetMapping(value = "/get/{id}")
    public String getFiles(@PathVariable Long id, Model model) {
        List<FileDTO> result = fileService.getFilesList(id);
        model.addAttribute("files", result);
        return "files_page";
    }

}
