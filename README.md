# Parking Control API

Esta é uma API de controle de estacionamento desenvolvida em Java com Spring Boot.

## Recursos

- Cadastro de veículos e vaga


## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- Java JDK 8 ou superior
- Maven
- Git

Esta é uma API de controle de estacionamento desenvolvida em Java com Spring Boot.
Método POST <br>
http://localhost:8080/parking-spot <br>
exemplo JSON: <br>
{ <br>
    "parkingSpotNumber" : "205B",<br>
    "licensePlateCar" : "VDG1989",<br>
    "brandCar" : "Fiat",<br>
    "modelCar" : "vasco",<br>
    "colorCar" : "preto",<br>
    "responsibleName" : "Luiz Adolfo",<br>
    "apartment" : "12",<br>
    "block" : "5"<br>
}<br>
exemplo para GET de consulta de placa: <br>
http://localhost:8080/parking-spot/car/VDG1989 <br>

A aplicação estará disponível em http://localhost:8080.

Tecnologias Utilizadas
Spring Boot
Spring Data JPA
H2 Database (para desenvolvimento)
Swagger (para documentação da API)
Contribuindo
Este repositório é aberto a contribuições. Sinta-se à vontade para enviar pull requests ou relatar problemas. Contribuições são bem-vindas!

