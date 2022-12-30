package br.com.wktechnology.agenciabancosangue.gateways.database.person;

import br.com.wktechnology.agenciabancosangue.domains.Person;

import java.util.Optional;

public interface PersonDatabaseGateway {
    Person create(final Person person);

    Person update(final Person person);

    Optional<Person> findByCpf(final String cpf);
}
