package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CalculateIMCRequestJson {

    @JsonProperty("pessoas")
    @NotEmpty
    private List<CreatePersonRequestJson> persons;
}
