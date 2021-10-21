package io.khatabook.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@AllArgsConstructor
public class GreetingController {
    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return ResponseEntity.status(HttpStatus.OK).body("Welcome");
    }
}
