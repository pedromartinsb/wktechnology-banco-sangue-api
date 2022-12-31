package br.com.wktechnology.agenciabancosangue.domains;

import br.com.wktechnology.agenciabancosangue.domains.enums.Gender;
import lombok.Data;

@Data
public class ObesePercentage {

    private Gender gender;
    private float percentage;
}
