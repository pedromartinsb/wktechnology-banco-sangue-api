package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.enums.States;
import br.com.wktechnology.agenciabancosangue.gateways.database.person.PersonDatabaseGateway;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.CreatePersonRequestJson;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.FindCandidatesJson;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.FindCandidatesRequestJson;
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

    public List<FindCandidatesJson> find(final FindCandidatesRequestJson findCandidatesRequestJson) {
        log.info("findCandidatesRequestJson: {}", findCandidatesRequestJson);
        List<FindCandidatesJson> findCandidatesReturn = new ArrayList<>();
        List<String> candidatesStates = findCandidatesRequestJson
                .getPersons().stream()
                .map(CreatePersonRequestJson::getState).collect(Collectors.toList());

        for (States state : States.values()) {
            fillCandidatesList(findCandidatesReturn, candidatesStates, state);
        }
        return findCandidatesReturn;
    }

    private void fillCandidatesList(List<FindCandidatesJson> findCandidatesReturn, List<String> candidatesStates, States state) {
        int count = Collections.frequency(candidatesStates, state.toString());
        FindCandidatesJson findCandidatesJson = new FindCandidatesJson();
        findCandidatesJson.setNumber(count);
        findCandidatesJson.setState(state);
        findCandidatesReturn.add(findCandidatesJson);
    }
}
