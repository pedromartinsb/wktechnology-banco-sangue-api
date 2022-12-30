package br.com.wktechnology.agenciabancosangue.domains;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@ToString
@Entity
public class Person {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Length(max = 14)
    private String cpf;

    @NonNull
    @Length(max = 12)
    private String rg;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Length(max = 9)
    private String gender;

    private String mother;

    private String father;

    @Email
    @NotNull
    private String email;

    @Length(max = 10)
    private String cep;

    private String street;

    @Length(max = 3)
    private Long number;

    private String neighborhood;

    private String city;

    @Length(max = 2)
    private String state;

    @Length(max = 16)
    private String phone;

    @Length(max = 17)
    private String cellphone;

    private Double height;

    private Double weight;

    @Column(name = "blood_type")
    @Length(max = 2)
    private String bloodType;
}
