package ru.updateyoureng.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="user_vocabulary")
@Data
@NoArgsConstructor
public class UserWord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account account;
    private float rate;
}
