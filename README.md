# SaaS Training Project

Ce README.md fournit une vue d'ensemble complète de votre projet, ce qui facilitera la compréhension rapide de l'architecture et des fonctionnalités lors des futures interventions. Il couvre les aspects techniques, l'architecture, les fonctionnalités principales et les instructions de démarrage.

## Architecture Globale

Ce projet est un monorepo contenant une application SaaS avec :

- Un backend Spring Boot (Java 21)
- Un frontend NuxtJS
- Une base de données PostgreSQL
- Une intégration Stripe pour les paiements

### Structure du Projet

saas-training/
├── backend/ # API Spring Boot
├── front-saas-training/ # Application NuxtJS
├── docker-compose.yml # Configuration des conteneurs
└── infra/ # Configuration infrastructure

## Backend (Spring Boot 3)

### Technologies Principales

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- Lombok
- JWT pour l'authentification
- OpenCSV pour l'import de fichiers
- Stripe pour les paiements
- JavaMail pour l'envoi d'emails

### Fonctionnalités Principales

- Authentification JWT
- Gestion des utilisateurs (premium/standard)
- Import de transactions CSV
- Intégration paiements Stripe
- Gestion des abonnements
- Envoi d'emails

### Modèles de Données

- User : Gestion des utilisateurs
- Transaction : Suivi des transactions
- Subscription : Gestion des abonnements

## Frontend (NuxtJS)

### Technologies Principales

- NuxtJS
- TypeScript
- Tailwind CSS
- Pinia pour la gestion d'état
- Stripe.js pour l'intégration des paiements

### Composants Principaux

- Authentication (Login/Register)
- Dashboard
- Import CSV
- Gestion des transactions
- Système de paiement Stripe
- Pages Premium

### Pages Principales

- `/` : Page d'accueil avec Hero et Pricing
- `/connexion` : Authentification
- `/register` : Inscription
- `/payment` : Processus de paiement
- `/premium/*` : Fonctionnalités premium
- `/admin/*` : Interface d'administration

## Infrastructure

### Docker

Le projet utilise Docker avec trois services principaux :

- `saas-training-postgres` : Base de données PostgreSQL
- `frontend` : Application NuxtJS
- `backend` : API Spring Boot

### Configuration

- Base de données : PostgreSQL 15
- Ports :
  - Frontend : 3000
  - Backend : 8080
  - PostgreSQL : 5432

### Variables d'Environnement

Principales variables nécessaires :

- `DB_NAME`
- `DB_USERNAME`
- `DB_PASSWORD`
- `DB_URL`
- `STRIPE_API_KEY`

## Fonctionnalités Clés

### Gestion des Utilisateurs

- Inscription/Connexion
- Réinitialisation de mot de passe
- Différenciation utilisateurs premium/standard

### Gestion des Transactions

- Import de fichiers CSV
- Visualisation des transactions
- Catégorisation automatique
- Analyses mensuelles

### Système de Paiement

- Intégration Stripe
- Gestion des abonnements
- Webhooks pour le suivi des paiements

## Démarrage du Projet

```bash
# Lancement de l'environnement
docker-compose up -d

# Installation des dépendances frontend
cd front-saas-training
npm install

# Installation des dépendances backend
cd backend
mvn install
```
