package br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf;

import br.com.wktechnology.agenciabancosangue.domains.enums.PersonCheck;

public interface CpfValidatorGateway {
    PersonCheck validateCpfToEnableToCreate(final String cpf);
}
