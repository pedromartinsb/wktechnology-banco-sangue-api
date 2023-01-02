package br.com.wktechnology.agenciabancosangue.databuilders.domains;

import br.com.wktechnology.agenciabancosangue.databuilders.DataBuilderBase;
import br.com.wktechnology.agenciabancosangue.domains.Person;

public class PersonDataBuilder extends DataBuilderBase<Person> {
    private Long id;

    public PersonDataBuilder() {
        this.id = 1L;
    }

    public PersonDataBuilder toCreate() {
        this.id = null;
        return this;
    }

    public Person build() {
        return Person
                .builder()
                .id(id)
                .name(this.faker.name().fullName())
                .cpf(this.faker.internet().uuid())
                .rg(this.faker.internet().uuid())
                .birthDate(this.faker.date().toString())
                .gender(this.faker.internet().uuid())
                .mother(this.faker.funnyName().name())
                .father(this.faker.funnyName().name())
                .email(this.faker.bothify("????##@gmail.com"))
                .cep(this.faker.internet().uuid())
                .street(this.faker.address().streetAddress())
                .number(123)
                .neighborhood(this.faker.address().fullAddress())
                .city(this.faker.address().city())
                .state(this.faker.address().state())
                .phone(this.faker.phoneNumber().phoneNumber())
                .cellphone(this.faker.phoneNumber().cellPhone())
                .height(1.83)
                .weight(81)
                .bloodType(this.faker.dog().name())
                .build();
    }
}
