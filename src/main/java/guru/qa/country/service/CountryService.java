package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.model.CountryJson;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryJson> allCountries() {
        return countryRepository.findAll()
                .stream()
                .map(e -> new CountryJson(
                        e.getId(),
                        e.getName(),
                        e.getCode(),
                        e.getDateOfIndependence()
                ))
                .toList();

    }

    public CountryJson addCountry(@Nonnull CountryJson countryJson) {
        CountryEntity countryEntity = CountryEntity.fromJson(countryJson);
        return CountryJson.fromEntity(countryRepository.save(countryEntity));
    }

    @Transactional
    public CountryJson renameCountry(UUID id, String newName) {
        CountryEntity countryEntity = countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
        countryEntity = new CountryEntity(countryEntity.getId(),
                newName,
                countryEntity.getCode(),
                countryEntity.getDateOfIndependence());
        countryRepository.save(countryEntity);
        return CountryJson.fromEntity(countryEntity);
    }
}
