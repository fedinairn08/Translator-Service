package com.tinkoff.translater.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YandexTranslationRequestDTO {
	private String folderId;
	private List<String> texts;
	private String targetLanguageCode;
	private String sourceLanguageCode;
}
