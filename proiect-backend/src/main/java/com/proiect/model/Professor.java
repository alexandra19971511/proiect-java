package com.proiect.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
//
//--Table professors as T{
//        --  id int [pk, increment] // auto-increment
//        --  full_name varchar
//        --  course_id int [ref: < V.id] // one-to-many
//        --}

@Entity
@Table(name = Professor.TABLE_NAME)
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Professor {
  protected static final String TABLE_NAME = "professor";
  public static final String PROFESSOR_SEQ = "PROFESSOR_SEQ";
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PROFESSOR_SEQ)
  @SequenceGenerator(sequenceName = "professor_seq", initialValue = 1, allocationSize = 1, name = PROFESSOR_SEQ)
  private Long id;
  private String fullName;

  @OneToMany(
      mappedBy = TABLE_NAME
//            cascade = CascadeType.DETACH,
//            orphanRemoval = true
  )
  private Collection<Course> courses = new ArrayList<>();

  public Professor(Long id) {
    this.id = id;
  }

  public Professor(Long id, String fullName) {
    this(id);
    this.fullName = fullName;
  }

  public Professor(String fullName) {
    this.fullName = fullName;
  }

}
