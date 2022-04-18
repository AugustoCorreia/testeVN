package com.testetecnico.testetecnico.exercise1;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class E1Service {
    public ResponseEntity<Object> getList(Integer limit) {
        if (limit < 1) return ResponseEntity.badRequest().body("Número Inválido apenas numeros acima de 1!");
       return ok(IntStream.range(1, limit).mapToObj(num -> {
            if (num % 3 == 0 && num % 5 == 0) {
                return "Visual Nuts";
            }
            if (num % 3 == 0) {
                return "Visual";
            }
            if (num % 5 == 0) {
                return "Nuts";
            }
            return String.valueOf(num);
        }).collect(Collectors.toList()));
    }
}
