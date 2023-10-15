# Lit-Market

Lit-Market is an online bookstore powered by a robust REST API built with Java, Hibernate, Spring Boot, Spring Security, Liquibase, Lombok, and MapStruct. This platform allows users to explore, discover, and purchase books seamlessly.

## Key Features
* Book Management: Users can create, manage, and list books. Each book can include details such as title, author, description, and price.

* Category Organization: Books are categorized for easy navigation. Users can browse books by genres, topics, or any other relevant categories.

* Shopping Cart: The Lit-Market API enables users to add books to their shopping cart. This feature simplifies the shopping experience, allowing users to review their selections before making a purchase.

* Order Processing: Users can place orders and complete the purchase process, making Lit-Market a fully functional online bookstore.

## Technologies Used
* Java: The project is built using the Java programming language.

* Spring Boot: Lit-Market leverages Spring Boot for efficient application development.

* Spring Security: Security is implemented through Spring Security, ensuring that user data remains protected.

* Hibernate: The Hibernate framework is used for managing database operations.

* Liquibase: Database version control and schema management are handled by Liquibase.

* Lombok: Lombok simplifies Java code by reducing boilerplate code.

* MapStruct: MapStruct simplifies object mapping and transformation.

## Installation and Running Lit-Market in Docker
### Install Docker:

Ensure that you have Docker installed on your system. You can download Docker from the official Docker website.

### Build the Docker Image:

Navigate to the directory of your project where the Dockerfile is located and run the following command to build the Docker image. For example:

 ```
  docker build -t litmarket .
```
This will create a Docker image with the name "litmarket."

### Add an .env File:

Create a file named .env in the root directory of your project. In this file, you can define environment variables required to configure your application. For example:

```
MYSQL_ROOT_PASSWORD=root
MYSQL_USER=root
MYSQL_PASSWORD=root
MYSQL_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
MYSQL_URL=jdbc:mysql://docker-mysql:3306/my-book-store
MYSQL_LOCAL_PORT=3308
MYSQL_DOCKER_PORT=3306

SPRING_LOCAL_PORT=8081
SPRING_DOCKER_PORT=8080
DEBUG_PORT=5005
Replace the placeholder values with your actual database connection details.
```
### Run the Docker Container:

Use docker-compose to start your application. In the terminal, run:

```
docker-compose up
```
This will launch the containers for your application and database according to the docker-compose.yml configuration.

After following these steps, your "Lit-Market" application should be accessible at the specified address. Ensure that Docker is running and your Docker image is successfully built and launched.

DON'T forget to create Mysql schema!

## License
This project is licensed under the MIT.