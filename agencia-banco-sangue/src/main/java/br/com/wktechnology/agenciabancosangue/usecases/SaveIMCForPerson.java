package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.PersonDatabaseGateway;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.CreatePersonRequestJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SaveIMCForPerson {

    @Autowired
    private PersonDatabaseGateway personDatabaseGateway;

    public void update(final CreatePersonRequestJson person, final double imc) {
        log.info("imc: {}", imc);
        person.setImc(imc);
        this.personDatabaseGateway.update(Person.class.cast(person));
    }
}
