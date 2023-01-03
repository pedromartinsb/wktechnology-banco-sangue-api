<h1 align="center"> Agência de Banco de Sangue </h1>
<p align="justify"> Uma API real simples contendo  CRUD de classes, cobertura de testes com Mockito e Faker, monitoramento dos endpoints com Spring Boot Actuator e documentação da API com Swagger. </p>

![Badge](https://img.shields.io/static/v1?label=java&message=language&color=red&style=for-the-badge&logo=JAVA)
![Badge](https://img.shields.io/static/v1?label=spring+boot&message=framework&color=green&style=for-the-badge&logo=SPRING)
![Badge](https://img.shields.io/static/v1?label=postman&message=testing+apis&color=orange&style=for-the-badge&logo=POSTMAN)
![Badge](https://img.shields.io/static/v1?label=mysql&message=data+base&color=blue&style=for-the-badge&logo=MySQL)
![Badge](https://img.shields.io/static/v1?label=swagger&message=api+documentation&color=green&style=for-the-badge&logo=SWAGGER)
![Badge](https://img.shields.io/static/v1?label=docker&message=container&color=blue&style=for-the-badge&logo=DOCKER)
![Badge](https://img.shields.io/static/v1?label=flyway&message=migrations&color=red&style=for-the-badge&logo=MIGRATIONS)

### Funcionalidades da Aplicação  

- CRUD de classes
    - Classe de Pessoa e suas responsabilidades
- CORS do enpoint
 - Spring Cache
    - Anotação @Cacheable
    - Boas práticas no uso de Cacheable
- Spring Boot Actuator
    - Monitoramento com Spring Boot Actuator
- Documentação da API com Swagger
- Collection do Postman para executar as chamadas
    
    
## O que a plataforma é capaz de fazer :checkered_flag:

:trophy: Cadastrar, Listar e Alterar a classe de Person 

:trophy: Buscar lista de pessoas candidatas a doação por estado

:trophy: Calcular IMC médio das pessoas na faixa etária de 10 em 10 anos

:trophy: Buscar lista de porcentagem de obesos por sexo

:trophy: Buscar a média de idade por tipo sanguíneo

:trophy: Buscar os possíveis doadores por cada tipo sanguíneo

## Status do Projeto

> API Rest - Backend: Concluido :heavy_check_mark:

> Projeto Frontend: Concluido :heavy_check_mark:

## Como rodar a aplicação

No terminal, clone o projeto:
git clone https://github.com/pedromartinsb/wktechnology-banco-sangue-api.git

Entre na pasta do projeto:
cd wktechnology-banco-sangue-api

Rodar a aplicação:
-> Abrir a árvore de arquivos src/main/java/br.com.wktechnology.agenciabancosangue -> Abrir o arquivo AgenciaBancoSangueApplication.java -> Botão direito dentro do arquivo -> Run As -> Java Application

Pronto, agora é possível rodar a aplicação a partir da rota http://localhost:9090

## Linguagens e libs utilizadas :books:

- [Java Download](https://www.java.com/pt_BR/download/): versão 11

- [Spring Boot Tutorial](https://spring.io/guides/gs/spring-boot/): versão 2.5.6

Frameworks utilizados:

- [Eclipse IDE](https://www.eclipse.org/downloads/): versão 2020‑06

- [Postman](https://www.postman.com/)

### Migrations  

- Versionamento de SQL para banco de dados
    - Como usar: V1__Create_Tables.sql | V2__Populate_Tables.sql


## Desenvolvedor
[<img src="https://avatars0.githubusercontent.com/u/33515329?s=460&u=251d4ef587ca509428d495ef98c0f6f1887dc3de&v=4" width=200 > <br> <sub> Pedro Campos </sub>](https://github.com/pedromartinsb)
| :---: |


## Requisições:

### POST de criação de Person
![image](https://user-images.githubusercontent.com/33515329/210457924-289cde0e-f102-4d78-8336-91637a160f87.png)


###  GET de Candidatos por Estado
![image](https://user-images.githubusercontent.com/33515329/210458337-5aaca0e4-ec81-4a6e-afea-eaf71c478c60.png)


### GET de Calcular o Imc médio
![image](https://user-images.githubusercontent.com/33515329/210458638-b255e174-81dd-4172-80b0-e805ede868d6.png)


### GET de Buscar porcentagem de obesidade
![image](https://user-images.githubusercontent.com/33515329/210458841-0fb010d2-0fbb-45ab-b3ff-ed43f8859d03.png)


### GET de Buscar média de idade por tipo sanguíneo
![image](https://user-images.githubusercontent.com/33515329/210458970-fed64faa-ea8b-478d-9425-da523f4a7131.png)


### GET de Buscar possíveis doadores por cada tipo sanguíneo
![image](https://user-images.githubusercontent.com/33515329/210459097-715eff79-322e-4d17-9fe2-b70580c88592.png)



