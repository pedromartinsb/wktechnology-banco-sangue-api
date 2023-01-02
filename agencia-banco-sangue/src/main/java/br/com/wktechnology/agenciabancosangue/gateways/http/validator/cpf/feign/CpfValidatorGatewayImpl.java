package br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf.feign;

import br.com.wktechnology.agenciabancosangue.domains.enums.PersonCheck;
import br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf.CpfValidatorGateway;
import br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf.feign.client.CpfValidatorFeignClient;
import br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf.feign.client.json.ValidateCpfEnableToCreateResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CpfValidatorGatewayImpl implements CpfValidatorGateway {

    final String ABLE_TO_CREATE = "ABLE_TO_CREATE";
    final String UNABLE_TO_CREATE = "UNABLE_TO_CREATE";

    @Autowired
    private CpfValidatorFeignClient cpfValidatorFeignClient;

    @Override
    public PersonCheck validateCpfToEnableToCreate(String cpf) {
        log.info("cpf: {}", cpf);
        final ValidateCpfEnableToCreateResponseJson response =
            this.cpfValidatorFeignClient.validateCpfEnableToCreate(cpf);

        if (response.getStatus().equals(ABLE_TO_CREATE))
            return PersonCheck.ENABLE;

        return PersonCheck.DISABLE;
    }
}
