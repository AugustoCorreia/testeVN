package com.testetecnico.testetecnico.exercise2.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Country {
   private String country;
   private List<String> languages;
}
