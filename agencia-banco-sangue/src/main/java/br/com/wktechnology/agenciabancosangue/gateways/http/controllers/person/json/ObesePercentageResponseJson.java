package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import br.com.wktechnology.agenciabancosangue.domains.ObesePercentage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ObesePercentageResponseJson {

    @JsonProperty("percentual_obesos")
    public List<ObesePercentage> obesePercentages;
}
