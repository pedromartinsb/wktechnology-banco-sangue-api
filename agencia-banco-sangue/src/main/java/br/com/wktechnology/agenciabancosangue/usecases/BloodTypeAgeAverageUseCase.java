package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.domains.enums.BloodType;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.BloodTypeAgeAverageResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BloodTypeAgeAverageUseCase {

    @Autowired
    private GetPersonByBloodType getPersonByBloodType;

    public List<BloodTypeAgeAverageResponseJson> get() {
        // dividir em listas por tipo sanguineo
        BloodTypeAgeAverageResponseJson aPlusBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.A_PLUS.getName(), BloodType.A_PLUS.getDescription());
        BloodTypeAgeAverageResponseJson aNegativeBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.A_NEGATIVE.getName(), BloodType.A_NEGATIVE.getDescription());
        BloodTypeAgeAverageResponseJson bPlusBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.B_PLUS.getName(), BloodType.B_PLUS.getDescription());
        BloodTypeAgeAverageResponseJson bNegativeBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.B_NEGATIVE.getName(), BloodType.B_NEGATIVE.getDescription());
        BloodTypeAgeAverageResponseJson oPlusBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.O_PLUS.getName(), BloodType.O_PLUS.getDescription());
        BloodTypeAgeAverageResponseJson oNegativeBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.O_NEGATIVE.getName(), BloodType.O_NEGATIVE.getDescription());
        BloodTypeAgeAverageResponseJson abPlusBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.AB_PLUS.getName(), BloodType.AB_PLUS.getDescription());
        BloodTypeAgeAverageResponseJson abNegativeBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.AB_NEGATIVE.getName(), BloodType.AB_NEGATIVE.getDescription());

        return getResponseJson(
                aPlusBloodTypeAgeAverageResponseJson,
                aNegativeBloodTypeAgeAverageResponseJson,
                bPlusBloodTypeAgeAverageResponseJson,
                bNegativeBloodTypeAgeAverageResponseJson,
                oPlusBloodTypeAgeAverageResponseJson,
                oNegativeBloodTypeAgeAverageResponseJson,
                abPlusBloodTypeAgeAverageResponseJson,
                abNegativeBloodTypeAgeAverageResponseJson);
    }

    private List<BloodTypeAgeAverageResponseJson> getResponseJson(
            final BloodTypeAgeAverageResponseJson aPlusBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverageResponseJson aNegativeBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverageResponseJson bPlusBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverageResponseJson bNegativeBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverageResponseJson oPlusBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverageResponseJson oNegativeBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverageResponseJson abPlusBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverageResponseJson abNegativeBloodTypeAgeAverageResponseJson) {
        List<BloodTypeAgeAverageResponseJson> averageResponseJsonList = new ArrayList<>();
        averageResponseJsonList.add(aPlusBloodTypeAgeAverageResponseJson);
        averageResponseJsonList.add(aNegativeBloodTypeAgeAverageResponseJson);
        averageResponseJsonList.add(bPlusBloodTypeAgeAverageResponseJson);
        averageResponseJsonList.add(bNegativeBloodTypeAgeAverageResponseJson);
        averageResponseJsonList.add(oPlusBloodTypeAgeAverageResponseJson);
        averageResponseJsonList.add(oNegativeBloodTypeAgeAverageResponseJson);
        averageResponseJsonList.add(abPlusBloodTypeAgeAverageResponseJson);
        averageResponseJsonList.add(abNegativeBloodTypeAgeAverageResponseJson);
        return averageResponseJsonList;
    }

    private BloodTypeAgeAverageResponseJson getBloodTypeAgeAverageResponseJson(
            final String name, final String description) {
        List<Person> bloodTypeList = this.getPersonByBloodType.get(name);
        int average = (int) bloodTypeList.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        return getBloodTypeAgeAverageResponseJson(average, description);
    }

    private BloodTypeAgeAverageResponseJson getBloodTypeAgeAverageResponseJson(final int aPlus, final String description) {
        BloodTypeAgeAverageResponseJson aPlusBloodTypeAgeAverageResponseJson = new BloodTypeAgeAverageResponseJson();
        aPlusBloodTypeAgeAverageResponseJson.setBloodType(description);
        aPlusBloodTypeAgeAverageResponseJson.setAverage(aPlus);
        return aPlusBloodTypeAgeAverageResponseJson;
    }
}
