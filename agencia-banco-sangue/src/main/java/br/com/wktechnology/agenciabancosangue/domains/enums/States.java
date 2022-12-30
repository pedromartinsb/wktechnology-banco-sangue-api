package br.com.wktechnology.agenciabancosangue.domains.enums;

public enum States {
    AC,
    AL,
    AP,
    AM,
    BA,
    CE,
    DF,
    ES,
    GO,
    MA,
    MT,
    MS,
    MG,
    PA,
    PB,
    PR,
    PE,
    PI,
    RJ,
    RN,
    RS,
    RO,
    RR,
    SC,
    SP,
    SE,
    TO;

    public static States findByName(String name) {
        for (States s : values()) {
            if (s.name().equals(name)) {
                return s;
            }
        }
        return null;
    }
}
