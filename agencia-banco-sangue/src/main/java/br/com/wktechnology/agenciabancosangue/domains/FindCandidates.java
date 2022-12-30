package br.com.wktechnology.agenciabancosangue.domains;

import br.com.wktechnology.agenciabancosangue.domains.enums.States;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FindCandidates {

    @JsonProperty("estado")
    private States state;

    @JsonProperty("numero")
    private Integer number;
}
