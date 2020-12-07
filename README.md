# User Details service

## Instructions to build and run
  ```
  git clone https://github.com/netrabhatt/userdetails.git
  cd userdetails
  mvn clean install
  java -jar target/userdetails-0.0.1-SNAPSHOT.jar
  ```

## To fetch a userdetails for id 1
  `curl http://localhost:8080/api/userdetails/1`

## To update userdetails for id 1
```
curl -v -d '{"empId" : 1,  "title": "Mr.",  "firstName": "Netra",  "lastName": "BHATT",  "gender": "Male",  "address": {    "street": "123 Bverley Park",    "city": "Kogarah",    "state": "NSW",    "postcode": "2570"}}' -H "Content-Type: application/json" -X PUT http://localhost:8080/api/userdetails/1
```

## To check circuit breaker, lets call id which is not available 5 times.
  ```
  curl http://localhost:8080/api/userdetails/123
  curl http://localhost:8080/api/userdetails/123
  curl http://localhost:8080/api/userdetails/123
  curl http://localhost:8080/api/userdetails/123
  curl http://localhost:8080/api/userdetails/123
  ```
Within 5 calls, circuit breaker will open and following will be received as response
*CircuitBreaker 'circuitBreaker' is OPEN and does not permit further calls*
