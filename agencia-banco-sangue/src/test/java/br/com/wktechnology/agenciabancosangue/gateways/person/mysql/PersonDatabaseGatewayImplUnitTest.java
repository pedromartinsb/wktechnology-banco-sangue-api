package br.com.wktechnology.agenciabancosangue.gateways.person.mysql;

import br.com.wktechnology.agenciabancosangue.databuilders.utils.BaseTest;
import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.mysql.PersonDatabaseGatewayImpl;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.mysql.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
