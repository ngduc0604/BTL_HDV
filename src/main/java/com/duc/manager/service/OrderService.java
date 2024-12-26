package com.duc.manager.service;

import com.duc.manager.dto.request.OrderCreationRequest;

import com.duc.manager.dto.request.OrderDetailDTO;
import com.duc.manager.dto.request.OrderUpdateRequest;
import com.duc.manager.entity.Carts;
import com.duc.manager.entity.Customers;
import com.duc.manager.entity.Orders;
import com.duc.manager.repository.CustomerRepository;
import com.duc.manager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    public Orders createOrder(OrderCreationRequest request){
        Orders order= new Orders();
        Customers customers= customerService.getCustomer(request.getCustomer_id());
        order.setCustomers(customers);
        order.setOrder_date(LocalDate.now());
        order.setStatus(request.getStatus());
        order.setTotalMoney(request.getTotalMoney());
        return orderRepository.save(order);
    }

    public List<Orders> getOrders(){
        return orderRepository.findAll();
    }

    public Orders getOrder(int Id){
        return orderRepository.findById(Id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public long getQuantityOrders(){
        return orderRepository.count();
    }
    public double getRevenue(){
        return orderRepository.getRevenue();
    }

    public Map<String, Object> getNumberOrderInMonth(){
        return orderRepository.getNumberOrderInMonth();
    }

    public void deleteOrder(int Id){ orderRepository.deleteById(Id); }

    public Orders updateOrders(int Id, OrderUpdateRequest request){
        Orders order= getOrder(Id);
        order.setStatus(request.getStatus());

        return orderRepository.save(order);
    }

    // Hàm lấy tổng tiền đơn hàng theo tuần trong tháng hiện tại
    public Map<String, Object> getTotalMoneyInMonth() {
        // Gọi repository để lấy dữ liệu tổng tiền theo tuần
        return orderRepository.getTotalMoneyInMonth();
    }
    public List<Map<String, Object>> getOrderDetails() {
        return orderRepository.findOrderWithDetailsAndCustomer();
    }

    public OrderDetailDTO getOrderDetails(Long orderId) {
        List<Map<String, Object>> result = orderRepository.findOrderDetailsByOrderId(orderId);

        if (result.isEmpty()) {
            throw new RuntimeException("Order not found");
        }

        // Khởi tạo DTO
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        List<OrderDetailDTO.ProductDTO> productList = new ArrayList<>();

        // Chuyển đổi dữ liệu từ query result sang DTO
        for (Map<String, Object> row : result) {
            if (orderDetailDTO.getOrderId() == null) {
                orderDetailDTO.setOrderId(Long.valueOf(row.get("order_id").toString()));
                orderDetailDTO.setStatus(row.get("status").toString());
                orderDetailDTO.setOrderDate(row.get("order_date").toString());
                orderDetailDTO.setCustomerName(row.get("customer_name").toString());
                orderDetailDTO.setCustomerEmail(row.get("customer_email").toString());
            }
            OrderDetailDTO.ProductDTO productDTO = new OrderDetailDTO.ProductDTO();
            productDTO.setProductName(row.get("product_name").toString());
            productDTO.setProductQuantity(Integer.parseInt(row.get("product_quantity").toString()));
            productDTO.setTotal(Double.parseDouble(row.get("total").toString()));

            productList.add(productDTO);
        }

        orderDetailDTO.setProducts(productList);

        return orderDetailDTO;
    }

    public int getTotalOrderInAMonth(){
    return orderRepository.getTotalOrderInAMonth();
    }

    public List<Map<String,Carts>> getOrderByCusId(int customerId){
      return orderRepository.findOderByCusId(customerId);
    }
}

