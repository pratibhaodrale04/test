package com.val.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.val.dto.ProductRequestDTO;
import com.val.dto.ProductResponseDTO;
import com.val.entity.Product;

@Service
public interface IProductService {
	List<Product> findAll();

	ProductResponseDTO save(ProductRequestDTO productRequest);

	Optional<Product> findById(int id);

}
