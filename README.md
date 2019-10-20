# spring-web-service
This is a bootstraped webservice project using spring boot

The flyway script will initialize a database with a user Table, you could run resources/db/demo/demo_users.sql to insert your first admin, the password of that admin is password hashed with bcrypt(10).

# What's included
## libraries
- spring boot
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-actuator
- spring-boot-starter-hateoas
- spring-boot-starter-security
- spring-security-oauth2-autoconfigure (2.1.9.RELEASE)
- spring-security-oauth2 (2.3.5.RELEASE)
- spring-security-jwt (1.0.10.RELEASE)
- mariadb java client
- swagger ui (springfox 2.9.2)
- spring-boot-devtools
- spring-boot-starter-test
- spring-security-test
- querydsl-apt
- querydsl-jpa
- jackson-dataformat-xml
- modelmapper

## Configurations
- authentication oauth2 with password flow (since our backend is the 1st party app), its up to you whenever you want to forward the refresh token to the SPA client or not. This could be easily achieve by setting refresh token to null in AuthService.
- flyway: already configured, first script is version 0.0.1
- jgitflow: you will need to configure it to your own repo for making a release version
- audit: done by using mappedsuperclass and AuditingEntityListener

## Rest example
There is an insomnia file that you could directly use and try

### login
request
```
> POST /yourcompany/api/v1/auth/login HTTP/1.1
> Host: localhost:8080
> User-Agent: insomnia/7.0.1
> Content-Type: application/json
> Accept: */*
> Content-Length: 73

| {
| 	"username" : "admin@yourcompany.whatever",
| 	"password" : "password"
| }
```

response
```
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbkB5b3VyY29tcGFueS53aGF0ZXZlciIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE1NzE1MzM1NzYsInVzZXJJZCI6MSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjE3MDVmYTgyLTgxYzMtNGI1ZS04YTA5LTQ2NTNlYThhNDVhMCIsImNsaWVudF9pZCI6ImFwcHMtY2xpZW50In0.HX0BL_jlI0tPUAhBjTt9K0XsjoDd194KeoUZKoPmq0M",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbkB5b3VyY29tcGFueS53aGF0ZXZlciIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiIxNzA1ZmE4Mi04MWMzLTRiNWUtOGEwOS00NjUzZWE4YTQ1YTAiLCJleHAiOjE1NzQxMjE5NzYsInVzZXJJZCI6MSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjZhNGMxYzA1LWRmZWYtNGMzMS04MTFjLWU2ZmY5NTAzOGZkYiIsImNsaWVudF9pZCI6ImFwcHMtY2xpZW50In0.owEk5oXD4ZzW8R5FZKd7WpUiAqmiFe78jGIUbmyqxLs",
  "expires_in": 3594,
  "scope": "read write",
  "userId": 1,
  "jti": "1705fa82-81c3-4b5e-8a09-4653ea8a45a0"
}
```

### authentication
request
```
> GET /yourcompany/api/v1/users/1/authentication HTTP/1.1
> Host: localhost:8080
> User-Agent: insomnia/7.0.1
> Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbkB5b3VyY29tcGFueS53aGF0ZXZlciIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE1NzE1MzM1NzYsInVzZXJJZCI6MSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjE3MDVmYTgyLTgxYzMtNGI1ZS04YTA5LTQ2NTNlYThhNDVhMCIsImNsaWVudF9pZCI6ImFwcHMtY2xpZW50In0.HX0BL_jlI0tPUAhBjTt9K0XsjoDd194KeoUZKoPmq0M
> Accept: */*

```

response
```
{
  "authorities": [
    {
      "authority": "ROLE_USER"
    }
  ],
  "details": {
    "remoteAddress": "0:0:0:0:0:0:0:1",
    "sessionId": null,
    "tokenValue": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbkB5b3VyY29tcGFueS53aGF0ZXZlciIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE1NzE1MzM1NzYsInVzZXJJZCI6MSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjE3MDVmYTgyLTgxYzMtNGI1ZS04YTA5LTQ2NTNlYThhNDVhMCIsImNsaWVudF9pZCI6ImFwcHMtY2xpZW50In0.HX0BL_jlI0tPUAhBjTt9K0XsjoDd194KeoUZKoPmq0M",
    "tokenType": "Bearer",
    "decodedDetails": {
      "user_name": "admin@yourcompany.whatever",
      "scope": [
        "read",
        "write"
      ],
      "exp": 1571533576,
      "userId": 1,
      "authorities": [
        "ROLE_USER"
      ],
      "jti": "1705fa82-81c3-4b5e-8a09-4653ea8a45a0",
      "client_id": "apps-client"
    }
  },
  "authenticated": true,
  "userAuthentication": {
    "authorities": [
      {
        "authority": "ROLE_USER"
      }
    ],
    "details": null,
    "authenticated": true,
    "principal": "admin@yourcompany.whatever",
    "credentials": "N/A",
    "name": "admin@yourcompany.whatever"
  },
  "principal": "admin@yourcompany.whatever",
  "oauth2Request": {
    "clientId": "apps-client",
    "scope": [
      "read",
      "write"
    ],
    "requestParameters": {
      "client_id": "apps-client"
    },
    "resourceIds": [],
    "authorities": [],
    "approved": true,
    "refresh": false,
    "redirectUri": null,
    "responseTypes": [],
    "extensions": {},
    "grantType": null,
    "refreshTokenRequest": null
  },
  "clientOnly": false,
  "credentials": "",
  "name": "admin@yourcompany.whatever"
}
```