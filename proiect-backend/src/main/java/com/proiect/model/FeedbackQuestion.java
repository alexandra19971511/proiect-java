package com.proiect.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.proiect.model.FeedbackQuestion.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class FeedbackQuestion implements Serializable {
    private static final String FEED_QUE_SEQ = "FEED_QUE_SEQ";
    protected static final String TABLE_NAME = "FEEDBACK_QUESTION";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = FEED_QUE_SEQ)
    @SequenceGenerator(sequenceName = "feedback_ques_seq", initialValue = 1, allocationSize = 1, name = FEED_QUE_SEQ)
    private Long id;
    private String question;

    public FeedbackQuestion(String question) {
        this.question = question;
    }

    public FeedbackQuestion(Long id, String question) {
        this.id = id;
        this.question = question;
    }
}
