package com.duc.manager.service;

import com.duc.manager.dto.request.OrderDetailCreationRequest;
import com.duc.manager.entity.CartDetails;
import com.duc.manager.entity.OrderDetails;
import com.duc.manager.entity.Orders;
import com.duc.manager.entity.Products;
import com.duc.manager.repository.OrderDetailsRepository;
import com.duc.manager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    public List<OrderDetails> getOrderDetails(){
        return orderDetailsRepository.findAll();
    }

    public OrderDetails getOrder(int Id){
        return orderDetailsRepository.findById(Id).get();
    }

    public OrderDetails createOrderDetails(OrderDetailCreationRequest request){
        Orders orders= orderService.getOrder(request.getOrder_id());
        Products products = productService.getProductById(request.getProduct_id());
        OrderDetails orderDetails= new OrderDetails();
        orderDetails.setOrders(orders);
        orderDetails.setProducts(products);
        orderDetails.setQuantity(request.getQuantity());
        orderDetails.setTotal(request.getTotal());
        return orderDetailsRepository.save(orderDetails);
    }


}
