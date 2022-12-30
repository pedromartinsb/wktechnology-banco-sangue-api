package br.com.wktechnology.agenciabancosangue.gateways.database.person;


public interface PersonDatabaseGateway {

    Integer findCandidatesPerState(final String state);
}
