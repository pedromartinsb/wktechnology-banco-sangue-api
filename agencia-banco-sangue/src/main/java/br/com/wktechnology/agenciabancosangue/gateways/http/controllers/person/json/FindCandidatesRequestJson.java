package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindCandidatesRequestJson {

    @Length(max = 2, message = "O campo deve conter apenas 2 caracteres.")
    @JsonProperty("estado")
    @NotEmpty(message = "Campo estado é obrigatório.")
    private String state;
}
