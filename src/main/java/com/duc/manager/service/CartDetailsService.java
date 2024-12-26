package com.duc.manager.service;

import com.duc.manager.dto.request.CartDetailCreation;
import com.duc.manager.dto.request.CartDetailsRequest;
import com.duc.manager.entity.CartDetails;
import com.duc.manager.entity.Carts;
import com.duc.manager.entity.Customers;
import com.duc.manager.entity.Products;
import com.duc.manager.repository.CartDetailsRepository;
import com.duc.manager.repository.CartRepositoy;
import com.duc.manager.repository.CustomerRepository;
import com.duc.manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartDetailsService {
    @Autowired
    CartDetailsRepository cartDetailsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepositoy cartRepositoy;

    @Autowired
    ProductRepository productRepository;

    public List<Map<String,Object>> findCartDetailsByCustomerId(int customerId){
        return cartDetailsRepository.findCartDetailsByCustomerId(customerId);
    }

    public CartDetails getCartDetailsById(int id) {
        return cartDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

   public CartDetails updateQuantity(int id, CartDetailsRequest request){
        CartDetails cartDetails= getCartDetailsById(id);
        cartDetails.setQuantity(request.getQuantity());
        Products products=new Products();
        products=cartDetails.getProduct();
        double newtotal=products.getPrice()* request.getQuantity();
        cartDetails.setTotalPrice(newtotal);
        return cartDetailsRepository.save(cartDetails);
    }



   public CartDetails createCartDetail(CartDetailCreation request){
        Carts cart=  cartRepositoy.findCartByCustomerId(request.getCustomers_id());
        System.out.println(request.getCustomers_id());
        Products product= productRepository.findProductsById(request.getProduct_id());
       System.out.println(product.getId());
        CartDetails cartDetail = cartDetailsRepository.findByCartIdAndProductId(cart.getCart_id(),product.getId()).orElse(null);
       if (cartDetail != null) {
           // Cập nhật số lượng và tổng giá trị
           cartDetail.setQuantity(cartDetail.getQuantity() + request.getQuantity());
           cartDetail.setTotalPrice(cartDetail.getQuantity() * product.getPrice());
       } else {
           // Thêm sản phẩm mới vào giỏ hàng
           cartDetail = new CartDetails();
           cartDetail.setCart(cart);
           cartDetail.setProduct(product);
           cartDetail.setQuantity(request.getQuantity());
           cartDetail.setTotalPrice(product.getPrice() * request.getQuantity());
       }
       // Lưu thông tin chi tiết giỏ hàng
        return cartDetailsRepository.save(cartDetail);
   }

   public void deleteCartDetails(int id){
        cartDetailsRepository.deleteById(id);
   }

}
