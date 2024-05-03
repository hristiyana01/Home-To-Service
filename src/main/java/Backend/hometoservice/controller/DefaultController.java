package Backend.hometoservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class DefaultController {

    @GetMapping("/")
    public String defaultPath() {
        return "index";
    }
    @GetMapping("/index")
    public String indexPath() {
        return "index";
    }
}
