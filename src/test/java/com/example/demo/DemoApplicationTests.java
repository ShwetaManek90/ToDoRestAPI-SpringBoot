package com.example.demo;

import com.example.demo.controller.ProductController;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductController.class)
@WithMockUser
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	Product mockProduct = new Product(1, "Hair Clip", new BigDecimal(12));

	String exampleProductJson = "{\"id\":\"1\",\"description\":\"Hair Clip\",\"price\":\"12\"}";

	@Test
	public void retrieveProductDetails() throws Exception {

		Mockito.when(productService.getProductByID(Mockito.anyInt())).thenReturn(mockProduct);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/products").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());

		String expected = "{\"id\":\"1\",\"description\":\"Hair Clip\",\"price\":\"12\"}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void createProduct() throws Exception {
		Product mockProduct = new Product(1, "Smallest Pin", new BigDecimal(1));


		Mockito.when(productService.createProduct(Mockito.any(Product.class))).thenReturn(mockProduct);


		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addProduct")
				.accept(MediaType.APPLICATION_JSON).content(exampleProductJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost:8080/addProduct",
				response.getHeader(HttpHeaders.LOCATION));

	}
}

