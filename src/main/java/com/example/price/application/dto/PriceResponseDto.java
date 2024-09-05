package com.example.price.application.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponseDto {
    private Long productId;
    private Long brandId;
    private Long priceList;
    private String startDate;
    private String endDate;
    private BigDecimal price;
}
