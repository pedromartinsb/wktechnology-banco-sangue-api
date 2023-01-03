package br.com.wktechnology.agenciabancosangue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgenciaBancoSangueApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgenciaBancoSangueApplication.class, args);
	}

}
