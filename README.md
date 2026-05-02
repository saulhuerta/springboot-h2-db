# SpringBoot RESTfull services with H2DB

## Methods
#### GET http://localhost:8080/api/departments

#### GET http://localhost:8080/api/employees

#### POST http://localhost:8080/api/employees
```json 
    { 
	    "firstName": "Juanito", 
	    "lastName": "Perez", 
	    "email": "j.p@dev.com", 
	    "departmentName": "Engineering" 
    }
```
#### POST http://localhost:8080/api/departments
```json 
    { 
	    "name": "Cloud Operations"
    }
```
