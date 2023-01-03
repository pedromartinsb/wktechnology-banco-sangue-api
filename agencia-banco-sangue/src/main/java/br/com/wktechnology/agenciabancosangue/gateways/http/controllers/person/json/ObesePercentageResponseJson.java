package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import br.com.wktechnology.agenciabancosangue.domains.ObesePercentage;
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
public class ObesePercentageResponseJson {

    @JsonProperty("percentual_obesos")
    public List<ObesePercentage> obesePercentages;
}
