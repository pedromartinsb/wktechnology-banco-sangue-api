package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.BloodTypeAgeAverage;
import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.domains.enums.BloodType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BloodTypeAgeAverageUseCase {

    @Autowired
    private GetPersonByBloodTypeUseCase getPersonByBloodTypeUseCase;

    public List<BloodTypeAgeAverage> get() {
        // dividir em listas por tipo sanguineo
        var aPlusBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.A_PLUS.getName(), BloodType.A_PLUS.getDescription());
        var aNegativeBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.A_NEGATIVE.getName(), BloodType.A_NEGATIVE.getDescription());
        var bPlusBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.B_PLUS.getName(), BloodType.B_PLUS.getDescription());
        var bNegativeBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.B_NEGATIVE.getName(), BloodType.B_NEGATIVE.getDescription());
        var oPlusBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.O_PLUS.getName(), BloodType.O_PLUS.getDescription());
        var oNegativeBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.O_NEGATIVE.getName(), BloodType.O_NEGATIVE.getDescription());
        var abPlusBloodTypeAgeAverageResponseJson =
                getBloodTypeAgeAverageResponseJson(BloodType.AB_PLUS.getName(), BloodType.AB_PLUS.getDescription());
        var abNegativeBloodTypeAgeAverageResponseJson =
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

    private List<BloodTypeAgeAverage> getResponseJson(
            final BloodTypeAgeAverage aPlusBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverage aNegativeBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverage bPlusBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverage bNegativeBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverage oPlusBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverage oNegativeBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverage abPlusBloodTypeAgeAverageResponseJson,
            final BloodTypeAgeAverage abNegativeBloodTypeAgeAverageResponseJson) {
        List<BloodTypeAgeAverage> averageResponseJsonList = new ArrayList<>();
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

    private BloodTypeAgeAverage getBloodTypeAgeAverageResponseJson(
            final String name, final String description) {
        List<Person> bloodTypeList = this.getPersonByBloodTypeUseCase.get(name);
        var average = (int) bloodTypeList.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        return getBloodTypeAgeAverageResponseJson(average, description);
    }

    private BloodTypeAgeAverage getBloodTypeAgeAverageResponseJson(final int aPlus, final String description) {
        BloodTypeAgeAverage aPlusBloodTypeAgeAverageResponseJson = new BloodTypeAgeAverage();
        aPlusBloodTypeAgeAverageResponseJson.setBloodType(description);
        aPlusBloodTypeAgeAverageResponseJson.setAverage(aPlus);
        return aPlusBloodTypeAgeAverageResponseJson;
    }
}
