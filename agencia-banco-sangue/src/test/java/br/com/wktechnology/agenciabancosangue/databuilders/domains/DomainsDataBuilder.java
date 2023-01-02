package br.com.wktechnology.agenciabancosangue.databuilders.domains;

import lombok.Getter;

@Getter
public class DomainsDataBuilder {

    private final PersonDataBuilder personDataBuilder;

    public DomainsDataBuilder() {
        this.personDataBuilder = new PersonDataBuilder();
    }
}
