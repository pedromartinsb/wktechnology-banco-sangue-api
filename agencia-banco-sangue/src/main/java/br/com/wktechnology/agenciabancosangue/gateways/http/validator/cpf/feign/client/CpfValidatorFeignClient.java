package br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf.feign.client;

import br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf.feign.client.json.ValidateCpfEnableToCreateResponseJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@FeignClient(name = "${feign.cpfValidator.name", url = "${feign.cpfValidator.url}")
public interface CpfValidatorFeignClient {

    @GetMapping("users/{cpf}")
    ValidateCpfEnableToCreateResponseJson validateCpfEnableToCreate(@PathVariable(name = "cpf") @Valid String cpf);
}
