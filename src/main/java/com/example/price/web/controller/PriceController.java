package com.example.price.web.controller;

import com.example.price.application.dto.PriceResponseDto;
import com.example.price.application.service.ObtainPrices;
import com.example.price.domain.exception.PriceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final ObtainPrices obtainPrices;


    @Operation(summary = "get price in time intervals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get price successfully"),
            @ApiResponse(responseCode = "404", description = "Not found price in time intervals")
    })
    @GetMapping(value ="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceResponseDto> getPrice(@RequestParam("applicationDate") String applicationDate,
                                                           @RequestParam("productId") Long productId,
                                                           @RequestParam("brandId") Long brandId) {

        PriceResponseDto responseDto = obtainPrices.getPrice(productId, brandId, applicationDate);

        return ResponseEntity.ok(responseDto);
    }


    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(PriceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Message\": \"" + e.getMessage() + "\"}");
    }

}