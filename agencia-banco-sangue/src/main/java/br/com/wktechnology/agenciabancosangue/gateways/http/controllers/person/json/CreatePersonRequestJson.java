package br.com.wktechnology.agenciabancosangue.gateways.http.controllers.person.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonRequestJson {

    @JsonProperty("nome")
    @NotEmpty(message = "Campo nome é obrigatório.")
    private String name;

    @Length(max = 14)
    @NotEmpty(message = "Campo cpf é obrigatório.")
    private String cpf;

    @Length(max = 12)
    @NotEmpty(message = "Campo rg é obrigatório.")
    private String rg;

    @JsonProperty("data_nasc")
    @NotEmpty(message = "Campo data_nasc é obrigatório.")
    private String birthDate;

    @JsonProperty("idade")
    private Integer age;

    @Length(max = 9)
    @JsonProperty("sexo")
    @NotEmpty(message = "Campo sexo é obrigatório.")
    private String gender;

    @JsonProperty("mae")
    @NotEmpty(message = "Campo mae é obrigatório.")
    private String mother;

    @JsonProperty("pai")
    @NotEmpty(message = "Campo pai é obrigatório.")
    private String father;

    @Email
    @NotEmpty(message = "Campo email é obrigatório.")
    private String email;

    @Length(max = 10)
    @NotEmpty(message = "Campo cep é obrigatório.")
    private String cep;

    @JsonProperty("endereco")
    @NotEmpty(message = "Campo endereco é obrigatório.")
    private String street;

    @Length(max = 3)
    @JsonProperty("numero")
    @NotEmpty(message = "Campo numero é obrigatório.")
    private Long number;

    @JsonProperty("bairro")
    @NotEmpty(message = "Campo bairro é obrigatório.")
    private String neighborhood;

    @JsonProperty("cidade")
    @NotEmpty(message = "Campo cidade é obrigatório.")
    private String city;

    @Length(max = 2)
    @JsonProperty("estado")
    @NotEmpty(message = "Campo estado é obrigatório.")
    private String state;

    @Length(max = 16)
    @JsonProperty("telefone")
    @NotEmpty(message = "Campo telefone é obrigatório.")
    private String phone;

    @Length(max = 17)
    @JsonProperty("celular")
    @NotEmpty(message = "Campo celular é obrigatório.")
    private String cellphone;

    @JsonProperty("altura")
    @NotEmpty(message = "Campo altura é obrigatório.")
    private Double height;

    @JsonProperty("peso")
    @NotEmpty(message = "Campo peso é obrigatório.")
    private Double weight;

    @Length(max = 2)
    @JsonProperty("tipo_sanguineo")
    @NotEmpty(message = "Campo tipo_sanguineo é obrigatório.")
    private String bloodType;
}
