package ru.updateyoureng.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.updateyoureng.model.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
    Word findWordByValueEquals(String word);
}
