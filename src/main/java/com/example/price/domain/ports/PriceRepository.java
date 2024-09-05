package com.example.price.domain.ports;

import com.example.price.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findByProductIdAndBrandIdAndDateRange(Long productId, Long brandId, LocalDateTime date);
}