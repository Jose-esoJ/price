package com.example.price.web.controller;

import com.example.price.application.dto.PriceResponseDto;
import com.example.price.application.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;


    @Operation(summary = "get price in time intervals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get price successfully"),
            @ApiResponse(responseCode = "404", description = "Not found price in time intervals")
    })
    @GetMapping(value ="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriceResponseDto>> getPrice(@RequestParam("applicationDate") String applicationDate,
                                                           @RequestParam("productId") Long productId,
                                                           @RequestParam("brandId") Long brandId) {

        List<PriceResponseDto> responseDto = priceService.getPrice(productId, brandId, applicationDate);

        return ResponseEntity.ok(responseDto);
    }
}