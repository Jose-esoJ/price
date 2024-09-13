package com.example.price.service;

import com.example.price.application.dto.PriceResponseDto;
import com.example.price.application.service.ObtainPricesImpl;
import com.example.price.domain.exception.PriceNotFoundException;
import com.example.price.domain.model.Price;
import com.example.price.domain.ports.PriceRepository;
import com.example.price.util.Format;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ObtainPricesImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private ObtainPricesImpl obtainPricesImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPrice_WhenPriceFound() {
        Long productId = 1L;
        Long brandId = 1L;
        String dateInitial = "2024-09-12 10:00:00";
        LocalDateTime applicationDate = Format.dateFormat(dateInitial);

        Price price = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .productId(1L)
                .startDate(applicationDate.minusDays(1))
                .endDate(applicationDate.plusDays(1))
                .price(new BigDecimal("100.0"))
                .priority(1)
                .build();

        when(priceRepository.findByProductIdAndBrandIdAndDateRange(productId, brandId, applicationDate))
                .thenReturn(Optional.of(price));

        PriceResponseDto result = obtainPricesImpl.getPrice(productId, brandId, dateInitial);

        assertNotNull(result);
        assertEquals(productId, result.getProductId());
        assertEquals(brandId, result.getBrandId());
        assertEquals("100.0", result.getPrice().toString());
    }

    @Test
    void testGetPrice_WhenPriceNotFound() {
        Long productId = 1L;
        Long brandId = 1L;
        String dateInitial = "2024-09-12 10:00:00";
        LocalDateTime applicationDate = Format.dateFormat(dateInitial);

        when(priceRepository.findByProductIdAndBrandIdAndDateRange(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> {
            obtainPricesImpl.getPrice(productId, brandId, dateInitial);
        });
    }

    @Test
    void testGetPrice_WhenMultiplePrices_FindsMaxPriority() {
        Long productId = 1L;
        Long brandId = 2L;
        String dateInitial = "2024-09-12 10:00:00";
        LocalDateTime applicationDate = Format.dateFormat(dateInitial);

        Price lowPriorityPrice = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .productId(1L)
                .startDate(applicationDate.minusDays(1))
                .endDate(applicationDate.plusDays(1))
                .price(new BigDecimal("80.0"))
                .priority(1)
                .build();

        Price highPriorityPrice = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .productId(2L)
                .startDate(applicationDate.minusDays(1))
                .endDate(applicationDate.plusDays(1))
                .price(new BigDecimal("100.0"))
                .priority(2)
                .build();

        when(priceRepository.findByProductIdAndBrandIdAndDateRange(productId, brandId, applicationDate))
                .thenReturn(Optional.of(highPriorityPrice));

        PriceResponseDto result = obtainPricesImpl.getPrice(productId, brandId, dateInitial);

        assertNotNull(result);
        assertEquals(2L, result.getBrandId());
        assertEquals(new BigDecimal("100.0"), result.getPrice());
    }
}
