package br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf;

import br.com.wktechnology.agenciabancosangue.domains.enums.PersonCheck;
import br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf.feign.CpfValidatorGatewayImpl;
import br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf.feign.client.CpfValidatorFeignClient;
import br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf.feign.client.json.ValidateCpfEnableToCreateResponseJson;
import br.com.wktechnology.agenciabancosangue.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class CpfValidatorGatewayImplUniTest extends BaseTest {

    @InjectMocks
    private CpfValidatorGatewayImpl cpfValidatorGateway;

    @Mock
    private CpfValidatorFeignClient cpfValidatorFeignClient;

    @Test
    @DisplayName("Should by able to create")
    public void givenCpf_whenCreatePerson_thenReturnEnableToCreate() {
        // given
        final String cpf = "157.692.800-42";
        when(this.cpfValidatorFeignClient.validateCpfEnableToCreate(cpf))
                .thenReturn(ValidateCpfEnableToCreateResponseJson.builder().status("ABLE_TO_CREATE").build());

        // when
        final PersonCheck personCheck = this.cpfValidatorGateway.validateCpfToEnableToCreate(cpf);

        // then
        assertEquals(PersonCheck.ENABLE, personCheck);
    }

    @Test
    @DisplayName("Should by disable to create")
    public void givenCpf_whenCreatePerson_thenReturnDisableToCreate() {
        // given
        final String cpf = "999.999.999-99";
        when(this.cpfValidatorFeignClient.validateCpfEnableToCreate(cpf))
                .thenReturn(ValidateCpfEnableToCreateResponseJson.builder().status("UNABLE_TO_CREATE").build());

        // when
        final PersonCheck personCheck = this.cpfValidatorGateway.validateCpfToEnableToCreate(cpf);

        // then
        assertEquals(PersonCheck.DISABLE, personCheck);
    }
}
