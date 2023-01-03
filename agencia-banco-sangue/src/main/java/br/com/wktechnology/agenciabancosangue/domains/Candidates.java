package br.com.wktechnology.agenciabancosangue.domains;

import br.com.wktechnology.agenciabancosangue.domains.enums.States;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candidates {

    @JsonProperty("estado")
    private States state;

    @JsonProperty("numero")
    private Integer number;
}
