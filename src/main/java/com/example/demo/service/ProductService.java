package com.example.demo.service;


import com.example.demo.dao.ProductRepository;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> createProducts(List<Product> products){
        return productRepository.saveAll(products);
    }

    public Product getProductByID(int productId){
        return productRepository.findById(String.valueOf(productId)).orElse(null);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product updateProduct(Product product){
        Product pro = null;
        Optional<Product> optionalProduct=productRepository.findById(String.valueOf(product.getId()));
        if(optionalProduct.isPresent()){
            pro=optionalProduct.get();
            pro.setDescription(product.getDescription());
            pro.setPrice(product.getPrice());
            productRepository.save(pro);
        }else{
            return new Product();
        }
        return pro;
    }

    public String deleteProductById(int id){
        productRepository.deleteById(String.valueOf(id));
        return "Product Deleted successfully";
    }

}
