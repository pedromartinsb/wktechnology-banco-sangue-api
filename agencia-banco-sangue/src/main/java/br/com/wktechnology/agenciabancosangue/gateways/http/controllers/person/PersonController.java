package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person;

import br.com.wktechnology.agenciabancosangue.domains.CalculateIMC;
import br.com.wktechnology.agenciabancosangue.domains.FindCandidates;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.*;
import br.com.wktechnology.agenciabancosangue.usecases.CalculateIMCUseCase;
import br.com.wktechnology.agenciabancosangue.usecases.FindCandidatesPerStateUseCase;
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
    private FindCandidatesPerStateUseCase findCandidatesPerStateUseCase;

    @Autowired
    private CalculateIMCUseCase calculateIMCUseCase;

    @ApiOperation(value = "Resource to Find number of Candidates per State", response = FindCandidatesResponseJson.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "FOUNDED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @Validated
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("find-candidates")
    public FindCandidatesResponseJson find(
            final @RequestBody() @Valid FindCandidatesRequestJson findCandidatesRequestJson) {
        log.info("findCandidatesRequestJson: {}", findCandidatesRequestJson);
        List<FindCandidates> findCandidates = this.findCandidatesPerStateUseCase.find(findCandidatesRequestJson);
        return FindCandidatesResponseJson
                .builder()
                .persons(findCandidates)
                .build();
    }

    @ApiOperation(value = "Resource to calculate IMC of ten to ten years", response = FindCandidatesResponseJson.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "FOUNDED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @Validated
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("calculate-imc")
    public CalculateIMCResponseJson calculateIMC(
            final @RequestBody() @Valid CalculateIMCRequestJson calculateIMCRequestJson) {
        log.info("calculateIMCRequestJson: {}", calculateIMCRequestJson);
        List<CalculateIMC> calculateIMC = this.calculateIMCUseCase.calculate(calculateIMCRequestJson);
        return CalculateIMCResponseJson
                .builder()
                .imc(calculateIMC)
                .build();
    }
}
