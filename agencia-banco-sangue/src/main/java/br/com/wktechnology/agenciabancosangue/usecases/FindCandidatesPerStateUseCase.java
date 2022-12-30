package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.domains.enums.States;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.PersonDatabaseGateway;
import br.com.wktechnology.agenciabancosangue.domains.FindCandidates;
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

    public List<FindCandidates> find() {
        List<Person> candidates = this.getPersonUseCase.findAll();
        List<FindCandidates> findCandidatesReturn = new ArrayList<>();
        List<String> candidatesStates = candidates.stream()
                .map(Person::getState).collect(Collectors.toList());

        for (States state : States.values()) {
            fillCandidatesList(findCandidatesReturn, candidatesStates, state);
        }
        return findCandidatesReturn;
    }

    private void fillCandidatesList(
            final List<FindCandidates> findCandidatesReturn,
            final List<String> candidatesStates,
            final States state) {
        var count = Collections.frequency(candidatesStates, state.toString());
        FindCandidates findCandidates = new FindCandidates();
        findCandidates.setNumber(count);
        findCandidates.setState(state);
        findCandidatesReturn.add(findCandidates);
    }
}
