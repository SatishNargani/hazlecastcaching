# Spring Boot CRUD Application with Hazelcast Caching

A Spring Boot application demonstrating **CRUD operations using Java 17, Spring Boot, Spring Data JPA, MySQL, and Hazelcast distributed caching**.

This project is created to demonstrate how caching can be integrated into a Spring Boot application to improve application performance and reduce unnecessary database calls.

## 🚀 Technologies Used

* **Java 17**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **MySQL**
* **Hazelcast**
* **Spring Cache**
* **Maven**
* **REST APIs**
* **Git & GitHub**

## 📌 Features

* Create, Read, Update, and Delete (CRUD) operations
* RESTful APIs using Spring Boot
* Database persistence using Spring Data JPA
* MySQL database integration
* Hazelcast caching
* Cache data for frequently accessed records
* Cache eviction when data is updated or deleted
* Exception handling
* Layered architecture
* Maven-based project

## 🏗️ Project Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL Database
```

Hazelcast caching is integrated at the service layer:

```text
Client
   ↓
REST Controller
   ↓
Service Layer
   ↓
Hazelcast Cache
   ↓
Repository
   ↓
MySQL
```

For a READ operation:

```text
Client
  ↓
GET API
  ↓
Service
  ↓
Check Hazelcast Cache
  ↓
 ┌───────────────┐
 │ Cache Hit?    │
 └───────┬───────┘
      Yes│       │No
         ↓       ↓
     Return    Repository
      Data        ↓
              MySQL DB
                  ↓
             Store in Cache
                  ↓
              Return Data
```

## ⚡ Hazelcast Caching

Hazelcast is used to cache frequently requested data.

The basic flow is:

1. Client sends a GET request.
2. Application checks Hazelcast cache.
3. If the data is available in the cache, it is returned directly.
4. If the data is not available, the application queries MySQL.
5. The retrieved data is stored in Hazelcast.
6. Subsequent requests can be served from the cache.

### Cache Update

When an entity is updated:

```text
PUT Request
   ↓
Update Database
   ↓
Update / Evict Cache
```

When an entity is deleted:

```text
DELETE Request
   ↓
Delete from Database
   ↓
Evict Cache
```

This helps prevent stale data from remaining in the cache.

## 🔧 CRUD Operations

The application provides APIs for the following operations:

| Operation | HTTP Method | Description               |
| --------- | ----------- | ------------------------- |
| Create    | POST        | Create a new record       |
| Read      | GET         | Retrieve records          |
| Update    | PUT         | Update an existing record |
| Delete    | DELETE      | Delete a record           |

### Example APIs

```text
POST   /api/products
GET    /api/products
GET    /api/products/{id}
PUT    /api/products/{id}
DELETE /api/products/{id}
```

> Update the endpoint names above according to the actual controller mappings in the project.

## 🗄️ Database Configuration

The application uses **MySQL** as the relational database.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jpa_one_to_many
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace the database name, username, and password with your local configuration.

## 🔥 Hazelcast Configuration

Hazelcast is used as the caching provider for the application.

The application can use Spring's caching abstraction to simplify cache management.

Typical cache operations include:

```java
@Cacheable
@CachePut
@CacheEvict
```

Example:

```java
@Cacheable(value = "products", key = "#id")
public Product getProductById(Long id) {
    return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
}
```

Update operation:

```java
@CachePut(value = "products", key = "#id")
public Product updateProduct(Long id, Product product) {
    // Update logic
}
```

Delete operation:

```java
@CacheEvict(value = "products", key = "#id")
public void deleteProduct(Long id) {
    // Delete logic
}
```

## 📂 Project Structure

```text
src
 └── main
     ├── java
     │   └── com.example
     │       ├── controller
     │       ├── service
     │       ├── repository
     │       ├── entity
     │       ├── config
     │       └── Application.java
     │
     └── resources
         ├── application.properties
         └── ...
```

## ▶️ How to Run the Application

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
```

### 2. Open the project

Open the project in:

* IntelliJ IDEA
* Eclipse
* VS Code

### 3. Configure MySQL

Create the required MySQL database:

```sql
CREATE DATABASE jpa_one_to_many;
```

Update the database credentials in:

```text
src/main/resources/application.properties
```

### 4. Build the project

Using Maven:

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

Or run the main Spring Boot application class directly from your IDE.

## 🧪 Testing the APIs

You can test the REST APIs using:

* Postman
* Insomnia
* cURL
* Swagger, if configured

Example:

```bash
curl http://localhost:8080/api/products/1
```

## 📈 Why Hazelcast?

Without caching:

```text
Client → Application → MySQL
Client → Application → MySQL
Client → Application → MySQL
Client → Application → MySQL
```

With Hazelcast:

```text
Client → Application → Hazelcast
                         ↓
                    Cache Hit
```

Caching can reduce database load and improve response time for frequently accessed data.

## 🎯 Learning Objectives

This project demonstrates practical implementation of:

* Java 17 features
* Spring Boot application development
* RESTful API development
* Spring Data JPA
* Hibernate
* MySQL integration
* CRUD operations
* Spring Cache abstraction
* Hazelcast caching
* Cache eviction and update strategies
* Layered application architecture

## 🔮 Future Enhancements

Possible enhancements for this project:

* Add Swagger / OpenAPI documentation
* Add Spring Boot Actuator
* Add unit and integration tests
* Add Docker support
* Add centralized exception handling
* Add validation using Bean Validation
* Add Spring Security / JWT authentication
* Add pagination and sorting
* Add distributed Hazelcast cluster configuration
* Add CI/CD using GitHub Actions

## 👨‍💻 Author

**Satish Nargani**

Java Backend Developer | Technical Lead

### Core Skills

```text
Java | Spring Boot | Microservices | REST APIs
Spring Data JPA | Hibernate | MySQL | PostgreSQL
AWS | Azure | Docker | Kubernetes
Kafka | Hazelcast | CI/CD
```

---

