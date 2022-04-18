package com.testetecnico.testetecnico.exercise2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(path = "countries")
@RestController
@RequiredArgsConstructor
public class E2Controller {

    private final E2Service service;

    @PostMapping
    public ResponseEntity<Object> review(@RequestPart("file") MultipartFile file){
        return service.review(file);
    }

}
