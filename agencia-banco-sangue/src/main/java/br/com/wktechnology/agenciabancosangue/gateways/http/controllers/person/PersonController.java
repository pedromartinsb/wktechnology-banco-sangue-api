package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person;

import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.FindCandidatesRequestJson;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.FindCandidatesResponseJson;
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

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${baseurl.v1}/person")
@Api(tags = "Person", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private FindCandidatesPerStateUseCase findCandidatesPerStateUseCase;

    @ApiOperation(value = "Resource to Find number of Candidates per State", response = FindCandidatesResponseJson.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "FOUNDED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @Validated
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public FindCandidatesResponseJson find(
            final @RequestBody(required = true) @Valid FindCandidatesRequestJson findCandidatesRequestJson) {
        log.info("findCandidatesRequestJson: {}", findCandidatesRequestJson);
        final Integer numberOfCandidates = this.findCandidatesPerStateUseCase.find(findCandidatesRequestJson.getState());
        return FindCandidatesResponseJson
                .builder()
                .number(numberOfCandidates)
                .build();
    }
}
