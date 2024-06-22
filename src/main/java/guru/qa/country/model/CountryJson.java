package guru.qa.country.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.country.data.CountryEntity;

import java.util.Date;
import java.util.UUID;

public record CountryJson(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("name")
        String name,
        @JsonProperty("code")
        String code,
        @JsonProperty("dateOfIndependent")
        Date dateOfIndependent) {

    public static CountryJson fromEntity(CountryEntity countryEntity) {
        return new CountryJson(
                countryEntity.getId(),
                countryEntity.getName(),
                countryEntity.getCode(),
                countryEntity.getDateOfIndependence()
        );
    }
}
