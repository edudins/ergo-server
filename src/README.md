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

