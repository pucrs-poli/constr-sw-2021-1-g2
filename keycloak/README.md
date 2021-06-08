#  API Keycloak

## Descrição

Uma api feita utilizando Spring Boot que faz o uso de uma biblioteca chamada keycloak-admin-client, que faz o controle e funciona como um wrapper de uma api para requisições ao serviço do keycloak. Para mais informações https://javadoc.io/doc/org.keycloak/keycloak-admin-client/latest/index.html.


## Configuração

Dependências necessárias para o uso do wrapper do keycloak-admin-client, camada de segurança do Spring Boot e o uso do protocolo de autenticação (OAuth2)

```xml
<dependency>
    <groupId>org.springframework.security.oauth.boot</groupId>
    <artifactId>spring-security-oauth2-autoconfigure</artifactId>
    <version>2.0.1.RELEASE</version>
</dependency>
        
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.security.oauth.boot</groupId>
    <artifactId>spring-security-oauth2-autoconfigure</artifactId>
    <version>2.0.1.RELEASE</version>
</dependency>
```

## Propriedades de configuração

```xml

#Porta da aplicação
server.port=8082

# Se valor for falso, desabilita a camada de segurança durante o desenvolvimento local.
rest.security.enabled=false

# Configuração de CORS para permissão de acesso aos endpoints
rest.security.api-matcher=/*
rest.security.cors.allowed-origins=*
rest.security.cors.allowed-headers=*
rest.security.cors.allowed-methods=GET,POST,PUT,PATCH,DELETE,OPTIONS
rest.security.cors.max-age=3600
rest.security.issuer-uri=http://18.228.10.207:8080/auth/realms/demo

# Configuração de protocolo de autenticação
security.oauth2.resource.id=keycloak-spring-oauth2-service
security.oauth2.resource.token-info-uri=${rest.security.issuer-uri}/protocol/openid-connect/token/introspect
security.oauth2.resource.user-info-uri=${rest.security.issuer-uri}/protocol/openid-connect/userinfo
security.oauth2.resource.jwt.key-value=-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAis2qHLJAPuUG6AaqNGfiKdohHescOo45I3PsmA7IxoEanguQd9diXR71vJOMjg8zjMKiBqTXjzC1suUE6l1RPXT0iY9lTpKgangDBAEbZK+L+Y/
+6irDc4IYWfMc7HbRaLLI7G0Ofx+HCwVRqTcr1H+3A1dtjzIvBzE2f1h+0lVlYHB8HrvBy2t8JHbs3U8RjFY5ULegPkLL49vJWp6xCEgpTGT0aNAxqQ+6MGYTBdaWZ2yA8z8FHjRHDBjDwh8J4ZohZ3JSAx
mCx5dxeHWxwjZ9dN5qU9mpMk0hmxhlpWf17IugMSf5kDhL/sTL3RdYhp0LNiDn+YCz36vtrLOGJQIDAQAB-----END PUBLIC KEY-----
security.oauth2.client.client-id=keycloak-spring-oauth2-service
security.oauth2.client.client-secret=6d25fad1-f350-4e06-aee5-a67373ff1db5
security.oauth2.client.user-authorization-uri=${rest.security.issuer-uri}/protocol/openid-connect/auth
security.oauth2.client.access-token-uri=${rest.security.issuer-uri}/protocol/openid-connect/token
security.oauth2.client.scope=openid
security.oauth2.client.grant-type=client_credentials

# Configuração de acesso para acesso ao serviço do keycloak
keycloak.auth-server-url=http://18.228.10.207:8080/auth
keycloak.realm=demo
keycloak.resource=keycloak-spring-oauth2-service
keycloak.username=admin
keycloak.password=admin
keycloak.credentials.secret=6d25fad1-f350-4e06-aee5-a67373ff1db5
```

## Roles

Para acesso aos endpoints o usuário cadastrado no Keycloak deve possuir o role estipulado no endpoint, no seguinte atributo `@PreAuthorize`. Ex: No endpoint `GET /users`, o usuário deve possuir o role de user, segue anotação utilizada no endpoint para tal definição `@PreAuthorize("hasAnyAuthority('ROLE_user')`, caso o usuário logado não possua tal permissão, a API retornara um erro com código `403 Unauthorized`. Tais informações do Role e outros dados pertinentes ao usuário retornam do json web token (JWT) gerado no momento do login, e que podem ser visto/descriptografado pelo site https://jwt.io/. Caso seja necessário mapear informações adicionais do usuário ao token, é possível através da interface do keycloak admin.

## Endpoints

* GET /users (Listar todos os usuários cadastrados)
* POST /users (Criar um novo usuário)
* GET /users/{id} (Buscar um usuário pelo seu identificador)
* PATCH /users/{id} (Atualizar palavra-chave de um usuário identificado pelo seu identificador)
* GET /users/search (Buscar o usuário por diversos parâmetros)
* Delete /users/{id} (Deleter um usuário pelo seu identificador)

```java

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/users")
    @PreAuthorize("hasAnyAuthority('ROLE_user')")
    public List<UserRepresentation> getAllUsers() {
        return keycloakUserService.findAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/users")
    @PreAuthorize("hasAnyAuthority('ROLE_user')")
    public void createUser(@RequestBody UserRequest userRequest) {
        keycloakUserService.createUser(userRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/users/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_user')")
    public UserRepresentation findUserById(@PathVariable String id) {
        return keycloakUserService.findUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/users/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_user')")
    public void updatePassword(@RequestBody UserRequest userRequest, @PathVariable String id) {
        keycloakUserService.updatePassword(userRequest, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/users/search")
    @PreAuthorize("hasAnyAuthority('ROLE_user')")
    public List<UserRepresentation> findUserByParams(@RequestParam(required = false) String username, 
                                                     @RequestParam(required = false) String firstname,
                                                     @RequestParam(required = false) String lastname, 
                                                     @RequestParam(required = false) String email, 
                                                     @RequestParam(required = false) Integer first) {
        return keycloakUserService.findUserByParams(username, firstname, lastname, email, first);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(path = "/users/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_user')")
    public void deleteUser(@PathVariable String id) {
        keycloakUserService.deleteUser(id);
    }
```
