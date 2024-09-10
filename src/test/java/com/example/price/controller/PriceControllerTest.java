package com.example.price.controller;

import com.example.price.application.dto.PriceResponseDto;
import com.example.price.application.service.ObtainPrices;
import com.example.price.domain.exception.PriceNotFoundException;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceControllerTest {

    @Mock
    private ObtainPrices obtainPrices;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_GetPrice_Success() {

        String applicationDate = "2020-06-14 10:00:00";
        Long productId = 35455L;
        Long brandId = 1L;

        PriceResponseDto mockResponse = new PriceResponseDto(productId, brandId, 1L, applicationDate, "2020-06-14 18:00:00", new BigDecimal("35.50"));

        when(obtainPrices.getPrice(productId, brandId, applicationDate)).thenReturn(mockResponse);

        ResponseEntity<PriceResponseDto> response = priceController.getPrice(applicationDate, productId, brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());

        verify(obtainPrices, times(1)).getPrice(productId, brandId, applicationDate);
    }

    @Test
    void test_HandlePrice_NotFoundException() {

        PriceNotFoundException exception = new PriceNotFoundException("Price not found");

        ResponseEntity<?> response = priceController.handleUserAlreadyExistsException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("{\"Message\": \"Price not found\"}", response.getBody());
    }


}

