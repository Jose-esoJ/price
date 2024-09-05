package com.example.price.controller;

import com.example.price.application.dto.PriceResponseDto;
import com.example.price.application.service.PriceService;
import com.example.price.web.controller.PriceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }

    @Test
    public void test_GetPriceSuccess() throws Exception {

        PriceResponseDto priceResponseDto = new PriceResponseDto(35455L, 1L, 1L, "2020-06-14 00:00:00", "2020-12-31 23:59:59", new BigDecimal("38.95"));

        List<PriceResponseDto> prices = List.of(priceResponseDto);

        when(priceService.getPrice(35455L, 1L, "2020-06-14 10:00:00")).thenReturn(prices);

        mockMvc.perform(get("/api/prices/search")
                        .param("applicationDate", "2020-06-14 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(1))) // Validar que hay un precio en la respuesta
                .andExpect(jsonPath("$[0].productId").value(35455L))
                .andExpect(jsonPath("$[0].price").value(new BigDecimal("38.95")));
    }


}