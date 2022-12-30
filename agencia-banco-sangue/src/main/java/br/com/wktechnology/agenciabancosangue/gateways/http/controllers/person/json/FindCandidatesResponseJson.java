package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class FindCandidatesResponseJson {

    @JsonProperty("pessoas")
    private List<FindCandidatesJson> persons;
}
