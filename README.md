WeatherForecast-API

This project uses openweathermap API and returns the average temperature (daily and nightly) and pressure of a given city

### Prerequisites
* Java 1.8 or above
* Maven

## Running
Run the project by execute the command:
```$xslt
$ mvn spring-boot:run
```

While running the project you can access the following url (using GET method) in order to test it manually:
```
http://localhost:8080/data/<SOME_CITY>
```

## Built With

* [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - The language used
* [Spring Boot 2.1.1](http://spring.io/projects/spring-boot) - The web framework used
* [Lombok](https://projectlombok.org/)- Lombok is used to reduce boilerplate code for model/data objects,
* [Maven](https://maven.apache.org/) - Dependency Management
* [Swagger](https://swagger.io/) - API Documentationmvn spring-boot:run


## Swagger specs :

Swagger usually helps in the smart way of having technical documentation of the API. Please refer to the Swagger specs at http://localhost:9067/swagger-ui.html. URL . At this URL you will know the structure of the request (which is te http GET request) and the JSON response structure as well. You will also be able to hit the request from the swagger and get the json response back on the UI and play with it. The app is currently configured to run on the port 9067 of the localhost ( In case you are running it on the local server ), however you are free to change it anytime you need ( In case you wish to deploy it to a different server ) by updating it in the application.properties file.

How does the API get the weather data?

The application connects to https://openweathermap.org with *bintray-xsavikx-openweathermap-java-api* lib for obtaining the weather forecast.

How to access the API?

Also as mentioned on the swagger specs, there currently exposed is a GET API with a parameter cityName

Below is the sample URL of the API exposed in order to get the weather forecast parameters. Below URL can find the average weather forecast for 3 days of Bangalore. Please feel free to update these parameters and get the weather data of cities.

URL : http://localhost:9067/weather/v1/data/Bangalore

Apart from the URL you will have to add appropriate header ex : Accept : application/json. in the request.

## Swagger ui

Please refer the Swagger ui for the response json structure.

http://localhost:9067/swagger-ui.html
