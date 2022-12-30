package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.IMC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FindObesePercentualUseCase {

    @Autowired
    private CalculateIMCUseCase calculateIMCUseCase;

    public List<Integer> find() {
        List<IMC> imcList = this.calculateIMCUseCase.calculate();
        return null;
    }
}
