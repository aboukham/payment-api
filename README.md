# payment-api
Development of  REST service to create a payment, send a notification to customer, and show the state of the payment. The API should be able to accept a Return for the Payment as well and match it to the original payment. This project includes  logging and monitoring features.

# Payment Service

The Payment Service is a Spring Boot application providing RESTful endpoints for creating payments, processing returns, and checking payment status. The service uses an in-memory H2 database for simplicity.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [Endpoints](#endpoints)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Configuration](#configuration)
- [Logging](#logging)
- [Monitoring](#monitoring)
- [Testing](#testing)


## Getting Started

### Prerequisites

- Java 8 or later
- Spring boot 3.0 or later
- Maven (for building and managing dependencies)

### Installation

1. Clone the repository by using this command:
   git clone https://github.com/aboukham/payment-api

2. Navigate to the project directory by using this commands:
   cd payment-api
   cd server
   Then open this diroctory in your IDE
3. Run the application by clicking the run icon on your IDE or use this command:
   java -jar target/payment-api.jar
   
## Usage

### Endpoints
1. Create Payment and send notification to customer:
  URL: POST http://localhost:8080/api/payment/create?email={customerEmail}
  Request Body: {"id":1 "amount": 100.0 }
2. Return Payment and send notification to customer:
 URL: POST http://localhost:8080/api/payment/create?email={customerEmail}
  Request Body: {"id":1 "amount": 100.0 }
Get Payment Status:
URL:  GET http://localhost:8080/api/payment/{id}

## Project Structure

payment-api
|- server
|-- src
|   |-- main
|       |-- java
|           |-- com.example.paymentAPI
|               |-- advice(Exception handling)
|               |-- aop (messaging, logging, monitoring)
|               |-- controller
|               |-- dao
|               |-- domain
|               |-- dto
|               |-- service
|               |-- PaymentApiApplication
|-- resources
|-- test

|- client(Testing the API using RestTemplate)
|-- src
|   |-- main
|       |-- java
|           |-- com.example.PaymentApiClient
|               |-- PaymentApiClientApplication
|               |-- PaymentDto
|               |-- RestTemplateConfig
|-- resources
|-- test

## Dependencies

Spring Web
Spring JPA
LomboK
H2 Database
Spring Boot Actuator
Java Mail

## Configuration

Connect to h2 database: check the application.properties file inside ressources folder to get all the information needed to connect to the User Interface of H2 database.

## Logging and Monitoring

Used Spring actuator, ObservationHandeler and Observation Registry to log the execution time of each method in the service. Allowing all actuator endpoints in application.properties file provides all the information about the service.

## Testing

For testing you can use postman and send the above endpoints woth your input or open the client folder on IDE and run it, you will see the result of some test cases provided in this service to test all features.

