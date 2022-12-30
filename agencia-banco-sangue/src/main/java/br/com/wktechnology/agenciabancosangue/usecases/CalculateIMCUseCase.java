package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.IMC;
import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.domains.PersonInterval;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CalculateIMCUseCase {

    @Autowired
    private FindAgeByBirthDateUseCase findAgeByBirthDateUseCase;

    @Autowired
    private UpdateIMCForPerson updateIMCForPerson;

    @Autowired
    private GetPersonUseCase getPersonUseCase;

    public List<IMC> calculate() {
        // buscar pessoas
        List<Person> persons = this.getPersonUseCase.findAll();

        // descobrir idade das pessoas
        List<Integer> ages = findAges(persons);

        // buscar a maior idade dentre as pessoas
        int oldestPerson = getOldestPerson(ages);

        // buscar o intervalo de idades de 10 em 10 anos
        List<Integer> agesIntervals = getAgesIntervals(oldestPerson);

        // criar uma nova lista com as pessoas separadas por idades
        List<PersonInterval> personIntervalList = getPersonIntervalList(persons, agesIntervals);

        // pegar a média do imc de cada intervalo
        return fillIMCListToReturn(agesIntervals, personIntervalList);
    }

    private List<IMC> fillIMCListToReturn(List<Integer> agesIntervals, List<PersonInterval> personIntervalList) {
        List<IMC> imcToReturn = new ArrayList<>();
        for (Integer interval : agesIntervals) {
            List<PersonInterval> personIntervals = personIntervalList
                    .stream().filter(p -> p.getInterval().equals(interval)).collect(Collectors.toList());

            double total = 0.0;
            for (PersonInterval personInterval : personIntervals) {
                total += personInterval.getImc();
            }

            double average = 0.0;
            if (!personIntervals.isEmpty()) {
                average = total / personIntervals.size();
            }

            IMC imc = new IMC();
            imc.setImc(average);
            imc.setSmallerGap(interval);
            imc.setGraterGap(interval+10);
            imcToReturn.add(imc);
        }
        return imcToReturn;
    }

    private List<PersonInterval> getPersonIntervalList(List<Person> persons, List<Integer> agesIntervals) {
        List<PersonInterval> personIntervalList = new ArrayList<>();

        for (Person person : persons) {
            for (Integer ageInterval : agesIntervals) {

                // encontrar o intervalo da pessoa
                var age = person.getAge();
                if (age >= ageInterval && age < ageInterval+10) {
                    var weight = person.getWeight();
                    var height = person.getHeight();
                    var imc = weight / (height * height);

                    PersonInterval personInterval = new PersonInterval();
                    personInterval.setPerson(person);
                    personInterval.setInterval(ageInterval);
                    personInterval.setImc(imc);
                    personIntervalList.add(personInterval);
                    this.updateIMCForPerson.update(person, imc);
                }

            }
        }
        return personIntervalList;
    }

    private List<Integer> getAgesIntervals(int oldestPerson) {
        var count = 0;
        List<Integer> intervals = new ArrayList<>();

        // vai incrementando de 5 em 5 até chegar no intervalo da idade máxima
        while (count <= oldestPerson) {
            var firstInterval = count;
            if (firstInterval == 0) intervals.add(firstInterval);
            var lastInterval = count + 10;
            intervals.add(lastInterval);
            count = lastInterval;
        }
        return intervals;
    }

    private int getOldestPerson(List<Integer> ages) {
        var oldestPerson = 0;
        for (Integer age : ages) {
            if (oldestPerson < age) oldestPerson = age;
        }
        return oldestPerson;
    }

    private List<Integer> findAges(List<Person> persons) {
        List<Integer> ages = new ArrayList<>();
        for (Person person : persons) {
            var age = this.findAgeByBirthDateUseCase.find(person.getBirthDate());
            person.setAge(age);
            ages.add(age);
        }
        return ages;
    }
}
