package ru.updateyoureng.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.updateyoureng.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
