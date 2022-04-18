package com.testetecnico.testetecnico.exercise2.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@Builder
public class CountryResponse {
    private Integer numberOfCountries;
    private List<Country> countriesDeutch;
    private Set<String> allLanguages;
    private Country countryWithMoreLanguages;
    private List<String> commonLanguages;
}
