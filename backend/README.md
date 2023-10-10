# Login Application (DXC)

Backend for login application

## Prerequisites

Before you start, make sure you have the following prerequisites:

- Java Development Kit (JDK) installed (e.g., OpenJDK or Oracle JDK).
- Apache Maven build tool installed.
- MySQL server installed and running.

## Config Setup
Update application.properties for your mySQL username and password

## Database Setup

### Create a MySQL Database

1. Log in to your MySQL server using the MySQL client.
2. Create database
```
CREATE DATABASE DXC;
```
3. Create roles (perform this step after running the app)
```
    USE DATABASE DXC;
    INSERT INTO ROLE VALUES (1, "USER");
    INSERT INTO ROLE VALUES (2, "MANAGER");
```


### Running the app
1. Change to the directory
```
cd ./backend
```
2. Run the app
```
mvn spring-boot:run
```
   