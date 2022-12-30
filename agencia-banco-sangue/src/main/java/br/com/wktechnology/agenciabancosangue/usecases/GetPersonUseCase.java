package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.PersonDatabaseGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GetPersonUseCase {

    @Autowired
    private PersonDatabaseGateway personDatabaseGateway;

    public List<Person> findAll() {
        return this.personDatabaseGateway.findAll();
    }
}
