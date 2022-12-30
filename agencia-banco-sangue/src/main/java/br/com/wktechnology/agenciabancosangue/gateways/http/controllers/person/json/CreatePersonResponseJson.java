package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CreatePersonResponseJson {
    private Long id;
}
