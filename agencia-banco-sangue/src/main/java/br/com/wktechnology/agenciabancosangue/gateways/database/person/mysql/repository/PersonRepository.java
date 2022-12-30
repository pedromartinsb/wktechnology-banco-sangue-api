package br.com.wktechnology.agenciabancosangue.gateways.database.person.mysql.repository;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
