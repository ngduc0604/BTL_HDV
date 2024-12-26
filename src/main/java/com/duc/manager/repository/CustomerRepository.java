package com.duc.manager.repository;

import com.duc.manager.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Integer> {
    // Phân loại khách hàng theo tổng chi tiêu (VIP, Regular)
    @Query(value = """
        SELECT c.customer_id AS customerId, 
               c.name AS name, 
               c.email AS email, 
               SUM(o.total_money) AS totalSpent,
               CASE 
                   WHEN SUM(o.total_money) > 100000 THEN 'VIP'
                   WHEN SUM(o.total_money) BETWEEN 50000 AND 100000 THEN 'Regular'
                   ELSE 'Basic'
               END AS customerType
        FROM customers c
        LEFT JOIN orders o ON c.customer_id = o.customer_id
        GROUP BY c.customer_id, c.name, c.email
        ORDER BY totalSpent DESC;
    """, nativeQuery = true)
    List<Map<String, Object>> classifyCustomersByTotalSpent();
    
}
