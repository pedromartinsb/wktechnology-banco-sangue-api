package br.com.wktechnology.agenciabancosangue.gateways.exceptions;

import br.com.wktechnology.agenciabancosangue.config.interceptors.controllers.BloodCenterBaseException;
import org.springframework.http.HttpStatus;

public class CreatePersonDatabaseException extends BloodCenterBaseException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getCode() {
        return "wktechnology.agenciabancosangue.error.create";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return "Error to create Person.";
    }
}
