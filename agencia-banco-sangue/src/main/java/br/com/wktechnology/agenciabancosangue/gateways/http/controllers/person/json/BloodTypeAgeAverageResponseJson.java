package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import br.com.wktechnology.agenciabancosangue.domains.BloodTypeAgeAverage;
import br.com.wktechnology.agenciabancosangue.domains.enums.BloodType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BloodTypeAgeAverageResponseJson {

    @JsonProperty("tipo_sanguineo_idade_media")
    private List<BloodTypeAgeAverage> bloodTypeAgeAverages;
}
