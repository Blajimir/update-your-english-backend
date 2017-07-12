package ru.updateyoureng.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.updateyoureng.model.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
