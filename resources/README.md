# API de Recursos

## Collections do postman

collection aws: https://drive.google.com/file/d/1buE-gYBGSnxDDlANUpVO0DTCS7uhkb_M/view?usp=sharing (Requests já prontos)

collection local: https://drive.google.com/file/d/1AIMQPXpgoFVBtsTow1JWD-902juYctq6/view?usp=sharing (Requests já prontos)

### TODOS
- [ ] Diagrama de classes
- [ ] Terminar testes das rotas
- [X] Configuração do mongodb no AWS
- [X] Configuração do API no AWS
- [ ] Ajustar bugs na validação de reservas
- [ ] Consumir microserviços de lessons

## Documentação

Documentação via AWS através da url http://ec2-54-232-199-57.sa-east-1.compute.amazonaws.com/swagger-ui.html

Documentação via localhost através da url http://localhost:8282/swagger-ui.html

## Configurações

Dependência do banco de dados do mmongodb

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>
```
Configuração da porta e de acesso ao banco configurado no ATLAS https://www.mongodb.com/cloud/atlas.


```yml
server:
  port: 8282

spring:
  data:
    mongodb:
      auto-index-creation: true
      uri: mongodb+srv://user:user@resources-db.y16jp.mongodb.net/resources-db?retryWrites=true&w=majority
```

## Endpoints

Como a API faz uso do Swagger é possível acessar a documentação via localhost através da url http://localhost:8282/swagger-ui.html. Neste link é possível ver todas as operações disponibilizadas na API de recursos. As principais são as operações que envolvem os controladores de Recursos (Resources), Tipo de Recursos (Resources-Types) e Reservation (Reservas) que está imbutida no controlador de Recursos, cada uma dessas três operações possui sua própria tabela no banco de dados

## Estrutura de pastas

* `config`: configurações adicionais, como beans, etc.
* `domain`: todas as classes criadas para o domínio da aplicação
* `dto`: requests enviadas pelo postman e as responses obtidas do postman
* `handler`: captura exceções lançadas no código
* `repository`: interfaces de repositório das entidades para serem salvas no banco
* `service`: serviços das operações realizadas no banco
* `validator`: validações das requests enviadas pelo postman para alguma rota
* `web.controller`: controllers/rotas da aplicação

![estrutura](https://i.ibb.co/w7vK7BJ/dir.jpg)

## Entidades
#### Reservas

```java
@Data
@Builder
@ToString(exclude = "_id")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Reservation")
@CompoundIndex(def = "{'day': 1, 'schedule': 1}")
public class Reservation implements Serializable {

    @Id
    private String _id;

    @Field(name = "lesson_id)")
    private String lessonID;

    private String reason;

    @DBRef
    private Resource resource;
    
    private LocalDate day;

    private Schedule schedule;  
```

#### Recursos

```java
@Data
@Builder
@ToString(exclude = "_id")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Resource")
public class Resource implements Serializable {

    @Id
    private String _id;

    private String name;

    private String description;

    @DBRef
    @Field("type")
    private ResourceType resourceType;
}
```

#### Tipos de Recursos

```java
@Data
@Builder
@ToString(exclude = "_id")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ResourceType")
public class ResourceType implements Serializable {

    @Transient
    public static final String RESOURCE_TYPE_SEQUENCE = "resource_type_sequence";

    @Id
    private String _id;

    @Setter
    @Indexed(unique = true)
    private String name;

    @Setter
    private String description;
}
```
