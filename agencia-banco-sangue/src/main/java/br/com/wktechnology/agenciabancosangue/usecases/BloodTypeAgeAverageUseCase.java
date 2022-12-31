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
        BloodTypeAgeAverageResponseJson aPlusBloodTypeAgeAverageResponseJson = getaPlusBloodTypeAgeAverageResponseJson();
        BloodTypeAgeAverageResponseJson aNegativeBloodTypeAgeAverageResponseJson = getaNegativeBloodTypeAgeAverageResponseJson();
        BloodTypeAgeAverageResponseJson bPlusBloodTypeAgeAverageResponseJson = getbPlusBloodTypeAgeAverageResponseJson();
        BloodTypeAgeAverageResponseJson bNegativeBloodTypeAgeAverageResponseJson = getbNegativeBloodTypeAgeAverageResponseJson();
        BloodTypeAgeAverageResponseJson oPlusBloodTypeAgeAverageResponseJson = getoPlusBloodTypeAgeAverageResponseJson();
        BloodTypeAgeAverageResponseJson oNegativeBloodTypeAgeAverageResponseJson = getoNegativeBloodTypeAgeAverageResponseJson();
        BloodTypeAgeAverageResponseJson abPlusBloodTypeAgeAverageResponseJson = getAbPlusBloodTypeAgeAverageResponseJson();
        BloodTypeAgeAverageResponseJson abNegativeBloodTypeAgeAverageResponseJson = getAbNegativeBloodTypeAgeAverageResponseJson();

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

    private BloodTypeAgeAverageResponseJson getAbNegativeBloodTypeAgeAverageResponseJson() {
        List<Person> bloodTypeABNegative = this.getPersonByBloodType.get(BloodType.AB_NEGATIVE.getName());
        int abNegative = (int) bloodTypeABNegative.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        return getBloodTypeAgeAverageResponseJson(abNegative, BloodType.AB_NEGATIVE.getDescription());
    }

    private BloodTypeAgeAverageResponseJson getAbPlusBloodTypeAgeAverageResponseJson() {
        List<Person> bloodTypeABPlus = this.getPersonByBloodType.get(BloodType.AB_PLUS.getName());
        int abPlus = (int) bloodTypeABPlus.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        return getBloodTypeAgeAverageResponseJson(abPlus, BloodType.AB_PLUS.getDescription());
    }

    private BloodTypeAgeAverageResponseJson getoNegativeBloodTypeAgeAverageResponseJson() {
        List<Person> bloodTypeONegative = this.getPersonByBloodType.get(BloodType.O_NEGATIVE.getName());
        int oNegative = (int) bloodTypeONegative.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        return getBloodTypeAgeAverageResponseJson(oNegative, BloodType.O_NEGATIVE.getDescription());
    }

    private BloodTypeAgeAverageResponseJson getoPlusBloodTypeAgeAverageResponseJson() {
        List<Person> bloodTypeOPlus = this.getPersonByBloodType.get(BloodType.O_PLUS.getName());
        int oPlus = (int) bloodTypeOPlus.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        return getBloodTypeAgeAverageResponseJson(oPlus, BloodType.O_PLUS.getDescription());
    }

    private BloodTypeAgeAverageResponseJson getbNegativeBloodTypeAgeAverageResponseJson() {
        List<Person> bloodTypeBNegative = this.getPersonByBloodType.get(BloodType.B_NEGATIVE.getName());
        int bNegative = (int) bloodTypeBNegative.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        return getBloodTypeAgeAverageResponseJson(bNegative, BloodType.B_NEGATIVE.getDescription());
    }

    private BloodTypeAgeAverageResponseJson getbPlusBloodTypeAgeAverageResponseJson() {
        List<Person> bloodTypeBPlus = this.getPersonByBloodType.get(BloodType.B_PLUS.getName());
        int bPlus = (int) bloodTypeBPlus.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        return getBloodTypeAgeAverageResponseJson(bPlus, BloodType.B_PLUS.getDescription());
    }

    private BloodTypeAgeAverageResponseJson getaNegativeBloodTypeAgeAverageResponseJson() {
        List<Person> bloodTypeANegative = this.getPersonByBloodType.get(BloodType.A_NEGATIVE.getName());
        int aNegative = (int) bloodTypeANegative.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        return getBloodTypeAgeAverageResponseJson(aNegative, BloodType.A_NEGATIVE.getDescription());
    }

    private BloodTypeAgeAverageResponseJson getaPlusBloodTypeAgeAverageResponseJson() {
        List<Person> bloodTypeAPlus = this.getPersonByBloodType.get(BloodType.A_PLUS.getName());
        int aPlus = (int) bloodTypeAPlus.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        return getBloodTypeAgeAverageResponseJson(aPlus, BloodType.A_PLUS.getDescription());
    }

    private BloodTypeAgeAverageResponseJson getBloodTypeAgeAverageResponseJson(final int aPlus, final String description) {
        BloodTypeAgeAverageResponseJson aPlusBloodTypeAgeAverageResponseJson = new BloodTypeAgeAverageResponseJson();
        aPlusBloodTypeAgeAverageResponseJson.setBloodType(description);
        aPlusBloodTypeAgeAverageResponseJson.setAverage(aPlus);
        return aPlusBloodTypeAgeAverageResponseJson;
    }
}
