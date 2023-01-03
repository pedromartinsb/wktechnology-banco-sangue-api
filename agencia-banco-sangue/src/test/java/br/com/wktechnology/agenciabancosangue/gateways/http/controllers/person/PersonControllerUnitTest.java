package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person;

import br.com.wktechnology.agenciabancosangue.domains.*;
import br.com.wktechnology.agenciabancosangue.domains.enums.Gender;
import br.com.wktechnology.agenciabancosangue.domains.enums.States;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.*;
import br.com.wktechnology.agenciabancosangue.usecases.*;
import br.com.wktechnology.agenciabancosangue.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PersonControllerUnitTest extends BaseTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private CreatePersonUseCase createPersonUseCase;

    @Mock
    private FindCandidatesPerStateUseCase findCandidatesPerStateUseCase;

    @Mock
    private CalculateIMCUseCase calculateIMCUseCase;

    @Mock
    private GetObesePercentageUseCase getObesePercentageUseCase;

    @Mock
    private BloodTypeAgeAverageUseCase bloodTypeAgeAverageUseCase;

    @Mock
    private PossibleDonationsByReceptorBloodTypeUseCase possibleDonationsByReceptorBloodTypeUseCase;

    @Test
    @DisplayName("Should create Person with success")
    public void givenListPerson_whenCreate_thenReturnVoid() {
        // given
        final List<CreatePersonRequestJson> requestJsonList = new ArrayList<>();
        final CreatePersonRequestJson createPersonRequestJson = getCreatePersonRequestJson();
        requestJsonList.add(createPersonRequestJson);
        final Person personCreated = this.domainsDataBuilder.getPersonDataBuilder().build();
        when(this.createPersonUseCase.create(any(CreatePersonRequestJson.class))).thenReturn(personCreated);

        // when
        this.personController.create(requestJsonList);

        // then
        verify(this.createPersonUseCase,
                VerificationModeFactory.times(1)).create(createPersonRequestJson);
    }

    @Test
    @DisplayName("Should by return number of candidates per state")
    public void givenAny_whenFindCandidates_thenReturnListOfCandidatesPerState() {
        // given
        final List<Candidates> candidates = new ArrayList<>();
        final Candidates candidatesFoundedAC = Candidates.builder().number(10).state(States.AC).build();
        final Candidates candidatesFoundedAL = Candidates.builder().number(21).state(States.AL).build();
        candidates.add(candidatesFoundedAC);
        candidates.add(candidatesFoundedAL);
        final FindCandidatesResponseJson findCandidatesResponseJson = FindCandidatesResponseJson
                .builder()
                .persons(candidates)
                .build();
        when(this.findCandidatesPerStateUseCase.find()).thenReturn(candidates);

        // when
        final FindCandidatesResponseJson response = this.personController.findCandidatesPerState();

        // then
        verify(this.findCandidatesPerStateUseCase,VerificationModeFactory.times(1)).find();
        assertEquals(findCandidatesResponseJson.getPersons(), response.getPersons());
    }

    @Test
    @DisplayName("Should by return calculate imc")
    public void givenAny_whenCalculateIMC_thenReturnListOfIMCs() {
        // given
        final List<IMC> imcs = new ArrayList<>();
        IMC imc = IMC.builder().imc(30.0).smallerGap(5).graterGap(10).build();
        imcs.add(imc);
        final CalculateIMCResponseJson calculateIMCResponseJson = CalculateIMCResponseJson
                .builder()
                .imcs(imcs)
                .build();
        when(this.calculateIMCUseCase.calculate()).thenReturn(imcs);

        // when
        final CalculateIMCResponseJson response = this.personController.calculateIMC();

        // then
        verify(this.calculateIMCUseCase,VerificationModeFactory.times(1)).calculate();
        assertEquals(calculateIMCResponseJson.getImcs(), response.getImcs());
    }

    @Test
    @DisplayName("Should by return obesity percentage")
    public void givenAny_whenObesePercentage_thenReturnListOfObsess() {
        // given
        final List<ObesePercentage> obesePercentageList = new ArrayList<>();
        ObesePercentage obesePercentage = new ObesePercentage();
        obesePercentage.setPercentage(17.5F);
        obesePercentage.setGender(Gender.MASCULINO);
        obesePercentageList.add(obesePercentage);
        final ObesePercentageResponseJson obesePercentageResponseJson = ObesePercentageResponseJson
                .builder()
                .obesePercentages(obesePercentageList)
                .build();
        when(this.getObesePercentageUseCase.get()).thenReturn(obesePercentageResponseJson);

        // when
        final ObesePercentageResponseJson response = this.personController.getObesePercentage();

        // then
        verify(this.getObesePercentageUseCase,VerificationModeFactory.times(1)).get();
        assertEquals(obesePercentageResponseJson.getObesePercentages(), response.getObesePercentages());
    }

    @Test
    @DisplayName("Should by return an average age by blood type")
    public void givenAny_whenBloodTypeAgeAverage_thenReturnListOfBloodTypeAgeAverage() {
        // given
        final List<BloodTypeAgeAverageResponseJson> averageResponseJsonList = new ArrayList<>();
        final BloodTypeAgeAverageResponseJson bloodTypeAgeAverageResponseJson = BloodTypeAgeAverageResponseJson
                .builder()
                .average(14)
                .bloodType("A+")
                .build();
        averageResponseJsonList.add(bloodTypeAgeAverageResponseJson);
        when(this.bloodTypeAgeAverageUseCase.get()).thenReturn(averageResponseJsonList);

        // when
        final List<BloodTypeAgeAverageResponseJson> response = this.personController.getBloodTypeAgeAverage();

        // then
        verify(this.bloodTypeAgeAverageUseCase,VerificationModeFactory.times(1)).get();
        assertEquals(averageResponseJsonList.size(), response.size());
        assertEquals(averageResponseJsonList.isEmpty(), Boolean.FALSE);
    }

    @Test
    @DisplayName("Should by return possible donations by receptor blood type")
    public void givenAny_whenPossibleDonationsByReceptorBloodType_thenReturnList() {
        // given
        final List<PossibleDonation> possibleDonationList = new ArrayList<>();
        PossibleDonation possibleDonation = PossibleDonation
                .builder()
                .bloodType("O-")
                .quantity(10)
                .personList(new ArrayList<>())
                .build();
        possibleDonationList.add(possibleDonation);
        final PossibleDonationsResponseJson possibleDonationsResponseJson = PossibleDonationsResponseJson
                .builder()
                .possibleDonations(possibleDonationList)
                .build();
        when(this.possibleDonationsByReceptorBloodTypeUseCase.get()).thenReturn(possibleDonationsResponseJson);

        // when
        final PossibleDonationsResponseJson response = this.personController.getPossibleDonationsByReceptorBloodType();

        // then
        verify(this.possibleDonationsByReceptorBloodTypeUseCase,VerificationModeFactory.times(1)).get();
        assertEquals(possibleDonationsResponseJson.getPossibleDonations(), response.getPossibleDonations());
        assertEquals(possibleDonationList.isEmpty(), Boolean.FALSE);
    }

    private CreatePersonRequestJson getCreatePersonRequestJson() {
        return CreatePersonRequestJson
                .builder()
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
                .imc(81.0)
                .build();
    }
}
