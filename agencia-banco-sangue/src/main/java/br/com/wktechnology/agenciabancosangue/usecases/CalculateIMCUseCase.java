package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.IMC;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.CalculateIMCRequestJson;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.CreatePersonRequestJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Slf4j
@Service
public class CalculateIMCUseCase {

    @Autowired
    private FindAgeByBirthDateUseCase findAgeByBirthDateUseCase;

    @Autowired
    private UpdateIMCForPerson updateIMCForPerson;

    public List<IMC> calculate(final CalculateIMCRequestJson calculateIMCRequestJson) {
        log.info("calculateIMCRequestJson: {}", calculateIMCRequestJson);
        List<Integer> ages = getAges(calculateIMCRequestJson);
        var largest = getLargest(ages);
        List<Integer> intervals = getIntervals(largest);

        List<IMC> imcs = new ArrayList<>();
        getCalculateIMC(intervals, imcs, calculateIMCRequestJson);
        return imcs;
    }

    private void getCalculateIMC(
            final List<Integer> intervals,
            final List<IMC> imcs,
            final CalculateIMCRequestJson calculateIMCRequestJson) {
        for (int i = 0; i < intervals.size(); i++) {
            if (i != intervals.size()-1) {
                createCalculateIMCInstance(imcs, intervals.get(i), intervals.get(i+1), i, calculateIMCRequestJson);
            }
        }
    }

    private void createCalculateIMCInstance(
            final List<IMC> imcs,
            final Integer smallerGap,
            final Integer greaterGap,
            final int index,
            final CalculateIMCRequestJson calculateIMCRequestJson) {
        var imc = getIMCPerInterval(calculateIMCRequestJson, smallerGap, greaterGap, index);

        IMC IMC = new IMC();
        IMC.setImc(imc);
        IMC.setSmallerGap(smallerGap);
        IMC.setGraterGap(greaterGap);
        imcs.add(IMC);
    }

    private Double getIMCPerInterval(
            final CalculateIMCRequestJson calculateIMCRequestJson,
            final Integer smallerGap,
            final Integer greaterGap,
            final Integer index) {

        List<Double> imcs = new ArrayList<>();

        for (CreatePersonRequestJson person : calculateIMCRequestJson.getPersons()) {
            var age = person.getAge();
            if (age >= smallerGap && age < greaterGap) {
                var weight = calculateIMCRequestJson.getPersons().get(index).getWeight();
                var height = calculateIMCRequestJson.getPersons().get(index).getHeight();
                var imc = weight / (height * height);

                //TODO: salvar o imc da pessoa no banco de dados
                this.updateIMCForPerson.update(person, imc);

                imcs.add(imc);
            }
        }

        OptionalDouble average = imcs.stream().mapToDouble(a -> a).average();
        return average.isPresent() ? average.getAsDouble() : 0.0;
    }

    private List<Integer> getAges(final CalculateIMCRequestJson calculateIMCRequestJson) {
        List<Integer> ages = new ArrayList<>();
        for (CreatePersonRequestJson person : calculateIMCRequestJson.getPersons()) {
            var age = this.findAgeByBirthDateUseCase.find(person.getBirthDate());
            person.setAge(age);
            ages.add(age);
        }
        return ages;
    }

    private int getLargest(final List<Integer> ages) {
        int max = 0;

        // descobre a maior idade dentro da lista de idades
        for (Integer age : ages) {
            if (max < age) max = age;
        }
        return max;
    }

    private List<Integer> getIntervals(final int max) {
        var count = 0;
        List<Integer> intervals = new ArrayList<>();

        // vai incrementando de 5 em 5 até chegar no intervalo da idade máxima
        while (count <= max) {
            var firstInterval = count;
            if (firstInterval == 0) intervals.add(firstInterval);
            var lastInterval = count + 5;
            intervals.add(lastInterval);
            count = lastInterval;
        }
        return intervals;
    }
}
