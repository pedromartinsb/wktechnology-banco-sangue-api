package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import br.com.wktechnology.agenciabancosangue.domains.enums.States;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FindCandidatesJson {

    @JsonProperty("estado")
    private States state;

    @JsonProperty("numero")
    private Integer number;
}
