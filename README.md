# reto-ecommer
# Spring Web API REST with H2 Database with architecture hexagonal

This is a simple example of a Spring Web 3.1.2 API REST application that uses an H2 in-memory database. The application demonstrates basic CRUD (Create, Read, Update, Delete) operations for a resource.

## Prerequisites

- Java 17 or higher
- Maven

## Technologies Used

- Spring Boot 3.1.2
- Spring Web
- H2 Database (In-memory)

## Getting Started

1. Clone the repository:
   git clone https://github.com/alfredjava/retoxcale
   cd retoxcale
2. Build the project:
   mvn clean package
3. Run the application:
4. mvn spring-boot:run

   The application will start on `http://localhost:8080`.

   ## API Endpoints

   The following are the available API endpoints:

- **List All Product**
- GET /api/product
    - 
- **List cart**
- GET /api/cart/1

- **saveProduct**
- POST /api/product
    - Request Body:
      {
      "name":"Iphone 13",
      "description":"Iphone 13",
      "stock":10,
      "price": 100000
      }
- - **saveCart**
- POST /api/cart
    - Request Body:
      {
      "user": {
      "email": "alfredfis@gmail.com"
      },
      "cartDetails": [
      {
      "product": "iphone13",
      "quantity": 3
      },
      {
      "product": "iphone14",
      "quantity": 4
      }
      ]
      }
## Descargar collection de postman
[retoXCALE.postman_collection.json](retoXCALE.postman_collection.json)
## Diagrama
// insertar diagrama de arquitectura hexagonal front resources directory
![ecomerdiag.jpg](ecomerdiag.jpg)

