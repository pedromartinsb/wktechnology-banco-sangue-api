package br.com.wktechnology.agenciabancosangue.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BloodTypeAgeAverage {

    @JsonProperty("tipo_sanguineo")
    private String bloodType;

    @JsonProperty("media")
    private int average;
}
