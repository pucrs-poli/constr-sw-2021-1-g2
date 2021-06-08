# API de Recursos

# Configurações

Configuração da porta e de acesso ao banco local do mongodb.

```yml
server:
  port: 8282

spring:
  data:
    mongodb:
      auto-index-creation: true
      database: resource_db
      host: localhost
      port: 27017
```

## Endpoints

Como a API faz uso do Swagger é possível acessar a documentação via localhost através da url http://localhost:8282/swagger-ui.html. Neste link é possível ver todas as operações disponibilizadas na API de recursos. As principais são as operações que envolvem os controladores de Recursos (Resources), Tipo de Recursos (Resources-Types) e Reservation (Reservas) que está imbutida no controlador de Recursos, cada uma dessas três operações possui sua própria tabela no banco de dados
