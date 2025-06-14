package com.duc.manager.service;

import com.duc.manager.dto.request.CartDetailsRequest;
import com.duc.manager.dto.request.ProductCreationRequest;
import com.duc.manager.dto.request.ProductUpdateRequest;
import com.duc.manager.entity.Products;
import com.duc.manager.repository.CartDetailsRepository;
import com.duc.manager.repository.OrderDetailsRepository;
import com.duc.manager.repository.ProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.ansi.Ansi8BitColor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private CartDetailsRepository cartDetailsRepository;


    public Products createProduct(ProductCreationRequest request){
        Products product= new Products();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImgFileName(request.getImgFileName());
        product.setStock(request.getStock());
        product.setUpdateDate(LocalDate.now());
        return productRepository.save(product);

    }

    public List<Products> getProducts(){
        return productRepository.findAll();
    }

    public Products getProductById(int Id){
        return productRepository.findById(Id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public long getQuantityProduct(){
        return productRepository.count();
    }

    public Products updateProducts(int Id, ProductUpdateRequest request){
        Products product= getProductById(Id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setUpdateDate(LocalDate.now());
       return productRepository.save(product);
    }

    public List<Map<String, Object>> getTop5(){
        return productRepository.getTop5();
    }

    public void deleteProduct(int Id){
        List<Integer>  list_id=orderDetailsRepository.findOderDetailsByProduct_Id(Id);
        List<Integer> list_cart=cartDetailsRepository.findCartDetailsByProduct_Id(Id);
        Set<Integer> list= new LinkedHashSet<>();
        list.addAll(list_id);
        list.addAll(list_cart);
        List<Integer> result = new ArrayList<>(list);
        if(result.size()==0){

        }else{
            for(Integer od_id : list){
                cartDetailsRepository.deleteById(od_id);
                orderDetailsRepository.deleteById(od_id);
            }
            productRepository.deleteById(Id);
        }
    }

    public  int getProductInMonth(){
        return productRepository.getProductInMonth();
    }

    //loc san pham theo gia
    public List<Products> productByPrice(Double minPrice, Double maxPrice){
        List<Products> a;
        if(minPrice == null && maxPrice == null){
            a = productRepository.findAll();
        }
        if(minPrice == null){
            minPrice = 0.0;
        }
        if(maxPrice == null){
            maxPrice = Double.MAX_VALUE;
        }
        a = productRepository.findProductByPrice(minPrice, maxPrice);
        return a;

    }
}
