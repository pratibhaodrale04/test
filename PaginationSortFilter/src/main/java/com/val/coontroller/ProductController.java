package com.val.coontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.val.entity.Product;
import com.val.service.IProductService;

@RestController
public class ProductController {
	
	@Autowired
	IProductService iProductService;
	
	@PostMapping("/add")
	public ResponseEntity<List<Product>> addProduct(@RequestBody List<Product> products){
		return ResponseEntity.ok(iProductService.save(products));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(value = "sortBy") String sortBy,
			@RequestParam(value = "sortDirection") String sortDirection){
		return ResponseEntity.ok(iProductService.getAllProdSortBy(sortBy, sortDirection));
	}
	
//	limit==page==page number starts with zero by default
//	offset==pageSize==number of items on that page
//	?limit20&offset=0---->1st page&20 items
//	?limit20&offset=20---->2nd page&40 items
//	?limit20&offset=40---->3rd page==60 items
	@GetMapping("/allProdPaginationSort")
	public ResponseEntity<Page<Product>> getAllProductsPaginationwithSort(@RequestParam int page,
			@RequestParam int pageSize, @RequestParam(value = "sortBy") String sortBy){
		return ResponseEntity.ok(iProductService.getProdPaginationwithSort(page, pageSize, sortBy));
	}
	
	@GetMapping("/allProdPagination")
	public ResponseEntity<Page<Product>> getAllProductsPagination(@RequestParam int page,
			@RequestParam int pageSize){
		return ResponseEntity.ok(iProductService.getProdPagination(page, pageSize));
	}
}