package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.ObesePercentage;
import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.domains.enums.Gender;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.ObesePercentageResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GetObesePercentageUseCase {

    @Autowired
    private GetPersonUseCase getPersonUseCase;

    public ObesePercentageResponseJson get() {
        // buscar todas as pessoas
        List<Person> personList = this.getPersonUseCase.findAll();

        // dividir em duas listas (masculino, feminino)
        ObesePercentage maleObesePercentage = getMaleObesePercentage(personList);
        ObesePercentage femalesObesePercentage = getFemalesObesePercentage(personList);

        return getObesePercentageResponseJson(maleObesePercentage, femalesObesePercentage);
    }

    private ObesePercentageResponseJson getObesePercentageResponseJson(ObesePercentage maleObesePercentage, ObesePercentage femalesObesePercentage) {
        ObesePercentageResponseJson obesePercentageResponseJson = new ObesePercentageResponseJson();
        List<ObesePercentage> percentageList = new ArrayList<>();
        percentageList.add(maleObesePercentage);
        percentageList.add(femalesObesePercentage);
        obesePercentageResponseJson.setObesePercentages(percentageList);
        return obesePercentageResponseJson;
    }

    private ObesePercentage getFemalesObesePercentage(List<Person> personList) {
        List<Person> femalesObese = personList
                .stream()
                .filter(p ->
                        Objects.equals(Gender.FEMININO.getName(), p.getGender())
                                && p.getImc() > 30)
                .collect(Collectors.toList());
        List<Person> femalesNotObese = personList
                .stream()
                .filter(p ->
                        Objects.equals(Gender.FEMININO.getName(), p.getGender()))
                .collect(Collectors.toList());
        float femalesPercentage = (float) ((femalesObese.size()) * 100) / femalesNotObese.size();
        ObesePercentage femalesObesePercentage = new ObesePercentage();
        femalesObesePercentage.setGender(Gender.FEMININO);
        femalesObesePercentage.setPercentage(femalesPercentage);
        return femalesObesePercentage;
    }

    private ObesePercentage getMaleObesePercentage(List<Person> personList) {
        List<Person> malesObese = personList
                .stream()
                .filter(p ->
                        Objects.equals(Gender.MASCULINO.getName(), p.getGender())
                                && p.getImc() > 30)
                .collect(Collectors.toList());
        List<Person> malesNotObese = personList
                .stream()
                .filter(p ->
                        Objects.equals(Gender.MASCULINO.getName(), p.getGender()))
                .collect(Collectors.toList());
        float malesPercentage = (float) ((malesObese.size()) * 100) / malesNotObese.size();
        ObesePercentage maleObesePercentage = new ObesePercentage();
        maleObesePercentage.setGender(Gender.MASCULINO);
        maleObesePercentage.setPercentage(malesPercentage);
        return maleObesePercentage;
    }
}
