package ru.tweekyone.dirs.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tweekyone.dirs.dto.DirectoryDTO;
import ru.tweekyone.dirs.service.DirectoryService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/dirs_and_files")
public class DirectoryController {
    private final DirectoryService directoryService;

    @GetMapping
    public String getDirectories(Model model) {
        model.addAttribute("dirs", directoryService.getAllDirectories());
        return "dirs_page";
    }

    @PostMapping(value = "/create")
    public String createDirectory(@RequestParam String path, Model model) {
        DirectoryDTO directoryDTO = directoryService.createDirectory(path);
        model.addAttribute("dirs", directoryDTO);
        return "dirs_page";
    }
}
