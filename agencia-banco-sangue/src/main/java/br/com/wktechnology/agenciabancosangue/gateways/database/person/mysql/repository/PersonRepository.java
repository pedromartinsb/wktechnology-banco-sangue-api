package br.com.wktechnology.agenciabancosangue.gateways.database.person.mysql.repository;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByState(final String state);
}
