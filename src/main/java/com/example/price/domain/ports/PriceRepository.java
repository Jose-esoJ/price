package com.example.price.domain.ports;

import com.example.price.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findByProductIdAndBrandIdAndDateRange(Long productId, Long brandId, LocalDateTime date);
}