package com.val.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.val.customexceptions.ProductDuplicateException;
import com.val.dto.ErrorResponse;
import com.val.dto.ProductRequestDTO;
import com.val.dto.ProductResponseDTO;
import com.val.entity.Product;
import com.val.service.IProductService;

@WebMvcTest(ProductController.class)
//@AutoConfigureMockMvc
public class ProductControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	IProductService iProductService;
	String CREATE_PROD = "/api/product/add";
	String FINDALL_PROD = "/api/product/all";
	
//	1. api: POST /api/product/add ==> 200 ProductResponseJson
	@Test
	@DisplayName("JUnit test for save Product operation")
	public void givenProd_whenSave_thenReturnSavedProd() throws Exception {
//	given
		ProductResponseDTO expected = getProductResponseDTO("TestName", "TestDatails", 4500L);
		
//		when
		when(iProductService.save(any(ProductRequestDTO.class))).thenReturn(expected);
		
		MvcResult mvcResult = mockMvc.perform(post(CREATE_PROD)
				.content(asJsonString(expected))
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isCreated()).andReturn();
//		String contentAsString = mvcResult.getResponse().getContentAsString();
		ProductResponseDTO productResponseDTO = new ObjectMapper().readValue(asJsonString(expected), ProductResponseDTO.class);
        assertThat(productResponseDTO).isEqualTo(expected);
        
		/*
		 * // verify int status = mvcResult.getResponse().getStatus();
		 * assertEquals(HttpStatus.CREATED.value(), status,
		 * "Incorrect Response Status");
		 * 
		 * // verify that service method was called once
		 * verify(iProductService).save(any(ProductRequestDTO.class));
		 * 
		 * 
		 * assertNotNull(productResponseDTO); assertEquals(55000L,
		 * productResponseDTO.getPrice().longValue());
		 */
        
        
        
        
//		.andDo(print())
//		.andExpect(status().isCreated())
//		.andExpect(jsonPath("$.name", is(expected.getName())))
//		.andExpect(jsonPath("$.proddetails", is(expected.getProddetails())))
//		.andExpect(jsonPath("$.price", is(expected.getPrice()), Long.class));
	}
	
//  2. POST /api/product/add ==> 400 ErrorResponse
	@Test
	@DisplayName("JUnit test for save Product operation")
//	@Disabled
	public void givenProd_whenSaveDuplicateProd_thenThrowDuplicateException() throws Exception {
//	given
		ProductResponseDTO responseDTO = getProductResponseDTO("TestName", "TestDatails", 4500L);
		ErrorResponse expected = getErrorResponse("PROD_DUPLICATE", "Product can not be Duplicated");
			
//			when
			when(iProductService.save(any(ProductRequestDTO.class)))
			.thenThrow(new ProductDuplicateException("PROD_DUPLICATE", "Product can not be Duplicated"));
			
			MvcResult mvcResult = mockMvc.perform(post(CREATE_PROD)
					.content(asJsonString(responseDTO))
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isBadRequest()).andReturn();
			String contentAsString = mvcResult.getResponse().getContentAsString();
//			ProductResponseDTO productResponseDTO = new ObjectMapper().readValue(asJsonString(responseDTO), ProductResponseDTO.class);
//	        assertThat(productResponseDTO).isEqualTo(expected);
	        
	        ErrorResponse errorResponse = new ObjectMapper().readValue(asJsonString(expected), ErrorResponse.class);
	        assertThat(errorResponse).isEqualTo(expected);
	}
	
	@Test
	@DisplayName("JUnit test for getAll Product operation")
	public void givenProd_whenFindAll_thenReturnAllProd() throws Exception {
//		given
		List<Product> prodList = Arrays.asList(new Product(1, "TestName", "TestDatails", 4500L),
				new Product(2, "TestName", "TestDatails", 8000L),
				new Product(3, "TestName", "TestDatails", 9570L));
//		when
		when(iProductService.findAll()).thenReturn(prodList);
		
		mockMvc.perform(get(FINDALL_PROD))
		.andDo(print())
		.andExpect(status().isOk())
//		.andExpect(jsonPath("$", hasSize(4)))
		.andExpect(jsonPath("$.size()", is(prodList.size())))
		.andExpect(jsonPath("$[2].price", is(prodList.get(2).getPrice()), Long.class));
		

	}
	
	@Test
	@DisplayName("JUnit test for getById Product operation")
	public void givenProd_whenFindById_thenReturnProdWithId() {
//	given
		
	}
	
	private ProductResponseDTO getProductResponseDTO(String name, String proddetails, Long price) {
		return new ProductResponseDTO(name, proddetails, price);
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	private ErrorResponse getErrorResponse(String code, String message) {
		return new ErrorResponse(code, message);
	}
}
