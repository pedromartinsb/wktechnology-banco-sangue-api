package br.com.wktechnology.agenciabancosangue.domains.enums;

public enum BloodType {
    O_PLUS(1, "O+", "O Positivo"),
    O_NEGATIVE(2, "O-", "O Negativo"),
    A_PLUS(3, "A+", "A Positivo"),
    A_NEGATIVE(4, "A-", "A Negativo"),
    B_PLUS(5, "B+", "B Positivo"),
    B_NEGATIVE(6, "B-", "B Negativo"),
    AB_PLUS(7, "AB+", "AB Positivo"),
    AB_NEGATIVE(8, "AB-", "AB Negativo");

    private final int value;
    private final String name;
    private final String description;

    BloodType(int value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
