package br.com.wktechnology.agenciabancosangue.domains.enums;

public enum Gender {
    MASCULINO(1, "Masculino"), FEMININO(1, "Feminino");

    private final int value;
    private final String name;

    Gender(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
