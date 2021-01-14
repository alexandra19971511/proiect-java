package com.proiect.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static com.proiect.model.Course.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course implements Serializable {
    private static final String COURSE_SEQ = "COURSE_SEQ";
    protected static final String TABLE_NAME = "course";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = COURSE_SEQ)
    @SequenceGenerator(sequenceName = "course_seq", initialValue = 1, allocationSize = 1, name = COURSE_SEQ)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    public Course(Long id) {
        this.id = id;
    }

    public Course(Long id, String name) {
        this(id);
        this.name = name;
    }


}
