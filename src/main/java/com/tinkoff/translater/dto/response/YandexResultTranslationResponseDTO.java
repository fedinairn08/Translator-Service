package com.tinkoff.translater.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YandexResultTranslationResponseDTO {
	private List<YandexTextTranslationDTO> translations;
}
