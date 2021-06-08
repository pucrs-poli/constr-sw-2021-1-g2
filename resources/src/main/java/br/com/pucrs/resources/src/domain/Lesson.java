package br.com.pucrs.resources.src.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    private String _id;

    private Lesson lessonID;

    private String name;

    private String course;

    private String room;
}
