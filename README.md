Spring Boot Microservices
=========================

This is a simple microservices appications that demonstrate how to implement service discovery through Eureka.

There are simple two microservices projects.

Eureka Server
-------------

To start Eureka server, into `eureka-server` folder, type: 
```
$ mvn clean spring-boot:run
```
The server will be available in 9001 port. Just point out your browser `http://localhost:90001`.

Zuul Server
-----------
To start Zuul server, into `zuul-server` folder, type:
```
$ mvn clean spring-boot:run
```
The server will be available in 9002 port.

Grupos and Contatos microservice
--------------------------------
These are two simple implementation of microservices. They're using Spring Reactor to implement some reactive concepts using request/reply pattern.

To start each microservice, inside of project folder, just type:
```
$ mvn clean spring-boot:run
```
You will see each microservice will register itlsef in Eureka server automatically.

Any feedback will be welcome.
