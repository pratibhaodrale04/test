package com.val.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.val.dto.ProductRequestDTO;
import com.val.dto.ProductResponseDTO;
import com.val.entity.Product;
import com.val.repo.IProductRepo;
import com.val.service.IProductService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "/application.yml")
public class ProducrServiceTest {
	
	@Mock
	IProductRepo iProductRepo;
	
	@Autowired
	ModelMapper mapper;
	
//	@Autowired
	@InjectMocks
	IProductService iProductService;
	
	/*
	 * @Test public void test_saveProd() { // Ticket ticket = new Ticket(); Product
	 * p = new Product(1, "eded","sdas",8500L);
	 * 
	 * Mockito.when(iProductRepo.save(p)).thenReturn(p);
	 * 
	 * assertThat(iProductService.save(any(ProductRequestDTO.class))).isEqualTo(p);
	 * }
	 */
	
	@Test
	public void testGetOrdersList() {
		Product p1 = new Product(1, "eded","sdas",8500L);
		Product p2 = new Product(2, "eded","fdfg",7800L);
	    when(iProductRepo.findAll()).thenReturn(Arrays.asList(p1, p2));
	    List<Product> orderList = iProductRepo.findAll();
	    assertEquals(orderList.size(), 2);
	    assertEquals(orderList.get(0).getName(), "eded");
	    assertEquals(orderList.get(1).getProddetails(), "fdfg");
//	    assertEquals(orderList.get(0).getPrice(), 80.0);
//	    assertEquals(orderList.get(1).getPrice(), 70.0);
	}

}
