package br.com.wktechnology.agenciabancosangue.domains;

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
public class PossibleDonation {

    @JsonProperty("tipo_sanguineo")
    private String bloodType;

    @JsonProperty("quantidade")
    private int quantity;

    @JsonProperty("pessoas")
    private List<Person> personList;
}
