package com.duc.manager.repository;

import com.duc.manager.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CartRepositoy extends JpaRepository<Carts,Integer> {
    @Query(value = "SELECT * \n" +
            "FROM carts \n" +
            "WHERE customer_id = :customerId;",nativeQuery = true)
    Carts findCartByCustomerId(@Param("customerId") int customerId);

}
