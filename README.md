# carRentalApp

A backend of application of car rental company. There is a several REST endpoint as add client, car, obtain client list and car list, editing, removing, renting and returning a cars. 
REST API documentation is available on a shared swagger docs, which is available under "localhost:8080/swagger-ui.html" - also link is provided on the landing page.

## Instructions
Clone
--------

```sh
git clone https://github.com/wojdeb/carRentalApp.git
```

## To run the webapp manually

```
mvn spring-boot:run
```

and navigate your browser to  http://localhost:8080/ - There is a landing page on this address

## To run integration tests

```
mvn spring-boot:run
mvn verify
```
