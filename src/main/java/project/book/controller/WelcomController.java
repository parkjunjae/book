package project.book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomController {

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome to the Book Application!";
    }
}
