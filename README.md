# TP2 - Architecture Microservices avec Spring Cloud

Projet pratique d'implementation d'une architecture microservices avec Spring Boot et Spring Cloud.

## Technologies
- Java 17
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3
- Spring Cloud Gateway
- Eureka Discovery Server
- Spring Cloud Config Server
- OpenFeign
- Spring Data JPA
- H2 Database
- Maven

## Architecture

| Service | Port |
|---------|------|
| Discovery Service | 8761 |
| Config Service | 9999 |
| Customer Service | 8081 |
| Inventory Service | 8082 |
| Billing Service | 8083 |
| Gateway Service | 8888 |

## Fonctionnalites
- Gestion des clients
- Gestion des produits
- Gestion des factures
- Communication entre microservices avec OpenFeign
- Decouverte des services avec Eureka
- Routage avec Spring Cloud Gateway
- Configuration centralisee avec Spring Cloud Config Server

## Demarrage

Demarrer les services dans cet ordre :

1. Config Service
2. Discovery Service
3. Customer Service
4. Inventory Service
5. Billing Service
6. Gateway Service

Depuis le dossier de chaque microservice :

.\mvnw.cmd spring-boot:run

## API Gateway

- http://localhost:8888/customers
- http://localhost:8888/products
- http://localhost:8888/bills
