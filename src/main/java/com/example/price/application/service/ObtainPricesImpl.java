package com.example.price.application.service;

import com.example.price.application.dto.PriceResponseDto;
import com.example.price.domain.exception.PriceNotFoundException;
import com.example.price.domain.model.Price;
import com.example.price.domain.ports.PriceRepository;
import com.example.price.util.Format;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ObtainPricesImpl implements ObtainPrices {

    private final PriceRepository priceRepository;

    @Override
    public PriceResponseDto getPrice(Long productId, Long brandId, String dateInitial) {

        LocalDateTime applicationDate = Format.dateFormat(dateInitial);

        Optional<Price> prices = priceRepository.findByProductIdAndBrandIdAndDateRange(productId, brandId, applicationDate);

        return prices.stream()
                .max(Comparator.comparing(Price::getPriority))
                .map(this::mapPriceToPriceResponseDto)
                .orElseThrow(() -> new PriceNotFoundException("No prices found for the criteria"));
    }

    private PriceResponseDto mapPriceToPriceResponseDto(Price price) {
        return PriceResponseDto.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate().toString())
                .endDate(price.getEndDate().toString())
                .price(price.getPrice())
                .build();
    }

}
