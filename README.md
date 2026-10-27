
# TP2 — Architecture Microservices avec Spring Cloud et Angular

## Description

Ce projet consiste à développer une application distribuée basée sur une architecture microservices avec Spring Boot et Spring Cloud.

L'application comprend plusieurs microservices indépendants, un serveur de découverte Eureka, un serveur de configuration centralisée, une API Gateway et une interface frontend développée avec Angular 22.

Le projet permet de gérer les clients, les produits et les factures.

## Technologies utilisées

### Backend

- Java 17
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3
- Spring Cloud Netflix Eureka
- Spring Cloud Config Server
- Spring Cloud Gateway
- Spring Data JPA
- Spring Cloud OpenFeign
- Spring Cloud LoadBalancer
- H2 Database
- Maven

### Frontend

- Angular 22
- TypeScript
- HTML5
- CSS3
- Angular Signals
- Angular Router
- Angular HttpClient
- Node.js
- npm

### Outils

- IntelliJ IDEA
- Visual Studio Code
- Git et GitHub
- PowerShell

## Architecture du projet

```text
                  Angular Frontend
                  localhost:4200
                         |
                         v
                  API Gateway
                  localhost:8888
                         |
          +--------------+--------------+
          |              |              |
          v              v              v
   Customer Service  Inventory Service  Billing Service
    localhost:8081    localhost:8082    localhost:8083
          |              |              |
          v              v              v
      H2 Database    H2 Database    H2 Database


             Eureka Discovery Server
                  localhost:8761

              Spring Config Server
                  localhost:9999
                         |
                         v
                    config-repo
```

Le microservice Billing utilise OpenFeign pour communiquer avec Customer Service et Inventory Service.

## Microservices développés

| Microservice | Port | Description |
|--------------|------|-------------|
| Discovery Service | 8761 | Enregistrement et découverte des services avec Eureka |
| Config Service | 9999 | Centralisation des configurations |
| Customer Service | 8081 | Gestion des clients |
| Inventory Service | 8082 | Gestion des produits |
| Billing Service | 8083 | Gestion des factures |
| Gateway Service | 8888 | Point d'entrée des API |

## Structure du projet

```text
tp2-microservices-spring-cloud/
│
├── discovery-service/
├── config-service/
├── customer-service/
├── inventory-service/
├── billing-service/
├── gateway-service/
│
├── config-repo/
│   ├── customer-service.properties
│   ├── inventory-service.properties
│   ├── billing-service.properties
│   └── gateway-service.properties
│
├── angular-frontend/
│   ├── src/
│   │   └── app/
│   │       ├── components/
│   │       │   ├── customers/
│   │       │   ├── products/
│   │       │   ├── bills/
│   │       │   └── navbar/
│   │       ├── models/
│   │       ├── services/
│   │       ├── app.routes.ts
│   │       └── app.config.ts
│   ├── proxy.conf.json
│   └── package.json
│
└── README.md
```

## Fonctionnalités

### Customer Service

- Consulter tous les clients
- Consulter un client par identifiant
- Stocker les clients dans H2
- Enregistrement automatique dans Eureka

### Inventory Service

- Consulter les produits
- Consulter un produit par identifiant
- Afficher les prix et les quantités
- Stocker les produits dans H2

### Billing Service

- Consulter les factures
- Consulter une facture par identifiant
- Récupérer les informations des clients avec OpenFeign
- Récupérer les informations des produits avec OpenFeign
- Afficher les lignes et les montants des factures

### API Gateway

- Centraliser les requêtes HTTP
- Router les requêtes vers les microservices
- Utiliser Eureka pour découvrir les services
- Utiliser Spring Cloud LoadBalancer

### Angular Frontend

- Affichage des clients
- Affichage des produits
- Affichage des factures
- Navigation entre les pages
- Chargement dynamique des données
- Gestion des erreurs HTTP
- Mise à jour réactive avec Angular Signals

## Installation et exécution

### Prérequis

- JDK 17
- Node.js
- npm
- Angular CLI
- Git
- Maven Wrapper

### 1. Cloner le projet

```bash
git clone https://github.com/mohamed-yawina/tp2-microservices-spring-cloud.git

cd tp2-microservices-spring-cloud
```

### 2. Démarrer les microservices

Ouvrir un terminal pour chaque microservice.

Ordre de démarrage recommandé :

1. Discovery Service
2. Config Service
3. Customer Service
4. Inventory Service
5. Billing Service
6. Gateway Service

Exemple sous Windows :

```powershell
cd discovery-service
.\mvnw.cmd spring-boot:run
```

Répéter la commande dans le dossier de chaque service.

Vérifier que Java 17 est utilisé pour les services Spring Boot.

### 3. Vérifier Eureka

Accéder à :

http://localhost:8761

Vérifier que les microservices sont enregistrés avec le statut UP.

### 4. Démarrer Angular

Dans un nouveau terminal :

```powershell
cd angular-frontend

npm install

ng serve --proxy-config proxy.conf.json --open
```

L'application sera accessible à :

http://localhost:4200

## Endpoints REST

### Customer Service

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | /customers | Liste des clients |
| GET | /customers/{id} | Client par ID |

### Inventory Service

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | /products | Liste des produits |
| GET | /products/{id} | Produit par ID |

### Billing Service

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | /bills | Liste des factures |
| GET | /bills/{id} | Facture par ID |

### API Gateway

Toutes les API sont accessibles à travers le port 8888 :

```text
http://localhost:8888/customers
http://localhost:8888/products
http://localhost:8888/bills
```

## Interface Angular

### Page Customers

URL :

http://localhost:4200/customers

Affiche la liste des clients avec :

- Identifiant
- Nom
- Email

### Page Products

URL :

http://localhost:4200/products

Affiche les produits avec :

- Identifiant
- Nom
- Prix
- Quantité disponible

### Page Bills

URL :

http://localhost:4200/bills

Affiche les factures avec :

- Identifiant de la facture
- Date
- Client
- Produits achetés
- Quantités
- Prix
- Montant total

## Communication Frontend / Backend

Le frontend Angular communique avec les microservices à travers Spring Cloud Gateway.

```text
Angular
   |
   v
Angular Development Proxy
   |
   v
Spring Cloud Gateway
   |
   v
Microservices Spring Boot
   |
   v
H2 Databases
```

Le fichier `proxy.conf.json` permet de rediriger les requêtes `/api` vers la Gateway pendant le développement.

## Compilation Angular

Pour générer une version de production :

```bash
cd angular-frontend

ng build
```

Les fichiers compilés sont générés dans le dossier `dist/`.

Le proxy de développement Angular n'est pas automatiquement inclus dans le build de production. Un déploiement réel nécessite une configuration adaptée pour accéder aux API.

## Objectifs pédagogiques

- Comprendre les architectures microservices
- Utiliser Spring Boot et Spring Cloud
- Mettre en place Eureka Discovery
- Centraliser les configurations avec Config Server
- Configurer une API Gateway
- Communiquer entre microservices avec OpenFeign
- Utiliser Spring Data JPA et H2
- Développer un frontend Angular
- Consommer des API REST avec HttpClient
- Utiliser Angular Signals pour les interfaces réactives

## Auteur

**Mohamed Yawina**

Projet réalisé dans le cadre du TP2 :
**Implémentation d'une architecture micro-services**

Encadrant : **Pr. Mohamed YOUSSFI**
