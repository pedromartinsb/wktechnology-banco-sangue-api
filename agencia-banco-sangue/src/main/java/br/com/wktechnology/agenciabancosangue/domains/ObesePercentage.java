package br.com.wktechnology.agenciabancosangue.domains;

import br.com.wktechnology.agenciabancosangue.domains.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ObesePercentage {

    @JsonProperty("sexo")
    private Gender gender;

    @JsonProperty("percentual")
    private float percentage;
}
