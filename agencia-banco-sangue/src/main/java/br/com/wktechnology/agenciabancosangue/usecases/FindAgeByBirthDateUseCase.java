package br.com.wktechnology.agenciabancosangue.usecases;

import br.com.wktechnology.agenciabancosangue.domains.converters.DateFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
@Service
public class FindAgeByBirthDateUseCase {

    @Autowired
    private DateFormat dateFormat;

    public int find(final String birthDate) {
        log.info("birthDate: {}", birthDate);
        LocalDate date = this.dateFormat.formatBirthDate(birthDate);
        LocalDate currentDate = LocalDate.now();
        return Period.between(date, currentDate).getYears();
    }
}
