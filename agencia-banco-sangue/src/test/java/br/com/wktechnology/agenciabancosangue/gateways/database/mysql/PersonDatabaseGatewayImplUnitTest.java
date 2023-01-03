package br.com.wktechnology.agenciabancosangue.gateways.database.mysql;

import br.com.wktechnology.agenciabancosangue.databuilders.utils.BaseTest;
import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.mysql.PersonDatabaseGatewayImpl;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.mysql.repository.PersonRepository;
import br.com.wktechnology.agenciabancosangue.gateways.exceptions.CreatePersonDatabaseException;
import br.com.wktechnology.agenciabancosangue.gateways.exceptions.FindPersonByCpfDatabaseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonDatabaseGatewayImplUnitTest extends BaseTest {

    @InjectMocks
    private PersonDatabaseGatewayImpl personDatabaseGateway;

    @Mock
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should return successful to create a Person")
    public void givenPersonToCreate_whenSave_thenReturnPersonCreated() {
        // given
        final Person personToCreate = this.domainsDataBuilder.getPersonDataBuilder().toCreate().build();
        final Person personCreated = this.domainsDataBuilder.getPersonDataBuilder().build();
        when(this.personRepository.save(any(Person.class))).thenReturn(personCreated);

        // when
        final Person response = this.personDatabaseGateway.create(personToCreate);
        final ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);

        // then
        verify(this.personRepository, VerificationModeFactory.times(1))
                .save(personArgumentCaptor.capture());
        final Person personCaptured = personArgumentCaptor.getValue();
        this.assertResponse(personCreated, response);
        this.assertCaptured(personToCreate, personCaptured);
    }

    @Test
    @DisplayName("Should update a Person with success return")
    public void givenPersonToUpdate_whenSave_thenReturnPersonUpdated() {
        // given
        final Person personToUpdate = this.domainsDataBuilder.getPersonDataBuilder().toUpdate().build();
        final Person personUpdated = this.domainsDataBuilder.getPersonDataBuilder().build();
        when(this.personRepository.save(any(Person.class))).thenReturn(personUpdated);

        // when
        final Person response = this.personDatabaseGateway.update(personToUpdate);
        final ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);

        // then
        verify(this.personRepository, VerificationModeFactory.times(1))
                .save(personArgumentCaptor.capture());
        assertEquals(personUpdated.getImc(), response.getImc());
    }

    @Test
    @DisplayName("Should by create Person error database")
    public void givenPersonToCreate_whenSave_ReturnErrorDatabase() {
        // given
        final Person personToCreate = this.domainsDataBuilder.getPersonDataBuilder().toCreate().build();
        doThrow(new RuntimeException()).when(this.personRepository).save(personToCreate);

        // then
        final CreatePersonDatabaseException error =
                assertThrows(CreatePersonDatabaseException.class, () -> this.personDatabaseGateway.create(personToCreate));
        assertEquals(error.getCode(), "wktechnology.agenciabancosangue.error.create");
        assertEquals(error.getMessage(), "Error to create Person.");
    }

    @Test
    @DisplayName("Should find by CPF successful")
    public void givenCPF_whenFind_thenReturnPerson() {
        // given
        final Person personFounded = this.domainsDataBuilder.getPersonDataBuilder().build();
        final String cpf = this.faker.internet().uuid();
        when(this.personRepository.findByCpf(cpf)).thenReturn(Optional.of(personFounded));

        // when
        final Optional<Person> response = this.personDatabaseGateway.findByCpf(cpf);

        // then
        assertResponse(personFounded, response.get());
    }

    @Test
    @DisplayName("Should find by CPF failed")
    public void givenCPF_whenFind_thenReturnError() {
        // given
        final String cpf = this.faker.internet().uuid();
        doThrow(new RuntimeException()).when(this.personRepository).findByCpf(cpf);

        // then
        final FindPersonByCpfDatabaseException error =
                assertThrows(FindPersonByCpfDatabaseException.class, () -> this.personDatabaseGateway.findByCpf(cpf));
        assertEquals(error.getCode(), "wktechnology.agenciabancosangue.database.error.findbycpf");
        assertEquals(error.getMessage(), "Error to find Person by cpf.");
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
}
