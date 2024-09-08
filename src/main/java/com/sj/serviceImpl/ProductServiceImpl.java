package com.sj.serviceImpl;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sj.Model.OrderResponse;
import com.sj.Model.ProductResponse;
import com.sj.entity.Product;
//import com.sj.exception.CustomException;
import com.sj.repository.ProductRepository;
import com.sj.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//@Autowired
	private RestTemplate restTemplate;  //null
	
	//@Value("${orderservice.base.url}")
	//private String orderBaseUrl;
	
	ProductServiceImpl (@Value("${orderservice.base.url}") String orderBaseUrl, RestTemplateBuilder builder){
        this.restTemplate=builder
        		.rootUri(orderBaseUrl)  
        		.build();   //intialize rest template     
	}
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Product createProduct(Product product) {
		Product productData=new Product();
		productData.setProductName(product.getProductName());
		productData.setPrice(product.getPrice());
		productData.setQuantity(product.getQuantity());
		return repository.save(productData);
	}

    @Override
    public ProductResponse getProductById(Long id) {
    	
    	Product product = repository.findById(id).get();
		ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
		OrderResponse orderResponse = restTemplate.getForObject("/getOrdersbyID/{id}",OrderResponse.class, id);
		
	    productResponse.setOrderResponse(orderResponse);
	    return productResponse;	
		
    }

	@Override
	public List<Product> getProducts() {
		List<Product> product = repository.findAll();
		return product;
	}

	@Override
	public Product updateProducts(Long id, Product request) {
		Optional<Product> requestId = repository.findById(id);
		if(requestId.isPresent()) {
			Product requestData=requestId.get();
			requestData.setProductName(request.getProductName());
			requestData.setPrice(request.getPrice());
			requestData.setQuantity(request.getQuantity());
			return repository.save(requestData);
		}
		else {
            throw new RuntimeException("Product not found with id: " + id);
        }
	}

	@Override
	public Product updateProductByPatch(Long id, Product request) {
		Optional<Product> requestId = repository.findById(id);
		if(!requestId.isPresent()) {
			throw new RuntimeException("Product not found with id: " + id);
		}
		else {
			Product productData = requestId.get();
			if(request.getProductName()!= null) {
				productData.setProductName(request.getProductName());
			}
			if(request.getPrice()!= 0) {
				productData.setPrice(request.getPrice());
			}
			if(request.getQuantity()!= 0) {
				productData.setQuantity(request.getQuantity());
			}
			
			return repository.save(productData);
		}
	}

	@Override
	public boolean deleteProduct(Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	
	@Override
    public List<Product> convertJsonToProductList(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, new TypeReference<List<Product>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error parsing JSON string to Product list");
        }

	}
}
