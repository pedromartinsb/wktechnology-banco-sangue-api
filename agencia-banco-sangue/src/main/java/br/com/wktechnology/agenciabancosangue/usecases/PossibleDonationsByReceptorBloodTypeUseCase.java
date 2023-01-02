package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.Person;
import br.com.wktechnology.agenciabancosangue.domains.PossibleDonation;
import br.com.wktechnology.agenciabancosangue.domains.enums.BloodType;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.PossibleDonationsResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PossibleDonationsByReceptorBloodTypeUseCase {

    @Autowired
    private GetPersonUseCase getPersonUseCase;

    public PossibleDonationsResponseJson get() {
        // buscar todas as pessoas
        List<Person> personList = this.getPersonUseCase.findAll();

        // idade entre 16 a 69 anos
        // peso acima de 50kg
        List<Person> possibleCandidates = getPossibleCandidates(personList);
        log.info("possibleCandidates: {}", possibleCandidates);

        // lista por tipo sanguineo
        PossibleDonation aPlusPossibleDonation = getaPlusPossibleDonation(possibleCandidates);
        PossibleDonation aNegativePossibleDonation = getaNegativePossibleDonation(possibleCandidates);
        PossibleDonation bPlusPossibleDonation = getbPlusPossibleDonation(possibleCandidates);
        PossibleDonation bNegativePossibleDonation = getbNegativePossibleDonation(possibleCandidates);
        PossibleDonation abPlusPossibleDonation = getAbPlusPossibleDonation(possibleCandidates);
        PossibleDonation abNegativePossibleDonation = getAbNegativePossibleDonation(possibleCandidates);
        PossibleDonation oPlusPossibleDonation = getoPlusPossibleDonation(possibleCandidates);
        PossibleDonation oNegativePossibleDonation = getoNegativePossibleDonation(possibleCandidates);

        return getPossibleDonationsResponseJson(
                aPlusPossibleDonation, aNegativePossibleDonation,
                bPlusPossibleDonation, bNegativePossibleDonation, abPlusPossibleDonation,
                abNegativePossibleDonation, oPlusPossibleDonation, oNegativePossibleDonation);
    }

    private PossibleDonationsResponseJson getPossibleDonationsResponseJson(
            final PossibleDonation aPlusPossibleDonation, final PossibleDonation aNegativePossibleDonation,
            final PossibleDonation bPlusPossibleDonation, final PossibleDonation bNegativePossibleDonation,
            final PossibleDonation abPlusPossibleDonation, final PossibleDonation abNegativePossibleDonation,
            final PossibleDonation oPlusPossibleDonation, final PossibleDonation oNegativePossibleDonation) {
        PossibleDonationsResponseJson possibleDonationsResponseJson = new PossibleDonationsResponseJson();
        List<PossibleDonation> possibleDonations = new ArrayList<>();
        possibleDonations.add(aPlusPossibleDonation);
        possibleDonations.add(aNegativePossibleDonation);
        possibleDonations.add(bPlusPossibleDonation);
        possibleDonations.add(bNegativePossibleDonation);
        possibleDonations.add(abPlusPossibleDonation);
        possibleDonations.add(abNegativePossibleDonation);
        possibleDonations.add(oPlusPossibleDonation);
        possibleDonations.add(oNegativePossibleDonation);

        possibleDonationsResponseJson.setPossibleDonations(possibleDonations);
        return possibleDonationsResponseJson;
    }

    private PossibleDonation getoNegativePossibleDonation(final List<Person> possibleCandidates) {
        var oNegativeBloodType = possibleCandidates
                .stream()
                .filter(p -> Objects.equals(p.getBloodType(), BloodType.O_NEGATIVE.getName()))
                .collect(Collectors.toList());
        PossibleDonation oNegativePossibleDonation = new PossibleDonation();
        oNegativePossibleDonation.setPersonList(oNegativeBloodType);
        oNegativePossibleDonation.setBloodType(BloodType.O_NEGATIVE.getDescription());
        oNegativePossibleDonation.setQuantity(oNegativeBloodType.size());
        return oNegativePossibleDonation;
    }

    private PossibleDonation getoPlusPossibleDonation(final List<Person> possibleCandidates) {
        var oPlusBloodType = possibleCandidates
                .stream()
                .filter(p -> Objects.equals(p.getBloodType(), BloodType.O_PLUS.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.O_NEGATIVE.getName()))
                .collect(Collectors.toList());
        PossibleDonation oPlusPossibleDonation = new PossibleDonation();
        oPlusPossibleDonation.setPersonList(oPlusBloodType);
        oPlusPossibleDonation.setBloodType(BloodType.O_PLUS.getDescription());
        oPlusPossibleDonation.setQuantity(oPlusBloodType.size());
        return oPlusPossibleDonation;
    }

    private PossibleDonation getAbNegativePossibleDonation(final List<Person> possibleCandidates) {
        var abNegativeBloodType = possibleCandidates
                .stream()
                .filter(p -> Objects.equals(p.getBloodType(), BloodType.A_NEGATIVE.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.B_NEGATIVE.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.AB_NEGATIVE.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.O_NEGATIVE.getName()))
                .collect(Collectors.toList());
        PossibleDonation abNegativePossibleDonation = new PossibleDonation();
        abNegativePossibleDonation.setPersonList(abNegativeBloodType);
        abNegativePossibleDonation.setBloodType(BloodType.AB_NEGATIVE.getDescription());
        abNegativePossibleDonation.setQuantity(abNegativeBloodType.size());
        return abNegativePossibleDonation;
    }

    private PossibleDonation getAbPlusPossibleDonation(final List<Person> possibleCandidates) {
        PossibleDonation abPlusPossibleDonation = new PossibleDonation();
        abPlusPossibleDonation.setPersonList(possibleCandidates);
        abPlusPossibleDonation.setBloodType(BloodType.AB_PLUS.getDescription());
        abPlusPossibleDonation.setQuantity(possibleCandidates.size());
        return abPlusPossibleDonation;
    }

    private PossibleDonation getbNegativePossibleDonation(final List<Person> possibleCandidates) {
        var bNegativeBloodType = possibleCandidates
                .stream()
                .filter(p -> Objects.equals(p.getBloodType(), BloodType.B_NEGATIVE.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.O_NEGATIVE.getName()))
                .collect(Collectors.toList());
        PossibleDonation bNegativePossibleDonation = new PossibleDonation();
        bNegativePossibleDonation.setPersonList(bNegativeBloodType);
        bNegativePossibleDonation.setBloodType(BloodType.B_NEGATIVE.getDescription());
        bNegativePossibleDonation.setQuantity(bNegativeBloodType.size());
        return bNegativePossibleDonation;
    }

    private PossibleDonation getbPlusPossibleDonation(final List<Person> possibleCandidates) {
        var bPlusBloodType = possibleCandidates
                .stream()
                .filter(p -> Objects.equals(p.getBloodType(), BloodType.B_PLUS.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.B_NEGATIVE.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.O_PLUS.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.O_NEGATIVE.getName()))
                .collect(Collectors.toList());
        PossibleDonation bPlusPossibleDonation = new PossibleDonation();
        bPlusPossibleDonation.setPersonList(bPlusBloodType);
        bPlusPossibleDonation.setBloodType(BloodType.B_PLUS.getDescription());
        bPlusPossibleDonation.setQuantity(bPlusBloodType.size());
        return bPlusPossibleDonation;
    }

    private PossibleDonation getaNegativePossibleDonation(final List<Person> possibleCandidates) {
        var aNegativeBloodType = possibleCandidates
                .stream()
                .filter(p -> Objects.equals(p.getBloodType(), BloodType.A_NEGATIVE.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.O_NEGATIVE.getName()))
                .collect(Collectors.toList());
        PossibleDonation aNegativePossibleDonation = new PossibleDonation();
        aNegativePossibleDonation.setPersonList(aNegativeBloodType);
        aNegativePossibleDonation.setBloodType(BloodType.A_NEGATIVE.getDescription());
        aNegativePossibleDonation.setQuantity(aNegativeBloodType.size());
        return aNegativePossibleDonation;
    }

    private PossibleDonation getaPlusPossibleDonation(final List<Person> possibleCandidates) {
        var aPlusBloodType = possibleCandidates
                .stream()
                .filter(p -> Objects.equals(p.getBloodType(), BloodType.A_PLUS.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.A_NEGATIVE.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.O_PLUS.getName()) ||
                        Objects.equals(p.getBloodType(), BloodType.O_NEGATIVE.getName()))
                .collect(Collectors.toList());
        PossibleDonation aPlusPossibleDonation = new PossibleDonation();
        aPlusPossibleDonation.setPersonList(aPlusBloodType);
        aPlusPossibleDonation.setBloodType(BloodType.A_PLUS.getDescription());
        aPlusPossibleDonation.setQuantity(aPlusBloodType.size());
        return aPlusPossibleDonation;
    }

    private List<Person> getPossibleCandidates(final List<Person> personList) {
        return personList
                .stream()
                .filter(p -> p.getAge() >= 16 && p.getAge() <= 69 && p.getWeight() >= 50)
                .collect(Collectors.toList());
    }
}
