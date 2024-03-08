package com.val.coontroller;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.val.entity.Product;
import com.val.service.IProductService;

import jakarta.annotation.PostConstruct;

@RestController
public class ProductController {
	
	@Autowired
	IProductService iProductService;
	
  @PostConstruct
  public void initDB() {
      List<Product> products = IntStream.rangeClosed(1, 200)
              .mapToObj(i -> new Product("product" + i, new Random().nextInt(100), new Random().nextInt(50000)))
              .collect(Collectors.toList());
      iProductService.save(products);
  }
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(value = "sortBy") String sortBy,
			@RequestParam(value = "sortDirection") String sortDirection){
		return ResponseEntity.ok(iProductService.sortBy(sortBy, sortDirection));
		
	}


}
