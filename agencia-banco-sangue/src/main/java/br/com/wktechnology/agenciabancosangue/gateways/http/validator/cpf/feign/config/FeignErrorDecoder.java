package br.com.wktechnology.agenciabancosangue.gateways.http.validator.cpf.feign.config;

import br.com.wktechnology.agenciabancosangue.gateways.exceptions.CpfNotFoundException;
import br.com.wktechnology.agenciabancosangue.gateways.exceptions.FeignClientException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(final String methodKey, final Response response) {
        if (response.status() == 404) {
            log.error("Error in request went through feign client: {}", response.request().body());
            return new CpfNotFoundException();
        }

        log.error("Error in request went: {}", response);
        return new FeignClientException();
    }
}
