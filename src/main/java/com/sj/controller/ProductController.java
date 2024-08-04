package com.sj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sj.entity.Product;
import com.sj.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<Product> createProduct (@RequestBody Product product){
		Product productData=productService.createProduct(product);
		return new ResponseEntity<Product>(productData,HttpStatus.CREATED);
	}
	
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<Product> getProductById (@PathVariable ("id") Long id){
		Product productData=productService.getProductById(id);
		return new ResponseEntity<Product>(productData,HttpStatus.OK);
	}
	
	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getProducts(){
		List<Product> productData=productService.getProducts();
		return new ResponseEntity<List<Product>>(productData,HttpStatus.OK);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProducts(@PathVariable ("id") Long id, @RequestBody Product request){
		Product productData=productService.updateProducts(id,request);
		return new ResponseEntity<Product>(productData,HttpStatus.CREATED);
	}
	
	@PatchMapping("/Updateproducts/{id}")
	public ResponseEntity<Product> updateProductByPatch(@PathVariable ("id") Long id, @RequestBody Product request){
		Product productData=productService.updateProductByPatch(id,request);
		return new ResponseEntity<Product>(productData,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
	    boolean isDeleted = productService.deleteProduct(id);
	    if (isDeleted) {
	        return ResponseEntity.ok("Product deleted successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
	    }
	}


}
