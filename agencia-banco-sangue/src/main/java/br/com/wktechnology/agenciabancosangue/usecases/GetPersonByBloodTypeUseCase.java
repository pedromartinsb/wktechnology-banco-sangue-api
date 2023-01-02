package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GetPersonByBloodTypeUseCase {

    @Autowired
    private GetPersonUseCase getPersonUseCase;

    public List<Person> get(final String bloodType) {
        log.info("bloodType: {}", bloodType);
        // buscar todas as pessoas
        List<Person> personList = this.getPersonUseCase.findAll();

        return personList
                .stream()
                .filter(p -> Objects.equals(bloodType, p.getBloodType()))
                .collect(Collectors.toList());
    }
}
