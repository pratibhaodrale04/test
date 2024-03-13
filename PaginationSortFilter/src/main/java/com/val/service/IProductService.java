package com.val.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.val.entity.Product;

public interface IProductService {
	
	public List<Product> getAllProdSortBy(String sortBy, String sortDirection);
	
//	Product save(Product product);

	public List<Product> save(List<Product> products);
	
	public Page<Product> getProdPagination(int page, int pageSize);

	public Page<Product> getProdPaginationwithSort(int page, int pageSize, String sortBy);

}
