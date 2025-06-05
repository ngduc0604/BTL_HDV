package com.duc.manager.repository;

import com.duc.manager.entity.CartDetails;
import com.duc.manager.entity.Carts;
import com.duc.manager.entity.OrderDetails;
import com.duc.manager.entity.Products;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
    @Query(value = "SELECT od_id FROM order_details WHERE id=:od_id",nativeQuery = true)
    List<Integer> findOderDetailsByProduct_Id(@Param("od_id")int product_id);
}
