package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import br.com.wktechnology.agenciabancosangue.domains.enums.BloodType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BloodTypeAgeAverageResponseJson {

    @JsonProperty("tipo_sanguineo")
    private String bloodType;

    @JsonProperty("media")
    private int average;
}
