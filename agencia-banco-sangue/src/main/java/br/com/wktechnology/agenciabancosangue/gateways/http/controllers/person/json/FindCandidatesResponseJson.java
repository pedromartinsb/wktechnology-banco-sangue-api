package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class FindCandidatesResponseJson {
    @JsonProperty("numero")
    private Integer number;
}
