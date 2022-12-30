package br.com.wktechnology.agenciabancosangue.gateways.database.person.mysql;

import br.com.wktechnology.agenciabancosangue.gateways.database.person.PersonDatabaseGateway;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.mysql.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonDatabaseGatewayImpl implements PersonDatabaseGateway {

    @Autowired
    private PersonRepository personRepository;

}
