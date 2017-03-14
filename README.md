# RankingBackend

### Prerequisites:
  - Java8
  - Maven
  - MongoDB

### Assumptions
RankingBackend handles ONLY ratings. All information about e.g. movie title or movie id should be taken from other service.
For instance:
If you'd like to get list of ranking within "comedy" category, you have to get list of movies' ids that are in this category from some external service and pass just these ids to RankingBackend API.

### Running
The application needs a MongoDB instance. Default configuration is:
```sh
host: localhost
port: 27017
```
It can be changed in application.properties file.

Before running, please execute unit and integration tests to ensure proper working:
```sh
mvn test
```
To get the server up and running (on localhost, port 8080):
```sh
mvn spring-boot:run
```

### Further development
The service is ready for handling some other rankings, i.e. games (mentioned in requirements) almost ad hoc. All is has to be done is to specify a new type in Type class, and to write appropiate controllers (or create generic ones to handle multiple types – the Type is already being passed in requests).
