package loantree.example.pidev.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/Home")
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "index";
        }

    }
