package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import br.com.wktechnology.agenciabancosangue.domains.PossibleDonation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PossibleDonationsResponseJson {

    @JsonProperty("possiveis_candidatos")
    private List<PossibleDonation> possibleDonations;
}
