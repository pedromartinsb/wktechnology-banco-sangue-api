package br.com.wktechnology.agenciabancosangue.gateways.database.person.mysql.repository;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByCpf(final String cpf);
}
