package com.testetecnico.testetecnico.exercise2;

import com.google.gson.Gson;
import com.testetecnico.testetecnico.exercise2.model.Country;
import com.testetecnico.testetecnico.exercise2.model.CountryResponse;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class E2Service {


    private Gson gson = new Gson();

    @SneakyThrows
    public ResponseEntity<Object> review(MultipartFile file) {
        Country[] countries = gson.fromJson(new String(file.getBytes()), Country[].class);

        HashSet<Country> countryHashSet = new HashSet<>(Arrays.asList(countries));
        List<Country> countriesDe = getCountriesDe(countryHashSet);
        Set<String> languagesSet = new HashSet<>();
        List<String> commonLanguagesList = new ArrayList<>();

        fillLanguages(countryHashSet, languagesSet, commonLanguagesList);

        Country countryMoreLanguages = getCountryMoreLanguages(countryHashSet);
        Map<String, Long> collect = getCollect(commonLanguagesList);
        List<String> commonLanguagesListFinal = getCommomLanguages(collect);

        return ok().body(
                CountryResponse.builder()
                        .numberOfCountries(countryHashSet.size())
                        .countriesDeutch(countriesDe)
                        .allLanguages(languagesSet)
                        .countryWithMoreLanguages(countryMoreLanguages)
                        .commonLanguages( commonLanguagesListFinal  )
                        .build()
        );
    }

    private void fillLanguages(HashSet<Country> countryHashSet, Set<String> languagesSet, List<String> commonLanguagesList) {
        countryHashSet.forEach(country -> languagesSet.addAll(country.getLanguages()));
        countryHashSet.forEach(country -> commonLanguagesList.addAll(country.getLanguages()));
    }

    private List<Country> getCountriesDe(HashSet<Country> countryHashSet) {
        return  countryHashSet.stream().filter(country -> country.getLanguages().contains("de")).collect(Collectors.toList());
    }

    private Map<String, Long> getCollect(List<String> commonLanguagesList) {
      return commonLanguagesList.stream().collect(Collectors.groupingBy(nome -> nome, Collectors.counting()));
    }

    private List<String> getCommomLanguages(Map<String, Long> collect) {
        List<String> commonLanguagesListFinal = new ArrayList<>();
        Long maxRepeat = collect.values().stream().max(Comparator.comparing(a -> a)).get();
        collect.forEach((key, value)->{
            if (value.equals(maxRepeat))
                commonLanguagesListFinal.add(key);
        });
        return commonLanguagesListFinal;
    }

    private Country getCountryMoreLanguages(HashSet<Country> countryHashSet) {
        return countryHashSet.stream().max(Comparator.comparingInt(a -> a.getLanguages().size())).orElse(null);
    }
}
