package br.com.wktechnology.agenciabancosangue.gateways.database.person.mysql;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.PersonDatabaseGateway;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.mysql.repository.PersonRepository;
import br.com.wktechnology.agenciabancosangue.gateways.exceptions.CreatePersonDatabaseException;
import br.com.wktechnology.agenciabancosangue.gateways.exceptions.FindPersonByCpfDatabaseException;
import br.com.wktechnology.agenciabancosangue.gateways.exceptions.UpdatePersonDatabaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class PersonDatabaseGatewayImpl implements PersonDatabaseGateway {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person create(Person person) {
        try {
            return this.personRepository.save(person);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            throw new CreatePersonDatabaseException();
        }
    }

    @Override
    public Person update(Person person) {
        try {
            return this.personRepository.save(person);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            throw new UpdatePersonDatabaseException();
        }
    }

    @Override
    public Optional<Person> findByCpf(String cpf) {
        try {
            return this.personRepository.findByCpf(cpf);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            throw new FindPersonByCpfDatabaseException();
        }
    }

}
