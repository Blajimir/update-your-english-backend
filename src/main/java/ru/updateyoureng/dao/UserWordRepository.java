package ru.updateyoureng.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.updateyoureng.model.UserWord;

public interface UserWordRepository extends JpaRepository<UserWord,Long> {
}
