package project.book.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/books")
public class LoginController {

    @PostMapping(value = "/login")
    public String loginController(){
    return null;
    }
}
