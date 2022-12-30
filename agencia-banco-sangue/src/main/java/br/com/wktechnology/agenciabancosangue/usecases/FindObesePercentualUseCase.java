package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.IMC;
import br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json.CalculateIMCRequestJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FindObesePercentualUseCase {

    @Autowired
    private CalculateIMCUseCase calculateIMCUseCase;

    public List<Integer> find(final CalculateIMCRequestJson calculateIMCRequestJson) {
        List<IMC> imcs = this.calculateIMCUseCase.calculate(calculateIMCRequestJson);
        return null;
    }
}
