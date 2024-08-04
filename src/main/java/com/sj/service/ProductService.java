package com.sj.service;

import java.util.List;

import com.sj.entity.Product;

public interface ProductService {

	Product createProduct(Product product);

	Product getProductById(Long id);

	List<Product> getProducts();

	Product updateProducts(Long id, Product request);

	Product updateProductByPatch(Long id, Product request);

	boolean deleteProduct(Long id);
}
