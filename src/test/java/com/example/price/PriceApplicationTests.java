package com.example.price;

import com.example.price.application.dto.PriceResponseDto;
import com.example.price.application.service.ObtainPricesImpl;
import com.example.price.domain.model.Price;
import com.example.price.domain.ports.PriceRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PriceApplicationTests {

	@InjectMocks
	private ObtainPricesImpl priceService;

	@Mock
	private PriceRepository priceRepository;


	@Test
	void testPrice_At10am14thAugust() {

		Price price = new Price(1L, LocalDateTime.of(2023, 8, 14, 10, 0),
				LocalDateTime.of(2023, 8, 14, 14, 0),
				2L, 35455L, 1, new BigDecimal("25.45"), "EUR");

		when(priceRepository.findByProductIdAndBrandIdAndDateRange(
				any(Long.class), any(Long.class), any(LocalDateTime.class)))
				.thenReturn(Optional.of(price));

		PriceResponseDto response = priceService.getPrice(35455L, 1L, "2023-08-14 10:00:00");

		assertEquals(1L, response.getBrandId());
		assertEquals(2L, response.getPriceList());
		assertEquals(new BigDecimal("25.45"), response.getPrice());
	}

	@Test
	void testPrice_At4pm14thAugust() {

		Price price = new Price(1L, LocalDateTime.of(2023, 8, 14, 16, 0),
				LocalDateTime.of(2023, 8, 14, 19, 0),
				3L, 35455L, 1, new BigDecimal("30.50"), "EUR");

		when(priceRepository.findByProductIdAndBrandIdAndDateRange(
				any(Long.class), any(Long.class), any(LocalDateTime.class)))
				.thenReturn(Optional.of(price));

		PriceResponseDto response = priceService.getPrice(35455L, 1L, "2023-08-14 16:00:00");

		assertEquals(1L, response.getBrandId());
		assertEquals(3L, response.getPriceList());
		assertEquals(new BigDecimal("30.50"), response.getPrice());

	}

	@Test
	void testPrice_At9pm14thAugust() {

		Price price = new Price(1L, LocalDateTime.of(2023, 8, 14, 0, 0),
				LocalDateTime.of(2023, 12, 31, 23, 59, 59),
				1L, 35455L, 0, new BigDecimal("35.50"), "EUR");

		when(priceRepository.findByProductIdAndBrandIdAndDateRange(
				any(Long.class), any(Long.class), any(LocalDateTime.class)))
				.thenReturn(Optional.of(price));

		PriceResponseDto response = priceService.getPrice(35455L, 1L, "2023-08-14 21:00:00");

		assertEquals(1L, response.getBrandId());
		assertEquals(1L, response.getPriceList());
		assertEquals(new BigDecimal("35.50"), response.getPrice());

	}

	@Test
	void testPrice_At10am15thAugust() {

		Price price = new Price(1L, LocalDateTime.of(2023, 8, 15, 0, 0),
				LocalDateTime.of(2023, 8, 15, 23, 59, 59),
				4L, 35455L, 1, new BigDecimal("38.95"), "EUR");

		when(priceRepository.findByProductIdAndBrandIdAndDateRange(
				any(Long.class), any(Long.class), any(LocalDateTime.class)))
				.thenReturn(Optional.of(price));

		PriceResponseDto response = priceService.getPrice(35455L, 1L, "2023-08-15 10:00:00");

		assertEquals(1L, response.getBrandId());
		assertEquals(4L, response.getPriceList());
		assertEquals(new BigDecimal("38.95"), response.getPrice());

	}

	@Test
	void testPrice_At9pm16thAugust() {

		Price price = new Price(1L, LocalDateTime.of(2023, 8, 14, 0, 0),
				LocalDateTime.of(2023, 12, 31, 23, 59, 59),
				1L, 35455L, 0, new BigDecimal("35.50"), "EUR");

		when(priceRepository.findByProductIdAndBrandIdAndDateRange(
				any(Long.class), any(Long.class), any(LocalDateTime.class)))
				.thenReturn(Optional.of(price));

		PriceResponseDto response = priceService.getPrice(35455L, 1L, "2023-08-16 21:00:00");

		assertEquals(1L, response.getBrandId());
		assertEquals(1L, response.getPriceList());
		assertEquals(new BigDecimal("35.50"), response.getPrice());
	}


}
