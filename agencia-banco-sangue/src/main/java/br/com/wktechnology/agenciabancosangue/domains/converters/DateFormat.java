package br.com.wktechnology.agenciabancosangue.domains.converters;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormat {

    public LocalDate formatBirthDate(String birthDate) {
        birthDate = regexBirthDate(birthDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(birthDate, formatter);
    }

    private String regexBirthDate(final String birthDate) {
        return birthDate.replaceAll("\\\\", "");
    }
}
