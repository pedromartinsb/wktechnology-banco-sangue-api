package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person;

import br.com.wktechnology.agenciabancosangue.domains.IMC;
import br.com.wktechnology.agenciabancosangue.domains.Candidates;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.*;
import br.com.wktechnology.agenciabancosangue.usecases.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${baseurl.v1}/person")
@Api(tags = "Person", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private CreatePersonUseCase createPersonUseCase;

    @Autowired
    private FindCandidatesPerStateUseCase findCandidatesPerStateUseCase;

    @Autowired
    private CalculateIMCUseCase calculateIMCUseCase;

    @Autowired
    private GetObesePercentageUseCase getObesePercentageUseCase;

    @Autowired
    private BloodTypeAgeAverageUseCase bloodTypeAgeAverageUseCase;

    @Autowired
    private PossibleDonationsByReceptorBloodTypeUseCase possibleDonationsByReceptorBloodTypeUseCase;

    @ApiOperation(value = "Resource to Create candidates",
            response = CreatePersonResponseJson.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @Validated
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void create(
            final @RequestBody() @Valid List<CreatePersonRequestJson> listCreatePersonRequestJson) {
        listCreatePersonRequestJson.forEach(this.createPersonUseCase::create);
    }

    @ApiOperation(value = "Resource to Find number of Candidates per State",
            response = FindCandidatesResponseJson.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "FOUNDED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @Validated
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("find-candidates")
    public FindCandidatesResponseJson findCandidatesPerState() {
        List<Candidates> candidates = this.findCandidatesPerStateUseCase.find();
        return FindCandidatesResponseJson
                .builder()
                .persons(candidates)
                .build();
    }

    @ApiOperation(value = "Resource to calculate IMC of ten to ten years",
            response = CalculateIMCResponseJson.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "FOUNDED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @Validated
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("calculate-imc")
    public CalculateIMCResponseJson calculateIMC() {
        List<IMC> imcList = this.calculateIMCUseCase.calculate();
        return CalculateIMCResponseJson
                .builder()
                .imcs(imcList)
                .build();
    }

    @ApiOperation(value = "Resource to get obese percentage per gender",
            response = ObesePercentageResponseJson.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "FOUNDED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @Validated
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("obese-percentage")
    public ObesePercentageResponseJson getObesePercentage() {
        return this.getObesePercentageUseCase.get();
    }

    @ApiOperation(value = "Resource to get an average age by blood type",
            response = ObesePercentageResponseJson.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "FOUNDED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @Validated
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("blood-type-age-average")
    public List<BloodTypeAgeAverageResponseJson> getBloodTypeAgeAverage() {
        return this.bloodTypeAgeAverageUseCase.get();
    }

    @ApiOperation(value = "Resource to get possible donations by receptor blood type",
            response = ObesePercentageResponseJson.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "FOUNDED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @Validated
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("possible-donations")
    public PossibleDonationsResponseJson getPossibleDonationsByReceptorBloodType() {
        return this.possibleDonationsByReceptorBloodTypeUseCase.get();
    }
}
