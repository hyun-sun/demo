package com.itosamto.demo.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HelloResponseDtoTest {

	@Test
	public void helloResponseDto_LombokTest() {
		String name = "test";
		int amount = 1000;

		HelloResponseDto dto = new HelloResponseDto(name, amount);

		assertThat(dto.getName()).isEqualTo(name);
	}
}