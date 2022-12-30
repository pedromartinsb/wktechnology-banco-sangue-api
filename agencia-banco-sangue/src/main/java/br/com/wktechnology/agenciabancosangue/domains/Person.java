package br.com.wktechnology.agenciabancosangue.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Size(max = 14, message = "O campo deve conter no máximo 14 caracteres.")
    private String cpf;

    @NonNull
    @Size(max = 12, message = "O campo deve conter no máximo 12 caracteres.")
    private String rg;

    @Column(name = "birth_date")
    private String birthDate;

    @JsonProperty("idade")
    private Integer age;

    @Size(max = 9, message = "O campo deve conter no máximo 9 caracteres.")
    private String gender;

    private String mother;

    private String father;

//    @Email
    @NotNull
    private String email;

    @Size(max = 10, message = "O campo deve conter no máximo 10 caracteres.")
    private String cep;

    private String street;

    private Integer number;

    private String neighborhood;

    private String city;

    @Size(max = 2, message = "O campo deve conter no máximo 2 caracteres.")
    private String state;

    @Size(max = 16, message = "O campo deve conter no máximo 16 caracteres.")
    private String phone;

    @Size(max = 17, message = "O campo deve conter no máximo 17 caracteres.")
    private String cellphone;

    private Double height;

    private Integer weight;

    @Column(name = "blood_type")
    @Size(max = 3, message = "O campo deve conter no máximo 2 caracteres.")
    private String bloodType;

    private Double imc;
}
