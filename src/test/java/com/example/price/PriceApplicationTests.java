package com.example.price;

import com.example.price.application.dto.PriceResponseDto;
import com.example.price.application.service.PriceServiceImpl;
import com.example.price.domain.model.Price;
import com.example.price.domain.ports.PriceRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PriceApplicationTests {

	@InjectMocks
	private PriceServiceImpl priceService;

	@Mock
	private PriceRepository priceRepository;

	private List<Price> priceList;


	@Test
	void testPrice_At10am14thAugust() {
		when(priceRepository.findByProductIdAndBrandIdAndDateRange(
				any(Long.class), any(Long.class), any(LocalDateTime.class)))
				.thenReturn(priceList);

		List<PriceResponseDto> response = priceService.getPrice(35455L, 1L, "2023-08-14 10:00:00");

		assertEquals(1L, response.get(0).getBrandId());
		assertEquals(1L, response.get(0).getPriceList());
		assertEquals(new BigDecimal("35.50"), response.get(0).getPrice());
		assertEquals(new BigDecimal("25.45"), response.get(1).getPrice());
	}

	@Test
	void testPrice_At4pm14thAugust() {
		when(priceRepository.findByProductIdAndBrandIdAndDateRange(
				any(Long.class), any(Long.class), any(LocalDateTime.class)))
				.thenReturn(priceList);

		List<PriceResponseDto> response = priceService.getPrice(35455L, 1L, "2023-08-14 16:00:00");

		assertEquals(1L, response.get(0).getBrandId());
		assertEquals(3L, response.get(2).getPriceList());
		assertEquals(new BigDecimal("35.50"), response.get(0).getPrice());
		assertEquals(new BigDecimal("30.50"), response.get(2).getPrice());

	}

	@Test
	void testPrice_At9pm14thAugust() {

		when(priceRepository.findByProductIdAndBrandIdAndDateRange(
				any(Long.class), any(Long.class), any(LocalDateTime.class)))
				.thenReturn(priceList);

		List<PriceResponseDto> response = priceService.getPrice(35455L, 1L, "2023-08-14 21:00:00");

		assertEquals(1L, response.get(0).getBrandId());
		assertEquals(1L, response.get(0).getPriceList());
		assertEquals(new BigDecimal("35.50"), response.get(0).getPrice());

	}

	@Test
	void testPrice_At10am15thAugust() {
		when(priceRepository.findByProductIdAndBrandIdAndDateRange(
				any(Long.class), any(Long.class), any(LocalDateTime.class)))
				.thenReturn(priceList);

		List<PriceResponseDto> response = priceService.getPrice(35455L, 1L, "2023-08-15 10:00:00");

		assertEquals(1L, response.get(0).getBrandId());
		assertEquals(1L, response.get(0).getPriceList());
		assertEquals(4L, response.get(3).getPriceList());
		assertEquals(new BigDecimal("35.50"), response.get(0).getPrice());
		assertEquals(new BigDecimal("38.95"), response.get(3).getPrice());

	}

	@Test
	void testPrice_At9pm16thAugust() {
		when(priceRepository.findByProductIdAndBrandIdAndDateRange(
				any(Long.class), any(Long.class), any(LocalDateTime.class)))
				.thenReturn(priceList);

		List<PriceResponseDto> response = priceService.getPrice(35455L, 1L, "2023-08-16 21:00:00");

		assertEquals(1L, response.get(0).getBrandId());
		assertEquals(1L, response.get(0).getPriceList());
		assertEquals(new BigDecimal("35.50"), response.get(0).getPrice());
	}


	@BeforeEach
	public void setupPrices() {
		priceList = new ArrayList<>();
		priceList.add(new Price(1L, LocalDateTime.of(2023, 8, 14, 0, 0),
				LocalDateTime.of(2023, 12, 31, 23, 59, 59),
				1L, 35455L, 0, new BigDecimal("35.50"), "EUR"));
		priceList.add(new Price(1L, LocalDateTime.of(2023, 8, 14, 10, 0),
				LocalDateTime.of(2023, 8, 14, 14, 0),
				2L, 35455L, 1, new BigDecimal("25.45"), "EUR"));
		priceList.add(new Price(1L, LocalDateTime.of(2023, 8, 14, 16, 0),
				LocalDateTime.of(2023, 8, 14, 19, 0),
				3L, 35455L, 1, new BigDecimal("30.50"), "EUR"));
		priceList.add(new Price(1L, LocalDateTime.of(2023, 8, 15, 0, 0),
				LocalDateTime.of(2023, 8, 15, 23, 59, 59),
				4L, 35455L, 1, new BigDecimal("38.95"), "EUR"));
	}


}
