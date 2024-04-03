# Login Via google

# Technologies
Java 1.8, 
Spring-core (5.3.29), Spring-MVC (5.3.29),Spring-Security (5.8.6), Spring AOP(Aspect Oriented Programming)(5.3.29),
MySql, JPA(2.3.0.RELEASE),
HTML, CSS, JSTL, MAVEN, 
Flyway-db Library,
Tomcat > 9.x,
Jwt token (0.12.5)

# Flyway?
Flyway library use to execute db scripts at the time of application start. It helps to migrate database. 

# AOP
AOP is an approach to programming that allows global properties of a program to determine how it is compiled into an executable program

# JPA Cache
cache enabled for user table on findByUsename method

# log4j2

Log4j2 support added to log error,info etc

# OAuth 2.0 (Bearer token)
OAuth2 enabled you can get/see Bearer token after login or get token using below API

METHOD: GET

	http://localhost:8080/spring-jpa-login-via-google/get-token
	UserName: testt01021990@gmail.com
	password: admin@123
	

# CRUD Operation on Person class 
you can find postman script in resource folder of webapp module

CREATE Method:POST

	http://localhost:8080/spring-jpa-login-via-google/person
	{ "lastName": "ooasssssss", "firstName": "aaaaaaaa", "age":"30" }
	
UPDATE Method:PUT

	http://localhost:8080/spring-jpa-login-via-google/person
	{ "lastName": "ooasssssss", "firstName": "aaaaaaaa", "age":"30", "id":"1" }
	
DELETE Method:DELETE

	http://localhost:8080/spring-jpa-login-via-google/person/1
	
GET All Record Method:GET

	http://localhost:8080/spring-jpa-login-via-google/person/all

# ExceptionHandler
Added controller advice to handle all type of exception and based on that return speciefic object

Testing Method: GET

	http://localhost:8080/spring-jpa-login-via-google/person/aop/testing/1 
	http://localhost:8080/spring-jpa-login-via-google/person/aop/testing/1
