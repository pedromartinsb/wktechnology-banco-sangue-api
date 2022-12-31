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
        List<Person> bloodTypeAPlus = this.getPersonByBloodType.get(BloodType.A_PLUS.getName());
        int aPlus = (int) bloodTypeAPlus.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        BloodTypeAgeAverageResponseJson aPlusBloodTypeAgeAverageResponseJson = new BloodTypeAgeAverageResponseJson();
        aPlusBloodTypeAgeAverageResponseJson.setBloodType(BloodType.A_PLUS.getDescription());
        aPlusBloodTypeAgeAverageResponseJson.setAverage(aPlus);

        List<Person> bloodTypeANegative = this.getPersonByBloodType.get(BloodType.A_NEGATIVE.getName());
        int aNegative = (int) bloodTypeANegative.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        BloodTypeAgeAverageResponseJson aNegativeBloodTypeAgeAverageResponseJson = new BloodTypeAgeAverageResponseJson();
        aNegativeBloodTypeAgeAverageResponseJson.setBloodType(BloodType.A_NEGATIVE.getDescription());
        aNegativeBloodTypeAgeAverageResponseJson.setAverage(aNegative);

        List<Person> bloodTypeBPlus = this.getPersonByBloodType.get(BloodType.B_PLUS.getName());
        int bPlus = (int) bloodTypeBPlus.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        BloodTypeAgeAverageResponseJson bPlusBloodTypeAgeAverageResponseJson = new BloodTypeAgeAverageResponseJson();
        bPlusBloodTypeAgeAverageResponseJson.setBloodType(BloodType.B_PLUS.getDescription());
        bPlusBloodTypeAgeAverageResponseJson.setAverage(bPlus);

        List<Person> bloodTypeBNegative = this.getPersonByBloodType.get(BloodType.B_NEGATIVE.getName());
        int bNegative = (int) bloodTypeBNegative.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        BloodTypeAgeAverageResponseJson bNegativeBloodTypeAgeAverageResponseJson = new BloodTypeAgeAverageResponseJson();
        bNegativeBloodTypeAgeAverageResponseJson.setBloodType(BloodType.B_NEGATIVE.getDescription());
        bNegativeBloodTypeAgeAverageResponseJson.setAverage(bNegative);

        List<Person> bloodTypeOPlus = this.getPersonByBloodType.get(BloodType.O_PLUS.getName());
        int oPlus = (int) bloodTypeOPlus.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        BloodTypeAgeAverageResponseJson oPlusBloodTypeAgeAverageResponseJson = new BloodTypeAgeAverageResponseJson();
        oPlusBloodTypeAgeAverageResponseJson.setBloodType(BloodType.O_PLUS.getDescription());
        oPlusBloodTypeAgeAverageResponseJson.setAverage(oPlus);

        List<Person> bloodTypeONegative = this.getPersonByBloodType.get(BloodType.O_NEGATIVE.getName());
        int oNegative = (int) bloodTypeONegative.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        BloodTypeAgeAverageResponseJson oNegativeBloodTypeAgeAverageResponseJson = new BloodTypeAgeAverageResponseJson();
        oNegativeBloodTypeAgeAverageResponseJson.setBloodType(BloodType.O_NEGATIVE.getDescription());
        oNegativeBloodTypeAgeAverageResponseJson.setAverage(oNegative);

        List<Person> bloodTypeABPlus = this.getPersonByBloodType.get(BloodType.AB_PLUS.getName());
        int abPlus = (int) bloodTypeABPlus.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        BloodTypeAgeAverageResponseJson abPlusBloodTypeAgeAverageResponseJson = new BloodTypeAgeAverageResponseJson();
        abPlusBloodTypeAgeAverageResponseJson.setBloodType(BloodType.AB_PLUS.getDescription());
        abPlusBloodTypeAgeAverageResponseJson.setAverage(abPlus);

        List<Person> bloodTypeABNegative = this.getPersonByBloodType.get(BloodType.AB_NEGATIVE.getName());
        int abNegative = (int) bloodTypeABNegative.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        BloodTypeAgeAverageResponseJson abNegativeBloodTypeAgeAverageResponseJson = new BloodTypeAgeAverageResponseJson();
        abNegativeBloodTypeAgeAverageResponseJson.setBloodType(BloodType.AB_NEGATIVE.getDescription());
        abNegativeBloodTypeAgeAverageResponseJson.setAverage(abNegative);

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
}
