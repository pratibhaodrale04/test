package com.val.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.val.dto.ProductRequestDTO;
import com.val.dto.ProductResponseDTO;
import com.val.entity.Product;
import com.val.service.IProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	IProductService iProductService;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping("/all")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
		List<Product> findAll = iProductService.findAll();
		return ResponseEntity.ok(mapper.map(findAll, List.class));
	}
	
	@PostMapping("/add")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ProductResponseDTO  postMethodName(@RequestBody ProductRequestDTO productRequest) {
		return iProductService.save(productRequest);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<Product>> getProdById(@PathVariable Integer id) {
		return ResponseEntity.ok(iProductService.findById(id));
	}
	
	
	


}
