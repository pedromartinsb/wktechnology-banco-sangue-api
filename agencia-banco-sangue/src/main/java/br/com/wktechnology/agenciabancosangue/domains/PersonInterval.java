package br.com.wktechnology.agenciabancosangue.domains;

import lombok.Data;

@Data
public class PersonInterval {

    private Person person;

    private Integer interval;

    private Double imc;
}
