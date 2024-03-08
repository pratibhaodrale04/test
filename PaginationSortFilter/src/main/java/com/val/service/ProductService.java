package com.val.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.val.entity.Product;
import com.val.repo.IProductRepo;

public class ProductService implements IProductService{
	
	@Autowired
	IProductRepo iProductRepo;

	@Override
	public List<Product> sortBy(String sortBy, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
		? Sort.by(sortBy).ascending()
		: Sort.by(sortBy).descending();
		
		return iProductRepo.findAll(sort);
	}

	@Override
	public Product save(Product product) {
		return iProductRepo.save(product);
	}

}
