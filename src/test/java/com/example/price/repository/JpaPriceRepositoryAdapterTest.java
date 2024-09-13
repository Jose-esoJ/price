package com.example.price.repository;

import com.example.price.domain.model.Price;
import com.example.price.infrastructure.adapter.entity.PriceEntity;
import com.example.price.infrastructure.adapter.repository.JpaPriceRepository;
import com.example.price.infrastructure.adapter.repository.JpaPriceRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class JpaPriceRepositoryAdapterTest {

    @Mock
    private JpaPriceRepository jpaPriceRepository;

    @InjectMocks
    private JpaPriceRepositoryAdapter jpaPriceRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_FindByProductIdAndBrandIdAndDateRange_WhenPriceExists() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.now();

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setBrandId(brandId);
        priceEntity.setStartDate(LocalDateTime.now().minusDays(1));
        priceEntity.setEndDate(LocalDateTime.now().plusDays(1));
        priceEntity.setPriceList(1L);
        priceEntity.setProductId(productId);
        priceEntity.setPriority(1);
        priceEntity.setPrice(new BigDecimal("100.50"));
        priceEntity.setCurrency("EUR");

        when(jpaPriceRepository.findByProductIdAndBrandIdAndDateRange(productId, brandId, date))
                .thenReturn(Optional.of(priceEntity));

        Optional<Price> result = jpaPriceRepositoryAdapter.findByProductIdAndBrandIdAndDateRange(productId, brandId, date);

        assertTrue(result.isPresent());
        Price price = result.get();
        assertEquals(priceEntity.getBrandId(), price.getBrandId());
        assertEquals(priceEntity.getProductId(), price.getProductId());
        assertEquals(priceEntity.getStartDate(), price.getStartDate());
        assertEquals(priceEntity.getEndDate(), price.getEndDate());
        assertEquals(priceEntity.getPriceList(), price.getPriceList());
        assertEquals(priceEntity.getPriority(), price.getPriority());
        assertEquals(priceEntity.getPrice(), price.getPrice());
        assertEquals(priceEntity.getCurrency(), price.getCurrency());
    }

    @Test
    void test_FindByProductIdAndBrandIdAndDateRange_WhenPriceDoesNotExist() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.now();

        when(jpaPriceRepository.findByProductIdAndBrandIdAndDateRange(productId, brandId, date))
                .thenReturn(Optional.empty());

        Optional<Price> result = jpaPriceRepositoryAdapter.findByProductIdAndBrandIdAndDateRange(productId, brandId, date);

        assertTrue(result.isEmpty());
    }

}