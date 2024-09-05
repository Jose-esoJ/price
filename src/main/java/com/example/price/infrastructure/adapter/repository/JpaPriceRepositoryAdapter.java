package com.example.price.infrastructure.adapter.repository;

import com.example.price.domain.model.Price;
import com.example.price.domain.ports.PriceRepository;
import com.example.price.infrastructure.adapter.entity.PriceEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class JpaPriceRepositoryAdapter implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;

    @Override
    public List<Price> findByProductIdAndBrandIdAndDateRange(Long productId, Long brandId, LocalDateTime date) {
        return jpaPriceRepository.findByProductIdAndBrandIdAndDateRange(productId, brandId, date)
                .stream()
                .map(this::mapPriceEntityToPrice)
                .collect(Collectors.toList());
    }

    private Price mapPriceEntityToPrice(PriceEntity entity) {
        return Price.builder()
                .brandId(entity.getBrandId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .priceList(entity.getPriceList())
                .productId(entity.getProductId())
                .priority(entity.getPriority())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .build();
    }

}
