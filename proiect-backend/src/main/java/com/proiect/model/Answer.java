package com.proiect.model;


//Table answers as V {
////        id int [pk]
////        answers_map varchar  // {1:5, 2:4, ..}
////        professor_id int [ref: - T.id]
////        user_id int [ref: - U.id]
//////        }

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Answer.TABLE_NAME)
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer implements Serializable {
  protected static final String TABLE_NAME = "answer";
  public static final String ANSWER_SEQ = "ANSWER_SEQ";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ANSWER_SEQ)
  @SequenceGenerator(sequenceName = "answer_seq", initialValue = 1, allocationSize = 1, name = ANSWER_SEQ)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "professor_id", nullable = false)
  private Professor professor;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @Lob
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "answer_mapping",
      joinColumns = {@JoinColumn(name = "answer_id", referencedColumnName = "id")})
  @MapKeyColumn(name = "answer_key")
  @Column(name = "answer_value")
  private Map<Integer, Integer> answers = new HashMap<>();
}
