# ergo-server

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