package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person;

import br.com.wktechnology.agenciabancosangue.databuilders.utils.BaseTest;
import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.CreatePersonRequestJson;
import br.com.wktechnology.agenciabancosangue.usecases.CreatePersonUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PersonControllerUnitTest extends BaseTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private CreatePersonUseCase createPersonUseCase;

    @Test
    @DisplayName("Should create Person with success")
    public void givenListPerson_whenCreate_thenReturnVoid() {
        // given
        final List<CreatePersonRequestJson> requestJsonList = new ArrayList<>();
        final CreatePersonRequestJson createPersonRequestJson = getCreatePersonRequestJson();
        requestJsonList.add(createPersonRequestJson);
        final Person personCreated = this.domainsDataBuilder.getPersonDataBuilder().build();
        when(this.createPersonUseCase.create(any(CreatePersonRequestJson.class))).thenReturn(personCreated);

        // when
        this.personController.create(requestJsonList);

        // then
        verify(this.createPersonUseCase,
                VerificationModeFactory.times(1)).create(createPersonRequestJson);
    }

    private CreatePersonRequestJson getCreatePersonRequestJson() {
        return CreatePersonRequestJson
                .builder()
                .name(this.faker.name().fullName())
                .cpf(this.faker.internet().uuid())
                .rg(this.faker.internet().uuid())
                .birthDate(this.faker.date().toString())
                .gender(this.faker.internet().uuid())
                .mother(this.faker.funnyName().name())
                .father(this.faker.funnyName().name())
                .email(this.faker.bothify("????##@gmail.com"))
                .cep(this.faker.internet().uuid())
                .street(this.faker.address().streetAddress())
                .number(123)
                .neighborhood(this.faker.address().fullAddress())
                .city(this.faker.address().city())
                .state(this.faker.address().state())
                .phone(this.faker.phoneNumber().phoneNumber())
                .cellphone(this.faker.phoneNumber().cellPhone())
                .height(1.83)
                .weight(81)
                .bloodType(this.faker.dog().name())
                .imc(81.0)
                .build();
    }
}
