package br.com.wktechnology.agenciabancosangue.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IMC {

    private Double imc;

    @JsonProperty("menorIntervalo")
    private Integer smallerGap;

    @JsonProperty("maiorIntervalo")
    private Integer graterGap;
}