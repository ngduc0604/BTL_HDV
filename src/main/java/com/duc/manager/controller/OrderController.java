package com.duc.manager.controller;

import com.duc.manager.dto.request.OrderCreationRequest;
import com.duc.manager.dto.request.OrderDetailDTO;
import com.duc.manager.dto.request.OrderUpdateRequest;
import com.duc.manager.entity.Carts;
import com.duc.manager.entity.Orders;

import com.duc.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    Orders createOrder(@RequestBody OrderCreationRequest request){
        return orderService.createOrder(request);
    }

    @GetMapping("/getOrders")
    List<Orders> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping("getOrder/{Id}")
    Orders getCustomer(@PathVariable("Id") int Id){
        return orderService.getOrder(Id);
    }

    @GetMapping("getRevenue")
    double getRevenue(){
        return orderService.getRevenue();
    }
    @PutMapping("/updateOrder/{Id}")
    Orders updateOrder(@RequestBody OrderUpdateRequest request, @PathVariable int Id){
        return orderService.updateOrders(Id,request);
    }

    @GetMapping("/total_revenue")
    public ResponseEntity<Map<String, Double>> getTotalRevenue() {
        double totalRevenue = orderService.getRevenue();
        Map<String, Double> response = new HashMap<>();
        response.put("totalRevenue", totalRevenue);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getNumberOrderInMonth")
    public Map<String, Object> getNumberOrderInMonth(){
        return orderService.getNumberOrderInMonth();
    }

    @DeleteMapping("deleteOrder/{Id}")
    void deleteOrder(@PathVariable int Id){
        orderService.deleteOrder(Id);
    }

    @GetMapping("/orders/total-money-weekly")
    public Map<String, Object> getTotalMoneyInMonth() {
        // Gọi hàm service để lấy dữ liệu tổng tiền các tuần
        return orderService.getTotalMoneyInMonth();
    }
    @GetMapping("/details")
    public List<Map<String, Object>> getOrderDetails() {
        return orderService.getOrderDetails();
    }

    @GetMapping("getorderdetail/{orderId}")
    public ResponseEntity<OrderDetailDTO> getOrderDetails(@PathVariable Long orderId) {
        OrderDetailDTO orderDetail = orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(orderDetail);
    }

    @GetMapping("getTotalOrderInAMonth")
    public ResponseEntity<Map<String, Integer>> getTotalOrderInAMonth(){
        int result=orderService.getTotalOrderInAMonth();
        Map<String, Integer> response = new HashMap<>();
        response.put("totalOrder", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getOrderByCusId/{customer_id}")
    public List<Map<String, Carts>> getOrderByCusId(@PathVariable int customer_id){
        return orderService.getOrderByCusId(customer_id);
    }

}
