# Vehicle Rest API
Minimal [Spring Boot](http://projects.spring.io/spring-boot)

## Requirements
For building and running the application you need:

- [JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Gradle](https://gradle.org)

## Introduction
This guide walks you through the process of creating an application that accesses relational JPA data through REST API calls.

## About the application
This is a Vehicle Management Portal application that will handle all the CRUD operations regarding to the vehicles.
The main features are: `Create` `Read` `Update` `Delete`

## Programming Languages
This project is authored in Java 8.

## Dependencies
* 	[Flyway](https://flywaydb.org/) - Version control for database
* 	[Git](https://git-scm.com/) - Free and Open-Source distributed version control system
*   [Google JIB](https://cloud.google.com/blog/products/gcp/introducing-jib-build-java-docker-images-better) - Jib is a fast and simple container image builder 
that handles all the steps of packaging your application into a container image
* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, 
Automate your logging variables, and much more
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[TestContainers](https://www.testcontainers.org/) - Lightweight, throwaway instances of common databases
* 	[Postgres](https://www.postgresql.org/) - Open-Source Relational Database Management System

## Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the 
`com.galvao.vehicle.VehicleApplication` class from your IDE.

Alternatively you can use the [Spring Boot Gradle plugin](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/) like so:

```shell
./gradlew bootRun
```

* compiles Java classes to the /target directory
* copies all resources to the /target directory
* starts an embedded Apache Tomcat server

## Folder structure + important files

```bash
.
├── README.md                                   # Important! Read before changing configuration
├── build.gradle
├── settings.gradle
└── src
    ├── main
    │   ├── java                                # service
    │   └── resources
    │       ├── application.yml                 # Common application configuration runnning using docker configs
    │       └── application-LOCAL.yml           # Overriding configuration specifc to local environment
    └── test
        ├── java                                # Sample Testcases
        └── resources
            └── application-TEST.yml
```

## Database Migration (Setup to run with a standalone local instance of the database)
Postgres Docker locally [reference](https://hub.docker.com/_/postgres)
```
$ docker pull postgres
```
```
$ docker run --name some-postgres -e POSTGRES_PASSWORD=password -d postgres
```

Database setup, checkout the latest version of the project and from the root folder run:
```
$ ./gradlew migrateLocal
```

## Testing the application
There is a documentation swagger page with all the possible endpoint calls for the API.

http://localhost:8081/api/docs/

## Security
This application is using http basic authentication of spring security, in order to do the API calls its needed to inform the authorization at the headers:

At start of the application spring security prints the password e.g.: 
```
Using generated security password: cf1939e3-9b2e-46a0-976a-43580c30d41e
```

Request must use basic auth: username = user / password = cf1939e3-9b2e-46a0-976a-43580c30d41e
e.g.
```curl -X GET \
  http://localhost:8081/api/v1/models \
  -H 'authorization: Basic dXNlcjpiYTRhZjZiNS0wYzk2LTRiYmQtYjAyOC1iZDNhY2YzMGYwMWE=' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 8f433dda-4bbb-6158-0c36-d6472784328b' 
```

## Integration tests
The application has a very high test coverage - 100%, that guarantees that all the scenarios are covered.