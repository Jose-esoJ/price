package com.example.price.application.service;

import com.example.price.application.dto.PriceResponseDto;
import com.example.price.application.exception.PriceNotFoundException;
import com.example.price.domain.model.Price;
import com.example.price.domain.ports.PriceRepository;
import com.example.price.util.Format;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public List<PriceResponseDto> getPrice(Long productId, Long brandId, String dateInitial) {

        LocalDateTime applicationDate = Format.dateFormat(dateInitial);

        List<Price> prices = priceRepository.findByProductIdAndBrandIdAndDateRange(productId, brandId, applicationDate)
                .stream()
                .toList();

        return Optional.of(prices)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new PriceNotFoundException("No prices found for the criteria"))
                .stream()
                .map(this::mapPriceToPriceResponseDto)
                .collect(Collectors.toList());
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
