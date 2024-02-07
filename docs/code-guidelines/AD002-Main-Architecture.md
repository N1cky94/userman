# Main Architecture

| Date       | Status   | Deciders       |
|------------|----------|----------------|
| 07/02/2024 | accepted | - Nick Bauters |
| 07/02/2024 | Updated  | - Nick Bauters |

## Context

We will describe the main architecture that should be followed for this project.

## Main Architecture

This project is started as a monolith that will internally be split in potential microservices.
This so we can easily factor out these services when this is needed.

This will create a simple tree:
- **verenig** - Main monolith folder
   - **api** - The API layer. This should house the controllers and output DTOs
   - **views** - The front end layer. This should house the front end code
   - **core** - The core layer. This should house the business logic and the domain
      - **services** - For every component there will be a separation as if they are microservices
   - **security** - The security layer. This should house the security logic and the security domain

## Core

The core layer is the most important layer of the application.
This will hold all the business logic and the domain.

Every component/service will be seperated and will adhere to a Clean Architecture.
How this will be implemented will be documented in a later stage.

### Services

Services should protect their internal code and should only expose the necessary methods to the outside world.
This should be done by the use of usecase interfaces and service classes.
No domain object should be outputted to the outside world, a service method should always return a DTO.
A service method should always accept a CommandDto as input.

## Front

The front-end will be provided via the Vaadin framework.
This will be the only front-end framework that will be used in this project.

All routes should be documented in the api-guideline-and-documentation document.

## API

The API layer will be provided via the Spring framework.
It's important that we also provide API endpoints for certain tasks and services.
These should be documented in the api-guideline-and-documentation document.