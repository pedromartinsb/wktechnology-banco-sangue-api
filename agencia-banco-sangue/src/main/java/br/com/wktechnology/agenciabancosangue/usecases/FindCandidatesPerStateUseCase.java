package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.gateways.database.person.PersonDatabaseGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FindCandidatesPerStateUseCase {

    @Autowired
    private PersonDatabaseGateway personDatabaseGateway;

    public Integer find(final String state) {
        log.info("state: {}", state);
        return this.personDatabaseGateway.findCandidatesPerState(state);
    }
}
