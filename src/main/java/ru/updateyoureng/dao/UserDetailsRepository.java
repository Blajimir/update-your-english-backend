package ru.updateyoureng.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.updateyoureng.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {
    UserDetails findByAccount_Login(String login);
}
