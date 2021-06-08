package br.com.pucrs.resources.src.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

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
