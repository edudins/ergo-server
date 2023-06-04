# ergo-server

Implement REST service based on spring-boot framework
- Separate web, service and repository levels
- Create application for person data operations
  - Find person by name
  - Find person by birthdate
  - Find all saved persons
  - Save person
  - Update person data
  - Delete person
- Service output Person object with following properties
  - Personal id
  - First name, last name
  - Gender: male, female
  - Date of birth
  - Phone number
  - E-mail
- Cover input with validations
- Throw exceptions on validations
- For data storage need to use database, e.g. H2
- Dependency manger: maven
- Log some service activities to log file and in database
- Implement unit tests

## logging

Each person service activity logs to a file in `/logs/application.log` as well as in the database.

## curl commands

**all persons**
`curl localhost:8080/persons`

**find person by first name, last name and date of birth**
`curl -G -d 'firstName=Christina' -d 'lastName=Pleasant' -d 'dateOfBirth=1999-01-01'  localhost:8080/persons/find`

**find person by individual parameters**
`curl -G -d 'firstName=Christina'  localhost:8080/persons/find`
`curl -G -d 'lastName=Pleasant'  localhost:8080/persons/find`
`curl -G -d 'dateOfBirth=1999-01-01'  localhost:8080/persons/find`

**save person**
```
curl -X POST -H "Content-Type: application/json" -d '{
  "firstName": "Lauris",
  "lastName": "Gulbis",
  "gender": "MALE",
  "dateOfBirth": "1987-01-01",
  "phoneNumber": "+37133333333",
  "email": "lauris@gulbis.lv"
}' http://localhost:8080/persons
```

**update person**
```
curl -X PUT -H "Content-Type: application/json" -d '{
  "firstName": "Christina",
  "lastName": "Bailey",
  "gender": "FEMALE",
  "dateOfBirth": "1999-01-02",
  "phoneNumber": "+37155555555",
  "email": "christinabailey@info.lv"
}' http://localhost:8080/persons/2
```

**delete person**
```
curl -X DELETE http://localhost:8080/persons/2
```
