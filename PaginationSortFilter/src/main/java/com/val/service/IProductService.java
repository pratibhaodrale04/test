package com.val.service;

import java.util.List;

import com.val.entity.Product;

public interface IProductService {
	
	public List<Product> sortBy(String sortBy, String sortDirection);
	
	Product save(Product product);

}
