package br.com.wktechnology.agenciabancosangue.config.interceptors.controllers;

import br.com.wktechnology.agenciabancosangue.config.interceptors.controllers.json.ExceptionJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler {

    private static final String CONSTRAINT_VIOLATION = "example.error.constraintViolation";
    private static final String EXCEPTION_HANDLER_TO_RESOLVE_EXCEPTION = "exceptionHandler.errorToResolveException";
    private static final String ERROR_TO_RESOLVE_EXCEPTION_HANDLER = "Error to resolve exception handler";
    private static final String ARGUMENT_NOT_VALID = "example.error.argumentNotValid";
    private static final String MISSING_PARAM = "missingParam";
    private static final String BODY_INCORRECT_VALUE = "body.incorrectValue";

    @ExceptionHandler({BloodCenterBaseException.class})
    @ResponseBody
    public ResponseEntity<ExceptionJson> bloodCenterException(final BloodCenterBaseException e, final HttpServletResponse response) {
        log.error(e.getMessage(), e);
        final ExceptionJson exceptionJson = new ExceptionJson(e);
        return new ResponseEntity<>(exceptionJson, new HttpHeaders(), e.getHttpStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ExceptionJson genericError(final Throwable e) {
        log.error(e.getMessage(), e);
        return new ExceptionJson(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionJson handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        if (!ObjectUtils.isEmpty(e.getMessage()) && ";".contains(e.getMessage())) {
            final Matcher matcher = Pattern.compile("(JSON parse error: Cannot deserialize value of type (.*)) from ((.*))")
                    .matcher(StringUtils.split(e.getMessage(), ";")[0]);

            if (matcher.matches()) {
                return new ExceptionJson(BODY_INCORRECT_VALUE, matcher.group(3));
            }
        }
        return new ExceptionJson(BODY_INCORRECT_VALUE, e.getLocalizedMessage());
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionJson handleRequiredParameters(final ServletRequestBindingException e) {
        log.error(e.getMessage(), e);
        return new ExceptionJson(MISSING_PARAM, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionJson handlerMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        log.error(e.getMessage(), e);
        return new ExceptionJson(e.getName(), "Failed to convert " + e.getValue());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionJson validationException(final MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        try {
            final StringBuffer errors = new StringBuffer();
            e.getBindingResult().getFieldErrors().forEach(field -> {
                errors.append(field.getField()).append(":").append(field.getDefaultMessage()).append(";");
            });
            return new ExceptionJson(ARGUMENT_NOT_VALID, errors.toString());
        } catch (Exception ex) {
            log.error(ERROR_TO_RESOLVE_EXCEPTION_HANDLER, ex);
            return new ExceptionJson(EXCEPTION_HANDLER_TO_RESOLVE_EXCEPTION, e.getMessage());
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionJson violationException(final ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        final StringBuffer errors = new StringBuffer();
        e.getConstraintViolations().forEach(constraintViolation ->
                errors.append(constraintViolation.getPropertyPath()).append(":").append(constraintViolation.getMessage()).append(";"));
        return new ExceptionJson(CONSTRAINT_VIOLATION, errors.toString());
    }
}
