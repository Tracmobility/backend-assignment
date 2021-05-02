# TracMobility Backend Assignment

An RESTful API for the software assessment. It is build with Java, and Spring Boot. For testing the application I used Junit 5, Mockito and AssertJ.

### Requirements

You have been tasked with building part of a simple vehicle system that allows
customers to borrow vehicles. Your code should provide the following functionality:
* List all available vehicles.
* Borrow and return vehicle. 

## Design

For the development of the architecture I have used a concept of OOP and SOLID principles. By following these concepts
I wanted to achieve strong encapsulation and improve the maintainability and extensibility of the project.

I designed the application to consist of five modules: 

* Controllers  
* Services 
* Repositories 
* Models
* Exceptions - this module consist only of the custom exception classes. 

### Controllers

The controller class is created in a way to keep it simple and without any complicated logic. That way,
the controller class is only responsible for receiving the request and passing it to the service, where all the business logic happen.

In the same package, there is a separate class designed specifically for error handling. By this class, I handle custom errors
for Service and Repository layers.

### Models

During the designing of the models, I tried to keep strong encapsulation.
By moving all classes related to the Vehicle into a separate package and declare all instance variables of the Vehicle class as default, I was able to 
expose only minimum information outside the package. 

To communicate outside the API there are separate DTO classes. DTO classes contains only minimum information related to the request and
have timestamped of the specific action performed on the vehicle. To accomplish requirements, I created only DTO for renting, returning and listing vehicles.  

### Repositories

One of the requirement is not to use JDBC. To meet this requirement I created VehicleRepository class with instance Variable of type
List to mimic the database. Inside the class, I implemented only methods necessary to achieve assessment requirements.

###Services

The only service used in the application is VehicleService class. The service contains all the logic to manipulate the vehicle object.

##Test
To test my application, I created interface Fixtures under package named fixtures with valid data for tests. 

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/KonarzewskiP/backend-assignment-1.git
```

**2. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>. You can test it in Postman at <http://localhost:8080/swagger-ui.html>. 
To be able to test it in Postman, you have to uncomment the hardcoded data in the VehicleRepository class. 
