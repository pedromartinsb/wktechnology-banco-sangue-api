package br.com.wktechnology.agenciabancosangue.domains;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
    @Length(max = 14, message = "O campo deve conter no máximo 14 caracteres.")
    private String cpf;

    @NonNull
    @Length(max = 12, message = "O campo deve conter no máximo 12 caracteres.")
    private String rg;

    @Column(name = "birth_date")
    private String birthDate;

    @Length(max = 9, message = "O campo deve conter no máximo 9 caracteres.")
    private String gender;

    private String mother;

    private String father;

    @Email
    @NotNull
    private String email;

    @Length(max = 10, message = "O campo deve conter no máximo 10 caracteres.")
    private String cep;

    private String street;

    @Length(max = 3, message = "O campo deve conter no máximo 3 números.")
    private Long number;

    private String neighborhood;

    private String city;

    @Length(max = 2, message = "O campo deve conter no máximo 2 caracteres.")
    private String state;

    @Length(max = 16, message = "O campo deve conter no máximo 16 caracteres.")
    private String phone;

    @Length(max = 17, message = "O campo deve conter no máximo 17 caracteres.")
    private String cellphone;

    private Double height;

    private Double weight;

    @Column(name = "blood_type")
    @Length(max = 2, message = "O campo deve conter no máximo 2 caracteres.")
    private String bloodType;
}
