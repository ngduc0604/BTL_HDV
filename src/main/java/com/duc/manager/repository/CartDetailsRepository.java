package com.duc.manager.repository;

import com.duc.manager.entity.CartDetails;
import com.duc.manager.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetails,Integer> {
        @Query(value = "SELECT cd.*\n" +
                "FROM cart_details cd\n" +
                "JOIN Carts c ON cd.cart_id = c.cart_id\n" +
                "JOIN Customers cust ON c.customer_id = cust.customer_id\n" +
                "WHERE cust.customer_id = :customerId", nativeQuery = true)
        List<Map<String,Object>> findCartDetailsByCustomerId(@Param("customerId") int customerId);

        @Query(value = "SELECT * FROM cart_details WHERE cart_id = :cartId AND product_id = :productId", nativeQuery = true)
        Optional<CartDetails> findByCartIdAndProductId(@Param("cartId") int cartId, @Param("productId") int productId);

        @Query(value = "select * FROM cart_details WHERE cart_id = :cart_id",nativeQuery = true)
        List<Map<String,Object>> getByCartId(@Param("cart_id") int cart_id);

        @Query(value = "SELECT cd_id FROM cart_details WHERE product_id=:product_id",nativeQuery = true)
        List<Integer> findCartDetailsByProduct_Id(@Param("product_id")int product_id);
}
