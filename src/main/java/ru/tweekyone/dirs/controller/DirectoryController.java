package ru.tweekyone.dirs.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.tweekyone.dirs.service.DirectoryService;

@Controller
@AllArgsConstructor
public class DirectoryController {

    private final DirectoryService directoryService;
}
