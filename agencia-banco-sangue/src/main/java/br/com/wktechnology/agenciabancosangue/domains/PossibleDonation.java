package br.com.wktechnology.agenciabancosangue.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PossibleDonation {

    @JsonProperty("tipo_sanguineo")
    private String bloodType;

    @JsonProperty("quantidade")
    private int quantity;

    @JsonProperty("pessoas")
    private List<Person> personList;
}
