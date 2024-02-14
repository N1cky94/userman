# User Management (UserMan)

> Archilios project 2024
>
> Main Contributors: Nick Bauters
> Purpose: This project is a demo project for Vaadin and other technologies.
> Last Update: 06-02-2024

UserMan is a web application that helps you manage your users.
It holds simple, but powerful capabilities to manage your users.
- Creating users
- Activating accounts
- Messaging Users

It uses a couple of important technologies:
- Java 21
- Spring Boot 3
- Vaadin 24
- PostgreSQL
- Docker

## Setting up for development

To set up the project for development, we need some configuration.
We will go through this configuration, step by step.

### Java

Using SDKMan, you can install Java 21+ on your machine.
We use the SE 21 from OpenJDK.

```bash
sdk install java 21.ea.35-open
sdk use java 21.ea.35-open
```

### IDEA

We use IntelliJ IDEA for the best coding experience.
GitHub CoPilot is allowed to be used for this project, and will be provided for every developer working on this project.

To set up the project in IntelliJ IDEA, you need to check and verify a couple of things.

1. Make sure you have the Lombok plugin installed.
2. Check if the correct Java version is being used.
3. Configure the following environment variables in the run configuration:
```txt
DATABASE_NAME=UserManDB
DATABASE_USER=user
DATABASE_PSW=kdOels9ejd82J
DATABASE_URL=jdbc:postgresql://localhost:5432/UserManDB
MAIL_HOST=smtp.yourmaildomain.com
MAIL_PORT=587
MAIL_USER=no-reply@yourdomain.com
MAIL_PSW=teST123[]TEst
```

You can also copy-paste the following in the run configuration:
```txt
DATABASE_NAME=UserManDB;DATABASE_USER=user;DATABASE_PSW=kdOels9ejd82J;DATABASE_URL=jdbc:postgresql://localhost:5432/UserManDB;MAIL_HOST=smtp.yourmaildomain.com;MAIL_PORT=587;MAIL_USER=no-reply@yourdomain.com;MAIL_PSW=teST123[]TEst
```

### PostgreSQL

The database is a PostgresQL database.
We are providing a [docker-compose](docker-compose.yml) file to set up the database for development.
This also loads all the necessary [schema's and dummy data](data).

Before running the Docker container, you will also need to install Docker on your machine.
And you will need the ```.env``` file from the project's root directory.
This file should look like this:

```dotenv
DATABASE_NAME=UserManDB
DATABASE_USER=user
DATABASE_PSW=kdOels9ejd82J
DATABASE_URL=jdbc:postgresql://localhost:5432/UserManDB
```

```bash
docker-compose up -d
```

You can also use the following command to stop the database.

```bash
docker-compose down -v
```

We use the ```-v``` flag to remove the volumes as well.

### Node Package manager

Before you run the application, make sure to install the Node package.
We use the default NPM package for this project.

```bash
npm install
```

## Development

Before contributing to the source code, make sure to read [all documentation in the project](docs).
This includes the README.md, the LICENSE.md, and the CONTRIBUTING.md.

## Running the project

You can run it via IntelliJ IDEA or another IDE.
You can also run the tests this way.

Via maven, we run the maven wrapper for production ends.
```bash
./mvnw clean test
```

You can run it within a docker image to test it in a production-like environment.

## How the project is delivered

The project is a monolithic project that consists of all the code necessary to run the application.
For the moment, this project only runs in development mode, so there is no CD pipeline.

This will probably be run on a Heroku server when the application goes live.
This will comprise at least an application server and a database server.

### Deploying to Production
To create a production build, call `mvnw clean package -Pproduction` (Windows), or `./mvnw clean package -Pproduction` (Mac & Linux). 
This will build a JAR file with all the dependencies and front-end resources, ready to be deployed. 
The file can be found in the target folder after the build completes.

Once the JAR file is built, you can run it using `java -jar target/userman-1.0-SNAPSHOT.jar`
