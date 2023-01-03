package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.PersonDatabaseGateway;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.CreatePersonRequestJson;
import br.com.wktechnology.agenciabancosangue.usecases.exceptions.PersonAlreadyExistsWithCpfException;
import br.com.wktechnology.agenciabancosangue.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreatePersonUseCaseUnitTest extends BaseTest {

    @InjectMocks
    private CreatePersonUseCase createPersonUseCase;

    @Mock
    private PersonDatabaseGateway personDatabaseGateway;

    @Mock
    private FindPersonByCpfUseCase findPersonByCpfUseCase;

    @Test
    @DisplayName("Should return successful to create a Person")
    public void givenPersonToCreate_whenSave_thenReturnPersonCreated() {
        // given
        final Person personToCreate = this.domainsDataBuilder.getPersonDataBuilder().toCreate().build();
        final Person personCreated = this.domainsDataBuilder.getPersonDataBuilder().build();
        when(this.personDatabaseGateway.create(any(Person.class))).thenReturn(personCreated);
        when(this.personDatabaseGateway.findByCpf(personCreated.getCpf())).thenReturn(Optional.empty());

        // when
        final Person response = this.personDatabaseGateway.create(personToCreate);
        final ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);

        // then
        verify(this.personDatabaseGateway, VerificationModeFactory.times(1))
                .create(personArgumentCaptor.capture());
        final Person personCaptured = personArgumentCaptor.getValue();
        this.assertResponse(personCreated, response);
        this.assertCaptured(personToCreate, personCaptured);
    }

    @Test
    @DisplayName("Should return error person already exists")
    public void givenPersonToCreate_whenSave_thenReturnErrorPersonAlreadyExists() {
        // given
        final Person personFounded = this.domainsDataBuilder.getPersonDataBuilder().build();
        personFounded.setCpf("157.692.800-42");
        final CreatePersonRequestJson personToCreate = getCreatePersonRequestJson();
        when(this.findPersonByCpfUseCase.find(personToCreate.getCpf())).thenReturn(Optional.of(personFounded));

        // when
        final PersonAlreadyExistsWithCpfException error =
                assertThrows(PersonAlreadyExistsWithCpfException.class, () -> this.createPersonUseCase.create(personToCreate));

        // then
        assertEquals(error.getCode(), "wktechnology.agenciabancosangue.error.personAlreadyExistsWithCpfException");
        assertEquals(error.getMessage(), "Person already exists with cpf.");
    }

    private void assertCaptured(final Person personToCreate, final Person personCaptured) {
        assertEquals(personToCreate.getCpf(), personCaptured.getCpf());
        assertEquals(personToCreate.getName(), personCaptured.getName());
        assertNull(personCaptured.getId());
    }

    private void assertResponse(final Person personCreated, final Person response) {
        assertEquals(personCreated.getId(), response.getId());
        assertEquals(personCreated.getCpf(), response.getCpf());
        assertEquals(personCreated.getName(), response.getName());
    }

    private CreatePersonRequestJson getCreatePersonRequestJson() {
        return CreatePersonRequestJson
                .builder()
                .name(this.faker.name().fullName())
                .cpf("15769280042")
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
