# Spring_Boot_Auth_JWT<br>
    This project is an application to provide JWT authentication.<br>
Please follow this steps :<br>
1. add the app_role.sql to your database OR try to inser maually these roles : ADMIN, USER
2. run the application
3. register a new user via a POST http request to /users with the following request body :<br>
 Example: 
 ```json
         {
	          "username": "test4",
	          "password": "0123456789",
	          "confirmPassword": "0123456789"
          }
 ```
 4. get your token by logging via a POST http request to /login with the following request body :<br>
 Example: 
 ```json
         {
	          "username": "test4",
	          "password": "0123456789"
          }
 ```
 5. the response Authorization header contains your Json Web Token<br>
 Example:
    <div>Authorization:  Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MyIsImV4cCI6MTU4NTY3Nzg1Niwicm9sZXMiOlt7ImF1dGhvcml0eSI6IlVTRVIifV19.WXuh77x8bsJ3ZxlfheftZRZ3Ins_8dAG26ucmlZQVoiBw5lCUBcgrEFGxzwDHbo1fWp5wAcIukC83KWbLZ_JCQ</div>
