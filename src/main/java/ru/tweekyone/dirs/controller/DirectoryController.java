package ru.tweekyone.dirs.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.tweekyone.dirs.service.DirectoryService;

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
    public RedirectView createDirectory(@RequestParam String path) {
        directoryService.createDirectory(path);
        return new RedirectView("/dirs_and_files");
    }
}
