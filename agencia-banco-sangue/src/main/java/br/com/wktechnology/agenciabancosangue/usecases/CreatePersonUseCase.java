package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.domains.converters.PersonConverter;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.PersonDatabaseGateway;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.CreatePersonRequestJson;
import br.com.wktechnology.agenciabancosangue.usecases.exceptions.PersonAlreadyExistsWithCpfException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CreatePersonUseCase {

    @Autowired
    private PersonDatabaseGateway personDatabaseGateway;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private FindPersonByCpfUseCase findPersonByCpfUseCase;

    public Person create(final CreatePersonRequestJson createPersonRequestJson) {
        log.info("createPersonRequestJson: {}", createPersonRequestJson);
        var cpfToFind = createPersonRequestJson.getCpf()
                .replaceAll("\\.", "").replaceAll("-", "");
        final Optional<Person> personOptional = this.findPersonByCpfUseCase.find(cpfToFind);
        this.checkPersonAlreadyExists(personOptional);
        Person personToCreate = this.personConverter.createPersonRequestJsonToPerson(createPersonRequestJson);
        return this.personDatabaseGateway.create(personToCreate);
    }

    private void checkPersonAlreadyExists(final Optional<Person> personOptional) {
        if (personOptional.isPresent()) throw new PersonAlreadyExistsWithCpfException();
    }
}
