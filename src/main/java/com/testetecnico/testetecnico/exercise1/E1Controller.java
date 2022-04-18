package com.testetecnico.testetecnico.exercise1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "e1")
@RestController
@RequiredArgsConstructor
public class E1Controller {

    private final E1Service service;

    @GetMapping("/{limit}")
    public ResponseEntity<Object> getListReturn(@PathVariable Integer limit){
        return service.getList(limit);
    }

}
