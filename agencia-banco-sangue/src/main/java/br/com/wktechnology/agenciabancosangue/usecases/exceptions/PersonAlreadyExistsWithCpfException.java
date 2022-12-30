package br.com.wktechnology.agenciabancosangue.usecases.exceptions;

import br.com.wktechnology.agenciabancosangue.config.interceptors.controllers.BloodCenterBaseException;
import org.springframework.http.HttpStatus;

public class PersonAlreadyExistsWithCpfException extends BloodCenterBaseException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getCode() {
        return "wktechnology.agenciabancosangue.error.personAlreadyExistsWithCpfException";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

    @Override
    public String getMessage() {
        return "Person already exists with cpf.";
    }
}
