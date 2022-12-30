package br.com.wktechnology.agenciabancosangue.domains.converters;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.CreatePersonRequestJson;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

    public Person createPersonRequestJsonToPerson(final CreatePersonRequestJson createPersonRequestJson) {
        return Person
                .builder()
                .birthDate(createPersonRequestJson.getBirthDate())
                .bloodType(createPersonRequestJson.getBloodType())
                .cellphone(createPersonRequestJson.getCellphone())
                .cep(createPersonRequestJson.getCep().replaceAll("-", ""))
                .city(createPersonRequestJson.getCity())
                .cpf(createPersonRequestJson.getCpf().replaceAll("\\.", "").replaceAll("-", ""))
                .email(createPersonRequestJson.getEmail())
                .father(createPersonRequestJson.getFather())
                .gender(createPersonRequestJson.getGender())
                .height(createPersonRequestJson.getHeight())
                .imc(createPersonRequestJson.getImc())
                .mother(createPersonRequestJson.getMother())
                .name(createPersonRequestJson.getName())
                .neighborhood(createPersonRequestJson.getNeighborhood())
                .number(createPersonRequestJson.getNumber())
                .rg(createPersonRequestJson.getRg().replaceAll("\\.", "").replaceAll("-", ""))
                .phone(createPersonRequestJson.getPhone())
                .state(createPersonRequestJson.getState())
                .street(createPersonRequestJson.getStreet())
                .weight(createPersonRequestJson.getWeight())
                .build();
    }
}
