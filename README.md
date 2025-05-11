# FitFit – Fitness Club Web Application

## Overview

**FitFit** is a full-stack web application integrated with a relational database for managing fitness club operations. The application supports user registration, role management, and various CRUD operations. Users are assigned roles (e.g., athlete, trainer, admin) which determine their access rights within the system.

## Features

- User registration and secure login (with password encryption)
- Role-based access (User, Trainer, Office Worker, Admin)
- Dynamic role upgrade requests
- Admin approval panel for role changes
- Event listing with pagination
- Profile management based on assigned roles
- Real-time interaction with a PostgreSQL database

## Technologies Used

### Backend
- **Java** – core programming language
- **Spring Boot** – simplifies application setup and API creation
- **Spring Security** – manages authentication and authorization
- **Hibernate** – ORM tool for database interaction
- **PostgreSQL** – relational database system

### Frontend
- **HTML/CSS** – page structure and styling
- **JavaScript** – client-side interactivity

## User Roles and Permissions

### Guest
- View public pages and event listings

### Registered User
- Manage personal information
- Submit role upgrade requests

### Trainer
- Manage team compositions
- View salary information

### Office Worker
- Edit athlete and trainer details
- Organize events

### Administrator
- Approve or reject role requests
- Manage user accounts and club data

## Pages Overview

- **Home Page** – shows key stats and events (available to all users)
- **Login/Register** – secure login form and account creation
- **Profile** – user-specific actions and details
- **Role Upgrade** – form for requesting new permissions
- **Admin Panel** – admin actions on user role requests

## Database Operations

- **Insert** – user registration, role requests
- **Update** – profile updates, role changes
- **Select** – events, user details
- **Delete** – managed by administrators

## Project Reflections

Despite being time-consuming, the project provided valuable experience in:
- Java backend development using Spring Boot and Hibernate
- Frontend integration using JavaScript
- Role-based authorization using Spring Security
- Real-world debugging and problem-solving

## Authors

- Artur Sliepchenko
- Denys Moldovan
