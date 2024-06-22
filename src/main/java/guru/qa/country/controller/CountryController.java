package guru.qa.country.controller;

import guru.qa.country.model.CountryJson;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public List<CountryJson> allCountries() {
        return countryService.allCountries();
    }

    @PostMapping("/add")
    public CountryJson addCountry(CountryJson countryJson) {
        return countryService.addCountry(countryJson);
    }

    @PatchMapping("/{id}/rename")
    public CountryJson renameCountry(@PathVariable UUID id, @RequestParam String newName) {
        return countryService.renameCountry(id, newName);
    }
}
