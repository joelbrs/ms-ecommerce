# Projeto de Microsserviços de E-commerce

Este projeto é uma aplicação de e-commerce composta por vários microsserviços, incluindo `ms-relations`, `ms-product`, `cloud-gateway`, e outros. Cada microsserviço é responsável por uma parte específica da funcionalidade do sistema.

## Estrutura do Projeto

- **ms-relations**: Gerencia as relações entre produtos e categorias.
- **ms-product**: Gerencia os produtos.
- **cloud-gateway**: Gateway API que roteia as solicitações para os microsserviços apropriados.
- **eureka-server**: Serviço de descoberta para registrar e localizar microsserviços.

## Tecnologias Utilizadas

- **Spring Boot**: Framework principal para construir os microsserviços.
- **Spring Cloud**: Conjunto de ferramentas para gerenciar microsserviços, incluindo Eureka, Feign, e Gateway.
- **RabbitMQ**: Sistema de mensageria para comunicação assíncrona entre microsserviços.
- **PostgreSQL**: Banco de dados relacional para armazenar dados.
- **Docker**: Para containerização dos microsserviços.

## Configuração e Execução

### Pré-requisitos

- Java 11+
- Maven
- Docker
- RabbitMQ
- PostgreSQL

## Contribuição

Sinta-se à vontade para contribuir com o projeto. Para isso, siga os passos abaixo:

1. Faça um fork do projeto.
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`).
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. Faça um push para a branch (`git push origin feature/nova-feature`).
5. Crie um novo Pull Request.
