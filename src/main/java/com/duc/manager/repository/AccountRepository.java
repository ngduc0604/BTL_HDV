package com.duc.manager.repository;
import com.duc.manager.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,Integer> {
    Accounts findByUsername(String Username);

}
