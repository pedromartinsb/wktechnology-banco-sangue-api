package br.com.wktechnology.agenciabancosangue.config.interceptors.controllers.json;

import br.com.wktechnology.agenciabancosangue.config.interceptors.controllers.BloodCenterBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionJson {
    private final String code;
    private final String message;

    public ExceptionJson(final BloodCenterBaseException baseException) {
        this.code = baseException.getCode();
        this.message = baseException.getMessage();
    }
}
