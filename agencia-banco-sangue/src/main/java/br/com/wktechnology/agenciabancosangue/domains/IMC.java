package br.com.wktechnology.agenciabancosangue.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IMC {

    private Double imc;

    @JsonProperty("menorIntervalo")
    private Integer smallerGap;

    @JsonProperty("maiorIntervalo")
    private Integer graterGap;
}
