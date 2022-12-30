package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.domains.enums.States;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.PersonDatabaseGateway;
import br.com.wktechnology.agenciabancosangue.domains.Candidates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FindCandidatesPerStateUseCase {

    @Autowired
    private PersonDatabaseGateway personDatabaseGateway;

    @Autowired
    private GetPersonUseCase getPersonUseCase;

    public List<Candidates> find() {
        List<Person> candidates = this.getPersonUseCase.findAll();
        List<Candidates> candidatesReturn = new ArrayList<>();
        List<String> candidatesStates = candidates.stream()
                .map(Person::getState).collect(Collectors.toList());

        for (States state : States.values()) {
            fillCandidatesList(candidatesReturn, candidatesStates, state);
        }
        return candidatesReturn;
    }

    private void fillCandidatesList(
            final List<Candidates> candidatesReturn,
            final List<String> candidatesStates,
            final States state) {
        var count = Collections.frequency(candidatesStates, state.toString());
        Candidates candidates = new Candidates();
        candidates.setNumber(count);
        candidates.setState(state);
        candidatesReturn.add(candidates);
    }
}
