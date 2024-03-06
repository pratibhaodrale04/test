package com.val.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.val.dto.ProductRequestDTO;
import com.val.dto.ProductResponseDTO;
import com.val.entity.Product;
import com.val.repo.IProductRepo;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class ProductService implements IProductService{
	
	IProductRepo iProductRepo;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public List<Product> findAll() {
		return iProductRepo.findAll();
	}

	@Override
	public ProductResponseDTO save(ProductRequestDTO productRequest) {
		Optional<ProductRequestDTO> prodRequest = Optional.ofNullable(productRequest);
		Product savedProd = iProductRepo.save(mapper.map(prodRequest, Product.class));
		return mapper.map(savedProd, ProductResponseDTO.class);
	}

	@Override
	public Optional<Product> findById(int id) {
		Optional<Product> prodById = iProductRepo.findById(id);
		if(prodById.isPresent()) {
			return prodById;
		}
		return Optional.empty();
	}
}