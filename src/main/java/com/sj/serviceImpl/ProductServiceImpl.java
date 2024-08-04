package com.sj.serviceImpl;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sj.entity.Product;
import com.sj.repository.ProductRepository;
import com.sj.service.ProductService;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository repository;

	@Override
	public Product createProduct(Product product) {
		Product productData=new Product();
		productData.setProductName(product.getProductName());
		productData.setPrice(product.getPrice());
		productData.setQuantity(product.getQuantity());
		return repository.save(productData);
	}

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
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


}
