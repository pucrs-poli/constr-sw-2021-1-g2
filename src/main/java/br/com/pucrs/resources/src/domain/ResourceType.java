package br.com.pucrs.resources.src.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

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
