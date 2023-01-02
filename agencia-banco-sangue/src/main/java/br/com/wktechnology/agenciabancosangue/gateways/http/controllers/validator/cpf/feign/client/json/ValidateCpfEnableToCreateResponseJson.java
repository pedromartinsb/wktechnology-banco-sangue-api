package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.validator.cpf.feign.client.json;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCpfEnableToCreateResponseJson {
    private String status;
}
