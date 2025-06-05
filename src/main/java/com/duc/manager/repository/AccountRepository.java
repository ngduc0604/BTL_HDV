package com.duc.manager.repository;
import com.duc.manager.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,Integer> {
    Accounts findByUsername(String Username);
    boolean existsByUsername(String username);
    @Query(value = "select account_id from customers where customer_id=:customer_id",nativeQuery = true)
    int getAccountIdByCustomerId(@Param("customer_id")int customer_id);
}
