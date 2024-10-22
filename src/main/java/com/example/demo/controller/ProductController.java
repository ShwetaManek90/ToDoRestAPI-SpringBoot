package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>("index", HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProduct( @PathVariable("id") int productId){
        Product p = productService.getProductByID(productId);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts(){
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Object> createProduct(@Validated @NonNull @RequestBody Product product){
        Product p= productService.createProduct(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<Object> createProducts(@Validated @NonNull @RequestBody List<Product> product){
        List<Product> products = productService.createProducts(product);
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

   @PutMapping("/updateProduct")
    public ResponseEntity<Object> updateProduct( @Validated @NonNull @RequestBody Product product){
       Product updatedProduct = productService.updateProduct(product);
       return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        return productService.deleteProductById(id);
    }
}
