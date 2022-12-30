package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.PersonDatabaseGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateIMCForPerson {

    @Autowired
    private PersonDatabaseGateway personDatabaseGateway;

    public void update(final Person person, final double imc) {
        log.info("imc: {}", imc);
        person.setImc(imc);
        this.personDatabaseGateway.update(person);
    }
}
