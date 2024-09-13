# Price API
This is a simple RESTful API for Get price in time intervals, built with Spring Boot and h2.
The API responds with JSON and handles error messages in a consistent

## Features

- Return price details 
- Handle errors and return appropriate messages
- Swagger integration for API documentation
- focused on hexagonal architecture

## Requirements

- Java 17
- Maven

## Deployment

### Clone the Repository.
### Update dependencies.
### Execute the following maven commands
### mvn clean
### mvn install

## CURL 
curl --location 'http://localhost:8080/api/prices/search?applicationDate=2023-08-14%2021%3A00%3A00&productId=35455&brandId=1'