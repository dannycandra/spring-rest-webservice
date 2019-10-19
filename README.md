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
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzE1MTg5MjAsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6Ijk3OTNhZjg2LWVjYTEtNDgzMS1hOTFkLWE0Y2Y0ZDgzMzRmYyIsImNsaWVudF9pZCI6ImFwcHMtY2xpZW50Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.VrEIF7aJOP6bEGGdW3tP_toG-ND309FzhvKhp9gD3ZY",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiI5NzkzYWY4Ni1lY2ExLTQ4MzEtYTkxZC1hNGNmNGQ4MzM0ZmMiLCJleHAiOjE1NzQxMDczMjAsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJmOWUxMWRiNi04MWEyLTRjZTMtYWRjZi03NWRiYzc0NGZmZjAiLCJjbGllbnRfaWQiOiJhcHBzLWNsaWVudCJ9.v_wNGmjRZXGlzFEHMtQcrKVlNXjuWleGPN3CXTbB0SE",
  "expires_in": 3598,
  "scope": "read write",
  "jti": "9793af86-eca1-4831-a91d-a4cf4d8334fc"
}```

### authentication
request
```
> GET /yourcompany/api/v1/users/1/authentication HTTP/1.1
> Host: localhost:8080
> User-Agent: insomnia/7.0.1
> Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzE1MjE3OTAsInVzZXJfbmFtZSI6ImFkbWluQHlvdXJjb21wYW55LndoYXRldmVyIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjJlMWM5MDFiLTY4NTMtNGFlZi04ZDljLTM5MTkwMGRlYTZmMiIsImNsaWVudF9pZCI6ImFwcHMtY2xpZW50Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.aNUj2ywSQusbwGBOxXAekF6pfvp8c-emGx88XLdahQU
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
    "tokenValue": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzE1MjE3OTAsInVzZXJfbmFtZSI6ImFkbWluQHlvdXJjb21wYW55LndoYXRldmVyIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjJlMWM5MDFiLTY4NTMtNGFlZi04ZDljLTM5MTkwMGRlYTZmMiIsImNsaWVudF9pZCI6ImFwcHMtY2xpZW50Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.aNUj2ywSQusbwGBOxXAekF6pfvp8c-emGx88XLdahQU",
    "tokenType": "Bearer",
    "decodedDetails": null
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
  "credentials": "",
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
    "refreshTokenRequest": null,
    "grantType": null
  },
  "clientOnly": false,
  "name": "admin@yourcompany.whatever"
}
```