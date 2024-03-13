package com.val.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.val.entity.Product;
import com.val.repo.IProductRepo;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	IProductRepo iProductRepo;

	@Override
	public List<Product> getAllProdSortBy(String sortBy, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
		? Sort.by(sortBy).ascending()
		: Sort.by(sortBy).descending();
		return iProductRepo.findAll(sort);
	}

	@Override
	public List<Product> save(List<Product> products) {
		return iProductRepo.saveAll(products);
	}

	@Override
	public Page<Product> getProdPagination(int page, int pageSize) {
		return iProductRepo.findAll(PageRequest.of(page, pageSize));
	}

	@Override
	public Page<Product> getProdPaginationwithSort(int page, int pageSize, String sortBy) {
		return iProductRepo.findAll(PageRequest.of(page, pageSize, Sort.by(sortBy)));
	}

}
