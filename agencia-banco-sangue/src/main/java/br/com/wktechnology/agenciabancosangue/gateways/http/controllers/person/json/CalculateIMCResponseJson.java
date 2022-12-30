package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import br.com.wktechnology.agenciabancosangue.domains.IMC;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class CalculateIMCResponseJson {

    private List<IMC> imcs;
}
